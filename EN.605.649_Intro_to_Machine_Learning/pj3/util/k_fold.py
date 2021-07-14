from random import sample
import math

def k_fold(dataset, k=5):
    '''
    A function to split the dataset into K folds, with simple approach: sort the data on the response variable and take every Kth point for a fold.

    Input: 
        dataset: 2D list of data
        k: the number of folds
    '''

    folds = [0] * k

    dataset_sorted = [data for data in dataset]
    dataset_sorted.sort(key = lambda l:l[-1])

    for i in range(len(dataset_sorted)):
        fold_idx = i % k

        if folds[fold_idx] == 0:
            folds[fold_idx] = []

        folds[fold_idx].append(dataset_sorted[i])

    return folds
