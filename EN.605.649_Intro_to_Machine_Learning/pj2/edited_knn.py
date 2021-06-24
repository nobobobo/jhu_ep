import knn


def remove_false_classification(train_data, k):
    idx = len(train_data) - 1
    ret = [data for data in train_data]
    while idx >= 0:
        x = ret.pop(idx)
        pred = knn.predict_classification(ret, x, k)
        if pred == x[-1]:
            ret.append(x)

        idx -= 1

    return ret


def remove_false_regression(train_data, k, epsilon):
    idx = len(train_data) - 1
    ret = [data for data in train_data]

    while idx >= 0:
        x = ret.pop(x)
        pred = knn.predict_regression(ret, x, k)
        if abs(pred - x[-1]) < epsilon:
            ret.append(x)

        idx -= 1


def edited_knn(train_data, test_data, k, isClassification, epsilon=0.001, num_edit=1):

    train_data_removed = [data for data in train_data]

    for i in range(num_edit):
        if isClassification:
            train_data_removed = remove_false_classification(
                train_data_removed, k)
        else:
            train_data_removed = remove_false_regression(
                train_data_removed, k, epsilon)

    predictions = []

    for test_p in test_data:
        if isClassification:
            pred = knn.predict_classification(train_data_removed, test_p, k)
        else:
            pret = knn.predict_regression(train_data_removed, test_p, k)

        predictions.append(pred)

    return predictions
