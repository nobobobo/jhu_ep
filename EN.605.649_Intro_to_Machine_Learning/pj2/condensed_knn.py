import knn
import random


def sfs_classification(train_data, seed=37):

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
                x_dash = knn.get_neighbors(z, x_t, 1)[0]
                if x_t[-1] != x_dash[-1]:
                    z.append(x_t)

    return z


def sfs_regression(train_data, epsilon, seed=37):

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
                x_dash = knn.get_neighbors(z, x_t, 1)[0]
                if abs(x_t[-1] - x_dash[-1]) >= epsilon:
                    z.append(x_t)

    return z


def condensed_knn(train_data, test_data, k, isClassification, epsilon=0.001):

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
            pret = knn.predict_regression(train_data_sfs, test_p, k)

        predictions.append(pred)

    return predictions