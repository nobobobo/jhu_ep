import pandas as pd
import numpy as np
import matplotlib.pyplot as plt


def read_class(class_name):
    # read csv file
    df = pd.read_csv('iris.csv')

    # read data of a class 
    obs = df.where(df.species == class_name).dropna().iloc[:, 0:4]

    # calculate cov, mean, min & max
    cov = obs.cov()
    mean = obs.mean().to_numpy()
    mn = obs.min().to_numpy()
    mx = obs.max().to_numpy()

    return cov, mean, mn, mx


def gen_rnd(row_size=100, col_size=4):
    # generate random numbers with row_size * col_size
    return np.random.rand(row_size, col_size)


def normalize(rnd_mat, cov, mean, mn, mx):
    # multiply random numbers by cov
    mat = np.dot(rnd_mat, cov)

    # set means
    for i in range(len(mat[0])):
        mat[:, i] /= mat[:, i].mean()
        mat[:, i] *= mean[i]

    # min-max normalize
    df = pd.DataFrame( mn + (mat-mn)*(mx-mn) /(mx-mn))
    df.columns = ['sepal_length', 'sepal_width', 'petal_length', 'petal_width']

    return df

def plot_with_real(new_df):
    df = pd.read_csv('iris.csv')

    colors = np.where(df.species == 'setosa','r',np.where(df.species == 'versicolor','g','b'))
    plt.scatter(df.sepal_length, df.petal_width, c=colors)

    plt.show()




if __name__ == '__main__':

    df = pd.read_csv('iris.csv')

    colors = np.where(df.species == 'setosa','r',np.where(df.species == 'versicolor','g','b'))
    plt.scatter(df.sepal_length, df.petal_width, c=colors)


    classes = ['setosa', 'versicolor', 'virginica']
    colors = ['r', 'g', 'b']
    df.truncate()
    for i in range(3):
        cov, mean, mn, mx = read_class(classes[i])
        rnd_mat = gen_rnd()
        new_data = normalize(rnd_mat, cov, mean, mn, mx)
        new_data['species'] = classes[i]
        plt.scatter(new_data.sepal_length, new_data.petal_width, marker='x', c=colors[i])


    # syn_colors = np.where(syn_data.species == 'setosa','r',np.where(syn_data.species == 'versicolor','g','b'))
    # plt.scatter(syn_data.sepal_length, syn_data.petal_width,marker='x', c=colors)

    plt.show()

