import csv
from matplotlib import markers
import matplotlib.pyplot as plt
import numpy as np
import scipy as sp
from scipy.stats import chi2

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

def partition(arr_2d, sort_key, low, high):
    i = low - 1
    pivot = arr_2d[high]
    for j in range(low, high):
        if arr_2d[j][sort_key] <= pivot[sort_key]:
            i += 1
            arr_2d[i], arr_2d[j] = arr_2d[j], arr_2d[i]

    arr_2d[i+1], arr_2d[high] = arr_2d[high], arr_2d[i+1]

    return i+1

def quickSort(arr_2d, sort_key, low, high):
    if len(arr_2d) == 1:
        return arr_2d
    if low < high:

        pi = partition(arr_2d, sort_key, low, high)

        quickSort(arr_2d, sort_key, low, pi-1)
        quickSort(arr_2d, sort_key, pi+1, high)

def mahalanobis_method(class_data):
    x_minus_mu = class_data - np.mean(class_data, axis=0)
    cov = np.cov(x_minus_mu.T)
    inv_covmat = np.linalg.inv(cov)     
    left_term = np.dot(x_minus_mu, inv_covmat) 
    mahal = np.dot(left_term, x_minus_mu.T)  
    md = np.sqrt(mahal.diagonal())  

    outlier = []
    C = np.sqrt(chi2.ppf((1-0.05), df=class_data.shape[1]))    #degrees of freedom = number of variables
    for index, value in enumerate(md):
        if value > C:
            outlier.append(index)
        else:
            continue
    return outlier, md

def feature_ranking(l):
    setosa = np.array(l[1:51])[:, :4].astype(np.float)
    versicolor = np.array(l[51:101])[:, :4].astype(np.float)
    virginica = np.array(l[101:151])[:, :4].astype(np.float)  

    success_rate = [0,0,0,0]

    S1 = np.std(setosa,axis=0)
    M1 = np.mean(setosa,axis=0)
    S2 = np.std(versicolor,axis=0)
    M2 = np.mean(versicolor,axis=0)
    S3 = np.std(virginica,axis=0)
    M3 = np.mean(virginica,axis=0)

    for i in range(4):
        success = 0
        for j in range(50):
            d1 = (setosa[j][i] - M1[i]) **2 * S1[i]
            d2 = (setosa[j][i] - M2[i]) **2 * S2[i]
            d3 = (setosa[j][i] - M3[i]) **2 * S3[i] 
            
            if d1 < d2 and d1 < d3:
                success += 1
            
            d1 = (versicolor[j][i] - M1[i]) **2 * S1[i]
            d2 = (versicolor[j][i] - M2[i]) **2 * S2[i]
            d3 = (versicolor[j][i] - M3[i]) **2 * S3[i]  

            if d2 < d1 and d2 < d3:
                success += 1
            
            d1 = (virginica[j][i] - M1[i]) **2 * S1[i]
            d2 = (virginica[j][i] - M2[i]) **2 * S2[i]
            d3 = (virginica[j][i] - M3[i]) **2 * S3[i]  

            if d3 < d1 and d3 < d2:
                success += 1
        
        success_rate[i] = success / 150.0

    return success_rate
           


 


if __name__ == '__main__':
    l = read_csv('./iris.csv')
    # visualize_features(l, 2, 0)

    # data = l[1:]
    # n = len(data)
    # print("Quick Sort by index 0: sepal length")
    # quickSort(data, 0, 0, n-1)
    # for i in range (n):
    #     print(data[i])

    # print("Quick Sort by index 1: sepal width")
    # quickSort(data, 1, 0, n-1)
    # for i in range (n):
    #     print(data[i])

    # print("Quick Sort by index 2: petal length")
    # quickSort(data, 2, 0, n-1)
    # for i in range (n):
    #     print(data[i])

    # print("Quick Sort by index 3: petal width")
    # quickSort(data, 3, 0, n-1)
    # for i in range (n):
    #     print(data[i])



    # setosa = np.array(l[1:51])[:, :4].astype(np.float)
    # versicolor = np.array(l[51:101])[:, :4].astype(np.float)
    # virginica = np.array(l[101:151])[:, :4].astype(np.float)    

    # outliers_mahal, md = mahalanobis_method(setosa)
    # print(outliers_mahal)

    # outliers_mahal, md = mahalanobis_method(versicolor)
    # print(outliers_mahal)

    # outliers_mahal, md = mahalanobis_method(virginica)
    # print(outliers_mahal)


    print(feature_ranking(l))