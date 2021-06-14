import numpy as np
import read_file
import data_handler
import k_fold
import algorithm
import evaluation

if __name__ == '__main__':
    path = '../datasets/car.data'
    data = read_file.read(path) 

    # data_handler.handle_catergorical_ordinal(data, 5, ['high', 'med', 'low']) 

    k = 5
    folds = k_fold.k_fold(data, k=k)

    test_data = folds[-1]
    train_data = []
    for i in range(k-1):
        train_data.extend(folds[i])

    print(algorithm.very_naive_algo(np.array(train_data), np.array(test_data), 5, mode='classification'))