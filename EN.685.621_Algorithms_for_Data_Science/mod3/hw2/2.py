import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.patches import Ellipse


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
    df = pd.DataFrame(mn+(mat-mean))
    df.columns = ['sepal_length', 'sepal_width', 'petal_length', 'petal_width']

    return df


def plot_eclipse(x, y, x_new, y_new, class_name):

    ax = plt.subplot(111)

    # real data
    cov = np.cov(x, y)
    lambda_, v = np.linalg.eig(cov)
    lambda_ = np.sqrt(lambda_)
    for j in range(1, 4):
        ell = Ellipse(xy=(np.mean(x), np.mean(y)),
                  width=lambda_[0]*j*2, height=lambda_[1]*j*2,
                  angle= np.rad2deg(np.arctan2(*v[:,0][::-1])) , color='r')
        ell.set_facecolor('none')
        ax.add_artist(ell)
    plt.scatter(x, y, marker='o', c='r')
    
    # new data
    cov_new = np.cov(x_new, y_new)
    print(cov_new)
    new_lambda_, v = np.linalg.eig(cov_new)
    new_lambda_ = np.sqrt(new_lambda_)
    for j in range(1, 4):
        ell = Ellipse(xy=(np.mean(x_new), np.mean(y_new)),
                  width=new_lambda_[0]*j*2, height=new_lambda_[1]*j*2,
                  angle= np.rad2deg(np.arctan2(*v[:,0][::-1])), color='b')
        ell.set_facecolor('none')
        ax.add_artist(ell)
    plt.scatter(x_new, y_new, marker='^', c='b')
    plt.show()


if __name__ == '__main__':

    df = pd.read_csv('iris.csv')

    classes = ['setosa', 'versicolor', 'virginica']
    for i in range(3):
        obs = df.where(df.species == classes[i]).dropna().iloc[:, 0:4]
        x = obs.sepal_length
        y = obs.petal_width

        cov, mean, mn, mx = read_class(classes[i])
        rnd_mat = gen_rnd()
        new_obs = normalize(rnd_mat, cov, mean, mn, mx)
        new_obs['species'] = classes[i]

        x_new = new_obs.sepal_length
        y_new = new_obs.petal_width

        plot_eclipse(x, y, x_new, y_new, classes[i])


