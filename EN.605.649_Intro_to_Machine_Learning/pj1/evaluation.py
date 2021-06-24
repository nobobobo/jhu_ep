import numpy as np

def evaluation_metric(metric_name, true_val, predicted_val):
    '''
    A function to return the metric value given the metric type

    Input: 
        metric_name: name of the metric. Options: accuracy, mse, precision, recall, F1, mae, r2, pearson
        true_val: a list of true labels/values
        predicted_val: a list of predictions
    '''
    if metric_name == 'accuracy':
        return sum(true_val == predicted_val) / len(predicted_val)

    if metric_name == 'mse': 
        return np.square(true_val - predicted_val).mean()

    if metric_name == 'precision' or metric_name == 'recall' or metric_name == 'F1'  :

        classes = np.unique(true_val)
        precisions = np.zeros(len(classes))
        recalls = np.zeros(len(classes))

        for i in range(len(classes)):
            cls = classes[i]
            precisions[i] = sum((predicted_val == cls) * (true_val == cls))/sum(predicted_val == cls)

        for i in range(len(classes)):
            cls = classes[i]
            recalls[i] = sum((predicted_val == cls) * (true_val == cls))/sum(true_val == cls)

        f1s = 2 * (1/recalls + 1/precisions)

        if metric_name == 'precision':
            return precisions
        if metric_name == 'recall':
            return recalls
        if metric_name == 'F1': 
            return f1s

    if metric_name == 'mae': 
        return np.absolute(true_val - predicted_val).mean()

    if metric_name == 'r2': 
        tot = sum(np.square(true_val - true_val.mean()))
        res = sum(np.square(true_val - predicted_val))
        return 1 - (res/tot)

    if metric_name == 'pearson':
        cov = np.cov(true_val, predicted_val)
        return cov/ (true_val.std() * predicted_val.std()) 


if __name__ == '__main__':
    true_val = np.array([0,0,0,0,0,1,1,1,1,1])
    predicted_val = np.array([0,0,0,0,0,0,0,1,1,1])

    print(evaluation_metric('mse', true_val, predicted_val))