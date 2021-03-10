import csv
from matplotlib import markers
import matplotlib.pyplot as plt
import numpy as np

# Read_csv function: 
# with the use of csv package, open & read the csv file 
# then store sepal_length, sepal_width, petal_length, petal_width, species info into a 2D list. 
def read_csv(file_name):
    with open(file_name) as f: 
        reader = csv.reader(f)
        l = [row for row in reader]
    
    return l

# Visualize_features function:
# Take sets of 2 features by their indices and the class of each observation,
# visually show the disribution by plotting.
def visualize_features(l, f1=0, f2=1):
    fig, ax = plt.subplots()
    setosa = np.array(l[1:51])
    versicolor = np.array(l[51:101])
    virginica = np.array(l[101:151])

    feat1 = setosa[:, f1]
    feat2 = setosa[:, f2]
    ax.scatter(feat1, feat2, color="b", label='Setosa')

    feat1 = versicolor[:, f1]
    feat2 = versicolor[:, f2]
    ax.scatter(feat1, feat2, color="g", label='Versicolor')

    feat1 = virginica[:, f1]
    feat2 = virginica[:, f2]
    ax.scatter(feat1, feat2, color="y", label='Virginica')

    ax.legend(scatterpoints=1)
    plt.xlabel(l[0][f1])
    plt.ylabel(l[0][f2])
    plt.show()



if __name__ == '__main__':
    l = read_csv('./iris.csv')
    visualize_features(l, 2, 0)