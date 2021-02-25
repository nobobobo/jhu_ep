import pandas as pd
import numpy as np
from scipy.fftpack import dct
import matplotlib.pyplot as plt
from sklearn.decomposition import PCA

if __name__ == '__main__':
    df = pd.read_csv('train.csv')

    # images matrix 42000x784
    images = df.iloc[:, 1:].to_numpy()

    # reshaped dct mat 42000x28x28
    img_dct = dct(images).reshape(42000, 28, 28)

    # extract vertical, horizontal, diagonal
    # 42000x28 for each
    vert = img_dct[:, :, 0]
    hori = img_dct[:, 0, :]
    diag = [img_dct[i].diagonal() for i in range(42000)]

    # concat vert, hori, diag for PCA
    vert_df = pd.DataFrame(vert)
    hori_df = pd.DataFrame(hori)
    diag_df = pd.DataFrame(diag)

    coef_set = pd.concat([vert_df, hori_df, diag_df], axis=1)

    # init PCA with top 3 components 
    pca = PCA(n_components=3)

    # fit with coef set
    pca.fit(coef_set)

    # reduce the transformed data
    principalComponents = pca.transform(coef_set)
    principalDf = pd.DataFrame(data = principalComponents
             , columns = ['PC1', 'PC2', 'PC3'])
    finalDf = pd.concat([principalDf, df.label], axis = 1)

    # Plot the top 2 components on 2d scatter 
    # since we have 42000 data, just plot datapoint with label 0, 1 & 2
    fig = plt.figure(figsize = (8,8))
    ax = fig.add_subplot(1,1,1, projection='3d') 
    ax.set_xlabel('PC1', fontsize = 15)
    ax.set_ylabel('PC2', fontsize = 15)
    ax.set_zlabel('PC3', fontsize = 15)
    ax.set_title('3 component PCA', fontsize = 20)
    labels = [0, 1, 2]
    colors = ['r', 'g', 'b']
    for label, color in zip(labels, colors):
        indicesToKeep = finalDf.label == label
        ax.scatter(finalDf.loc[indicesToKeep, 'PC1']
                , finalDf.loc[indicesToKeep, 'PC2']
                , finalDf.loc[indicesToKeep, 'PC3']
                , color = color
                , s = 50)
    ax.legend(labels)
    plt.show()

