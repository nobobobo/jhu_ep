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

    # breast-cancer
    breast_cancer = datasets['breast-cancer']
    data_handler.handle_missing(breast_cancer)

    breast_cancer_folds = k_fold.k_fold(breast_cancer)
    tuning_set = breast_cancer_folds[0]
    tuning_fold = k_fold.k_fold(tuning_set, 4)

    tuning_train = tuning_fold[0] + tuning_fold[1] + tuning_fold[2]
    tuning_test = tuning_fold[3]

    rem = breast_cancer_folds[1] + breast_cancer_folds[2] + breast_cancer_folds[3] + breast_cancer_folds[4]
    eval_fold = k_fold.k_fold(rem, 4)
    eval_train = eval_fold[0] + eval_fold[1] + eval_fold[2]
    eval_test = eval_fold[3]

    # # car
    # car = datasets['car']
    # data_handler.handle_categorical_ordinal(car, 0, ['vhigh', 'high', 'med', 'low'])
    # data_handler.handle_categorical_ordinal(car, 1, ['vhigh', 'high', 'med', 'low'])
    # data_handler.handle_categorical_ordinal(car, 2, [2, 3, 4, '5more'])
    # data_handler.handle_categorical_ordinal(car, 3, [2, 4, 'more'])
    # data_handler.handle_categorical_ordinal(car, 4, ['small', 'med', 'big'])
    # data_handler.handle_categorical_ordinal(car, 5, ['low', 'med', 'high'])
    # data_handler.handle_categorical_ordinal(car, 6, ['unacc', 'acc', 'good', 'vgood'])

    # car_folds = k_fold.k_fold(car)
    # tuning_set = car_folds[0]
    # tuning_fold = k_fold.k_fold(tuning_set, 4)

    # tuning_train = tuning_fold[0] + tuning_fold[1] + tuning_fold[2]
    # tuning_test = tuning_fold[3]

    # rem = car_folds[1] + car_folds[2] + car_folds[3] + car_folds[4]
    # eval_fold = k_fold.k_fold(rem, 4)
    # eval_train = eval_fold[0] + eval_fold[1] + eval_fold[2]
    # eval_test = eval_fold[3]


    # # congress-vote
    # house_vote = datasets['house-votes']

    # for i in range(len(house_vote)):
    #     cls = house_vote[i][0]
    #     house_vote[i].remove(cls)
    #     house_vote[i].append(cls)

    # row_len = len(house_vote[0]) 
    # data_handler.handle_categorical_nominal(house_vote, row_len-1, ['republican','democrat'])

    # for i in range(row_len-1):
    #     data_handler.handle_categorical_nominal(house_vote, row_len-2 - i, ['y','n','?'])

    # house_vote_folds = k_fold.k_fold(house_vote)
    # tuning_set = house_vote_folds[0]
    # tuning_fold = k_fold.k_fold(tuning_set, 4)

    # tuning_train = tuning_fold[0] + tuning_fold[1] + tuning_fold[2]
    # tuning_test = tuning_fold[3]
    
    # rem = house_vote_folds[1] + house_vote_folds[2] + house_vote_folds[3] + house_vote_folds[4]
    # eval_fold = k_fold.k_fold(rem, 4)
    # eval_train = eval_fold[0] + eval_fold[1] + eval_fold[2]
    # eval_test = eval_fold[3]   


    # # # tuning k-vals
    # # for k in range(1, 30, 2):
    # #     knn_predictions = knn.knn(tuning_train, tuning_test, k, 1)
    # #     met = evaluation_metric('accuracy', [data[-1] for data in tuning_test], knn_predictions)
    # #     print('KNN: k =', k, ", Accuracy =", met)

    # # for k in range(1, 30, 2):
    # #     edited_knn_predictions = edited_knn.edited_knn(tuning_train, tuning_test, k, 1, num_edit=1)
    # #     met = evaluation_metric('accuracy', [data[-1] for data in tuning_test], edited_knn_predictions)
    # #     print('Edited KNN: k=', k, ", Accuracy=", met)

    # for k in range(1, 30, 2):
    #     condensed_knn_predictions = condensed_knn.condensed_knn(tuning_train, tuning_test, k, 1)
    #     met = evaluation_metric('accuracy', [data[-1] for data in tuning_test], condensed_knn_predictions)
    #     print('Condensed KNN: k=', k, ", Accuracy=", met)


    # Evaluate Performances
    knn_predictions = knn.knn(eval_train, eval_test, 11, 1)
    met = evaluation_metric('accuracy', [data[-1] for data in eval_test], knn_predictions)
    print("Accuracy =", met)
    knn_predictions = edited_knn.edited_knn(eval_train, eval_test, 9, 1)
    met = evaluation_metric('accuracy', [data[-1] for data in eval_test], knn_predictions)
    print("Accuracy =", met)
    knn_predictions = condensed_knn.condensed_knn(eval_train, eval_test, 9, 1)
    met = evaluation_metric('accuracy', [data[-1] for data in eval_test], knn_predictions)
    print("Accuracy =", met)




    # abalone
    abalone = datasets['abalone']
    data_handler.handle_categorical_nominal(abalone, 0, ['M', 'F', 'I'])

    abalone_folds = k_fold.k_fold(abalone)
    tuning_set = abalone_folds[0]
    tuning_fold = k_fold.k_fold(tuning_set, 4)

    tuning_train = tuning_fold[0] + tuning_fold[1] + tuning_fold[2]
    tuning_test = tuning_fold[3]

    rem = abalone_folds[1] + abalone_folds[2] + abalone_folds[3] + abalone_folds[4]
    eval_fold = k_fold.k_fold(rem, 4)
    eval_train = eval_fold[0] + eval_fold[1] + eval_fold[2]
    eval_test = eval_fold[3]


    # machine 
    machine = datasets['machine']
    col0 = []
    col1 = []
    for row in machine:
        col0.append(row[0])
        col1.append(row[1])

    col0 = list(set(col0))
    col1 = list(set(col1))

    data_handler.handle_categorical_nominal(machine, 1, col1)
    data_handler.handle_categorical_nominal(machine, 0, col0)

    machine_folds = k_fold.k_fold(machine)
    tuning_set = machine_folds[0]
    tuning_fold = k_fold.k_fold(tuning_set, 4)

    tuning_train = tuning_fold[0] + tuning_fold[1] + tuning_fold[2]
    tuning_test = tuning_fold[3]

    rem = machine_folds[1] + machine_folds[2] + machine_folds[3] + machine_folds[4]
    eval_fold = k_fold.k_fold(rem, 4)
    eval_train = eval_fold[0] + eval_fold[1] + eval_fold[2]
    eval_test = eval_fold[3]


    # forest fires
    forest_fires = datasets['forestfires'][1:]

    col2 = ['jan', 'feb', 'mar', 'apr', 'may', 'jun', 'jul', 'aug', 'sep', 'oct', 'nov', 'dec'] 
    col3 = ['sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat']

    data_handler.handle_categorical_nominal(forest_fires, 3, col3)
    data_handler.handle_categorical_nominal(forest_fires, 2, col2) 

    forest_fires_folds = k_fold.k_fold(forest_fires)
    tuning_set = forest_fires_folds[0]
    tuning_fold = k_fold.k_fold(tuning_set, 4)

    tuning_train = tuning_fold[0] + tuning_fold[1] + tuning_fold[2]
    tuning_test = tuning_fold[3]

    rem = forest_fires_folds[1] + forest_fires_folds[2] + forest_fires_folds[3] + forest_fires_folds[4]
    eval_fold = k_fold.k_fold(rem, 4)
    eval_train = eval_fold[0] + eval_fold[1] + eval_fold[2]
    eval_test = eval_fold[3]    


    # # tuning k-vals
    # for k in range(1, 30, 2):
    #     for sigma in [100, 10, 1, 0.1, 0.5, 0.01, 0.001]:
    #         knn_predictions = knn.knn(tuning_train, tuning_test, 1, 0, sigma=sigma)
    #         met = evaluation_metric('mse', [data[-1] for data in tuning_test], knn_predictions)
    #         print('KNN: k =', k, ", sigma: ", sigma, ", MSE =", met)

    # for k in range(1, 30, 2):
    #     for sigma in [100, 10, 1, 0.1, 0.01]:
    #         edited_knn_predictions = edited_knn.edited_knn(tuning_train, tuning_test, 9, 0, epsilon=10, sigma=sigma, num_edit=1)
    #         met = evaluation_metric('mse', [data[-1] for data in tuning_test], edited_knn_predictions)
    #         print('Edited KNN: k=', k, ", epsilon = ", sigma, ", MSE=", met)

    # for k in [5]:
    #     for sigma in [100]:
    #         for eps in [100,10,1,0.1,0.01,0.001]:
    #             condensed_knn_predictions = condensed_knn.condensed_knn(tuning_train, tuning_test, k, 0, epsilon = eps, sigma=sigma)
    #             met = evaluation_metric('mse', [data[-1] for data in tuning_test], condensed_knn_predictions)
    #             print('Condensed KNN: ', k, ", MSE=", met)

    # Eval
    knn_predictions = knn.knn(eval_train, eval_test, 1, 0, sigma=100)
    met = evaluation_metric('mse', [data[-1] for data in eval_test], knn_predictions)
    print("MSE =", met)

    knn_predictions = edited_knn.edited_knn(eval_train, eval_test, 1, 0, epsilon=100,sigma=100)
    met = evaluation_metric('mse', [data[-1] for data in eval_test], knn_predictions)
    print("MSE =", met)    

    knn_predictions = condensed_knn.condensed_knn(eval_train, eval_test, 1, 0, epsilon=100,sigma=100)
    met = evaluation_metric('mse', [data[-1] for data in eval_test], knn_predictions)
    print("MSE =", met)    