import knn
import edited_knn
import condensed_knn
from util import k_fold
from util import data_handler
from util.read_file import read
from util.evaluation import evaluation_metric

if __name__ == '__main__':
    names = ['abalone', 'breast-cancer', 'car', 'forestfires', 'house-votes', 'machine']
    paths = ['../datasets/abalone.data',
    '../datasets/breast-cancer-wisconsin.data',
    '../datasets/car.data',
    '../datasets/forestfires.data',
    '../datasets/house-votes-84.data',
    '../datasets/machine.data']

    datasets = {}
    for i in range(len(names)):
        datasets[names[i]] = read(paths[i])


    # abalone
    abalone = datasets['abalone']
    data_handler.handle_categorical_nominal(abalone, 0, ['M', 'F', 'I'])

    abalone_folds = k_fold.k_fold(abalone)
    tuning_set = abalone_folds[0]
    tuning_fold = k_fold.k_fold(tuning_set, 4)

    tuning_train = tuning_fold[0] + tuning_fold[1] + tuning_fold[2]
    tuning_test = tuning_fold[3]


    for k in range(1, 20, 2):
        knn_predictions = knn.knn(tuning_train, tuning_test, k, 1)
        met = evaluation_metric('accuracy', [data[-1] for data in tuning_test], knn_predictions)
        print('KNN: k =', k, ", Accuracy =", met)

    for k in range(1, 20, 2):
        edited_knn_predictions = edited_knn.edited_knn(tuning_train, tuning_test, k, 1, num_edit=3)
        met = evaluation_metric('accuracy', [data[-1] for data in tuning_test], edited_knn_predictions)
        print('Edited KNN: k=', k, ", Accuracy=", met)

    for k in range(1, 20, 2):
        condensed_knn_predictions = condensed_knn.condensed_knn(tuning_train, tuning_test, k, 1)
        met = evaluation_metric('accuracy', [data[-1] for data in tuning_test], condensed_knn_predictions)
        print('Condensed KNN: k=', k, ", Accuracy=", met)
