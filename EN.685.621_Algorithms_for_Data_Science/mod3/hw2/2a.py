import pandas as pd
import numpy as np


def read_class(class_idx=0):
    # read csv file
    df = pd.read_csv('iris.csv')

    # read data of a class with class_idx
    # 0 - Setosa, 1 - versicolor, 2 - virginica
    classes = df.species.unique()
    tgt_class = classes[class_idx]
    obs = df.where(df.species == tgt_class).dropna().iloc[:, 0:4]

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
    mat = (mat-mn)/(mx-mn)

    return mat


if __name__ == '__main__':

    cov, mean, mn, mx = read_class()
    rnd_mat = gen_rnd()
    mat = normalize(rnd_mat, cov, mean, mn, mx)

    print(mat)

