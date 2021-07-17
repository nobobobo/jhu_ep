from CART import CARTRegressorNode
from util import k_fold
from util import data_handler
from util.read_file import read
from util.evaluation import evaluation_metric

if __name__ == '__main__':
    names = ['abalone', 'forestfires', 'machine']
    paths = ['../datasets/abalone.data',
    '../datasets/forestfires.data',
    '../datasets/machine.data']

    datasets = {}
    for i in range(len(names)):
        datasets[names[i]] = read(paths[i])

    # Abalone
    data = datasets['abalone']
    data_handler.handle_categorical_nominal(data, 0, ['M', 'F', 'I'])

    X = [x[:-1] for x in data]
    y = [x[-1] for x in data]
    feature_names = ['Male?', 'Female?', 'Infant?', 'Length', 'Diameter', 'Height', 'Whole weight', 'Shucked weight', 'Viscera weight', 'Shell weight']


    # # Computer Hardware
    # data = datasets['machine']

    # col0 = []
    # col1 = []
    # for row in data:
    #     col0.append(row[0])
    #     col1.append(row[1])

    # col0 = list(set(col0))
    # col1 = list(set(col1))

    # feature_names = []
    # for vendor_name in col0: 
    #     feature_names.append(f'vendor_name: {vendor_name}')
    # for model_name in col1:
    #     feature_names.append(f'model_name: {model_name}')
    
    # feature_names = feature_names + ['MYCT', 'MMIN', 'MMAX', 'CACH', 'CHMIN', 'CHMAX', 'PRP']

    # data_handler.handle_categorical_nominal(data, 1, col1)
    # data_handler.handle_categorical_nominal(data, 0, col0)


    # # forest fires 
    # data = datasets['forestfires'][1:]

    # col2 = ['jan', 'feb', 'mar', 'apr', 'may', 'jun', 'jul', 'aug', 'sep', 'oct', 'nov', 'dec'] 
    # col3 = ['sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat']

    # data_handler.handle_categorical_nominal(data, 3, col3)
    # data_handler.handle_categorical_nominal(data, 2, col2) 

    # feature_names = ['X', 'Y'] + col2 + col3 + ['FFMC', 'DMS', 'DC', 'ISI', 'temp', 'RH', 'wind', 'rain']



    folds = k_fold.k_fold(data)

    tuning = folds[-1]
    tuning_folds= k_fold.k_fold(tuning)
    tuning_test = tuning_folds[-1]
    tuning_train = tuning_folds[0] + tuning_folds[1] + tuning_folds[2] + tuning_folds[3]  

    thresholds = [0.001, 0.01, 0.1, 1, 10, 100, 1000, 10000, 100000]
    best_mse = None
    best_threshold = None
    for t in thresholds:
        tuning_train_X = [x[:-1] for x in tuning_train]
        tuning_train_y = [x[-1] for x in tuning_train]
        tuning_test_X = [x[:-1] for x in tuning_test]
        tuning_test_y = [x[-1] for x in tuning_test]

        model = CARTRegressorNode(tuning_train_X, tuning_train_y, feature_names, stopping_threshold=t)
        model.fit()
        preds = [model.predict(x) for x in tuning_test_X]
        mse = evaluation_metric('mse', tuning_test_y, preds)
        # print(f'Threshold: {t}, MSE: {mse}')
        # print(best_mse)
        if not best_mse:
            best_mse = mse 
            best_threshold = t
        elif mse < best_mse:
            best_mse = mse
            best_threshold = t

    print(f'Best Threshold for Stopping is: {best_threshold}')
    validation = folds[0] + folds[1] + folds[2] + folds[3]

    validation_folds = k_fold.k_fold(validation)

    mses = []
    mses_stopped = []

    for i in range(5): 
        test = validation_folds[i]
        test_X = [x[:-1] for x in test]
        test_y = [x[-1] for x in test]

        tmp = validation_folds[:i] + validation_folds[i+1:]
        training = tmp[0] + tmp[1] + tmp[2] + tmp[3]
        training_X = [x[:-1] for x in training]
        training_y = [x[-1] for x in training] 

        model = CARTRegressorNode(training_X, training_y, feature_names, stopping_threshold=0)
        model.fit()
        preds = [model.predict(x) for x in test_X]
        mse = evaluation_metric('mse', test_y, preds)
        mses.append(mse)

        test = validation_folds[i]
        test_X = [x[:-1] for x in test]
        test_y = [x[-1] for x in test]

        tmp = validation_folds[:i] + validation_folds[i+1:]
        training = tmp[0] + tmp[1] + tmp[2] + tmp[3]
        training_X = [x[:-1] for x in training]
        training_y = [x[-1] for x in training] 

        model_stopped = CARTRegressorNode(training_X, training_y, feature_names, stopping_threshold=best_threshold)
        model.fit()
        preds_stopped = [model.predict(x) for x in test_X]
        mse_stopped = evaluation_metric('mse', test_y, preds)
        mses_stopped.append(mse_stopped)

    print(f'Unstopped: {round(sum(mses)/5,2)}')
    print(f'Stopped with threshold {best_threshold}: {round(sum(mses_stopped)/5,2)}')