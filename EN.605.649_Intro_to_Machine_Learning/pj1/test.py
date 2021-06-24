import numpy as np
import read_file
import data_handler
import k_fold
import algorithm
import evaluation

if __name__ == '__main__':
    # using car data for tesing the pipeline
    path = '../datasets/car.data'
    data = read_file.read(path) 

    print("The head of unprocessed data: ")
    print(data[:10])

    # handling categorical feature: buying (buying price), lug_boot (the size of luggage boot)
    data_handler.handle_catergorical_ordinal(data, 0, ['vhigh', 'high', 'med', 'low']) 
    data_handler.handle_categorical_nominal(data, 4, ['small', 'med', 'big'])


    print("The head of processed data: ")
    print(data[:10])


    # K Fold
    k = 5
    folds = k_fold.k_fold(data, k=k)

    test_data = folds[-1]
    train_data = []
    for i in range(k-1):
        train_data.extend(folds[i])

    # Running naive algorithm on the training dataset 
    train_label = algorithm.very_naive_algo(np.array(train_data), -1, mode='classification')
    print('The majority of the train_data: ', train_label)

    # Calculate the training accuracy
    print('Training Accuracy: ', evaluation.evaluation_metric('accuracy',np.array(train_data)[:,-1], np.array([train_label]* len(train_data))))

    # Running the majority prediction on the test_data (Simply the majority of the training data) and the calculate the test accuracy
    print('Test Accuracy: ', evaluation.evaluation_metric('accuracy',np.array(test_data)[:,-1], np.array([train_label]* len(test_data))))