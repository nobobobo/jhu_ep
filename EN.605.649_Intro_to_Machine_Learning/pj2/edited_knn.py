import knn


def remove_false_classification(train_data, k):
    '''
    remove_false_classification()
    a function removed the falsely classified data points from trainin data pool

    inputs: 
    train_data: a list of training data
    k: the hyperparameter for classify the data while operation 

    output: 
    ret: a list of processed training data
    '''

    idx = len(train_data) - 1
    ret = [data for data in train_data]
    while idx >= 0:
        x = ret.pop(idx)
        pred = knn.predict_classification(ret, x, k)
        if pred == x[-1]:
            ret.append(x)

        idx -= 1

    return ret


def remove_false_regression(train_data, k, epsilon, sigma):
    '''
    remove_false_regression()
    a function removed the falsely classified data points from trainin data pool

    inputs: 
    train_data: a list of training data
    k: the hyperparameter for classify the data while operation 
    epsilon: the hyperparameter for evaluating the value is falsely predicted or not
    sima: the hyperparameter for RBF kernel

    output: 
    ret: a list of processed training data
    '''

    idx = len(train_data) - 1
    ret = [data for data in train_data]

    while idx >= 0:
        x = ret.pop(idx)
        pred = knn.predict_regression(ret, x, k, sigma)
        if abs(pred - x[-1]) < epsilon:
            ret.append(x)

        idx -= 1

    return ret

def edited_knn(train_data, test_data, k, isClassification, epsilon=0.001, sigma=0.01,num_edit=1):
    '''
    edited_knn()
    a function excutes a series of edited_knn operations on train data

    inputs: 
    train_data: a list of training data
    test_data: a list of testing data
    k: the hyperparameter for classify the data while operation 
    isClassification: a flag for classification/regression
    epsilon: the hyperparameter for evaluating the value is falsely predicted or not
    sima: the hyperparameter for RBF kernel
    num_edit: the number of epoc for removing falsely predicted data from the training pool.

    output: 
    prediction: a list of predicted data
    '''

    train_data_removed = [data for data in train_data]

    for i in range(num_edit):
        if isClassification:
            train_data_removed = remove_false_classification(
                train_data_removed, k)
        else:
            train_data_removed = remove_false_regression(
                train_data_removed, k, epsilon, sigma)

    predictions = []

    for test_p in test_data:
        if isClassification:
            pred = knn.predict_classification(train_data_removed, test_p, k)
        else:
            pred = knn.predict_regression(train_data_removed, test_p, k, sigma)

        predictions.append(pred)

    return predictions
