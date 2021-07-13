import knn
import random


def sfs_classification(train_data, seed=37):
    '''
    sfs_classification()
    a function operates stepwise forward selection on training dataset

    inputs: 
    train_data: a list of training data
    seed: a seed for the dataset shuffling

    output:
    z: a list of processed dataset
    '''

    random.seed(seed)
    train_data_shuffled = [data for data in train_data]
    random.shuffle(train_data_shuffled)

    z = []
    z_len = -1

    while z_len != len(z):
        z_len = len(z)
        for x_t in train_data:
            if len(z) == 0:
                z.append(x_t)
            else:
                x_dash = knn.get_neighbors(z, x_t, 1)[0]["data"]
                if x_t[-1] != x_dash[-1]:
                    z.append(x_t)

    return z


def sfs_regression(train_data, epsilon, seed=37):
    '''
    sfs_classification()
    a function operates stepwise forward selection on training dataset

    inputs: 
    train_data: a list of training data
    epsilon: the hyperparameter for evaluating the value is falsely predicted or not
    seed: a seed for the dataset shuffling

    output:
    z: a list of processed dataset
    '''

    random.seed(seed)
    train_data_shuffled = [data for data in train_data]
    random.shuffle(train_data_shuffled)

    z = []
    z_len = -1

    while z_len != len(z):
        z_len = len(z)
        for x_t in train_data:
            if len(z) == 0:
                z.append(x_t)
            else:
                x_dash = knn.get_neighbors(z, x_t, 1)[0]["data"]
                if abs(x_t[-1] - x_dash[-1]) >= epsilon:
                    z.append(x_t)

    return z


def condensed_knn(train_data, test_data, k, isClassification, epsilon=0.01, sigma=0.01):
    '''
    condensed_knn()
    a function excutes a series of condensed knn operations on train data

    inputs: 
    train_data: a list of training data
    test_data: a list of testing data
    k: the hyperparameter for classify the data while operation 
    isClassification: a flag for classification/regression
    epsilon: the hyperparameter for evaluating the value is falsely predicted or not
    sima: the hyperparameter for RBF kernel
    
    output: 
    prediction: a list of predicted data
    '''
    train_data_sfs = [data for data in train_data]

    if isClassification:
        train_data_sfs = sfs_classification(train_data_sfs)
    else: 
        train_data_sfs = sfs_regression(train_data_sfs, epsilon)
    
    predictions = []

    for test_p in test_data:
        if isClassification:
            pred = knn.predict_classification(train_data_sfs, test_p, k)
        else:
            pred = knn.predict_regression(train_data_sfs, test_p, k, sigma)

        predictions.append(pred)

    return predictions