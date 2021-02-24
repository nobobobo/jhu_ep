import pandas as pd 
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.image as mpimg


def read_and_reshape():

    # read csv file and take first 100 as samples
    df = pd.read_csv('train.csv')
    df = df.iloc[:100]

    # extract pixel values
    numbers = df.iloc[:,1:]

    # convert to numpy array and reshape
    # 100 of 28x28 pictures ~ 100x28x28
    mat = numbers.to_numpy().reshape(100, 28, 28)

    return mat
    
    

if __name__ == '__main__':
    mat = read_and_reshape()
    idxs = [1, 2, 4, 7, 8, 9, 11, 12, 17, 22]

    plt.figure()
    for i in range(len(idxs)):
        plt.subplot(2,5,i+1)
        plt.imshow(mat[idxs[i]])

    plt.show()

