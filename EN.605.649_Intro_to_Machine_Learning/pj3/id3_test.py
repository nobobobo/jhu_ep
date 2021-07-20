from ID3 import Id3Classifier
from util import k_fold
from util import data_handler
from util.read_file import read
from util.evaluation import evaluation_metric




if __name__ == '__main__':
    names = ['breast-cancer', 'car','house-votes']
    paths = ['../datasets/breast-cancer-wisconsin.data',
    '../datasets/car.data',
    '../datasets/house-votes-84.data']

    datasets = {}
    for i in range(len(names)):
        datasets[names[i]] = read(paths[i])


    # breast-cancer
    data = datasets['breast-cancer']
    data_handler.handle_missing(data)

    data = [x[1:] for x in data] # drop id column

    feature_names = ['Clump Thickness', 'Uniformity of Cell Size', 'Uniformity of Cell Shape', 'Marginal Adhesion', 'Single Epithelial Cell Size', 'Bare Nuclei', 'Bland Chromatin', 'Normal Nucleoli', 'Mitoses']

    # Car Eval
    data = datasets['car']
    feature_names = ['buying', 'maint', 'doors', 'persons', 'lug_boot', 'safety']


    # # Congressional Vote
    # data = datasets['house-votes']
    # for i in range(len(data)):
    #     cls = data[i][0]
    #     data[i].remove(cls)
    #     data[i].append(cls)

    # feature_names = ['handicapped-infants', 'water-project-cost-sharing', 'adoption-of-the-budget-resolution', 'physician-fee-freeze', 'el-salvador-aid', 'religious-groups-in-schools', 'anti-satellite-test-ban', 'aid-to-nicaraguan-contras', 'mx-missile', 'immigration', 'synfuels-corporation-cutback', 'education-spending', 'superfund-right-to-sue', 'crime', 'duty-free-exports', 'export-administration-act-south-africa']


    folds = k_fold.k_fold(data)

    pruning = folds[-1]
    pruning_X = [x[:-1] for x in pruning]
    pruning_labels = [x[-1] for x in pruning]

    validation = folds[0] + folds[1] + folds[2] + folds[3]

    validation_folds = k_fold.k_fold(validation)

    accs = []
    accs_pruned = []

    for i in range(1):
        test = validation_folds[i]
        test_X = [x[:-1] for x in test]
        test_labels = [x[-1] for x in test]

        tmp = validation_folds[:i] + validation_folds[i+1:]
        training = tmp[0] + tmp[1] + tmp[2] + tmp[3]
        training_X = [x[:-1] for x in training]
        training_labels = [x[-1] for x in training]

        model = Id3Classifier(training_X,feature_names,training_labels)
        model.fit()
        model.print_tree()

        predictions = [model.predict(x) for x in test_X]
        acc = evaluation_metric('accuracy', test_labels, predictions)
        accs.append(acc)

        model.reduced_error_pruning(pruning_X, pruning_labels)
        predictions_pruned = [model.predict(x) for x in test_X]
        acc_pruned = evaluation_metric('accuracy', test_labels, predictions_pruned)
        accs_pruned.append(acc_pruned)
        model.print_tree()

    # model.print_tree()

    # print('Before Pruning: ',round(sum(accs)/5, 4))
    # print('After Pruning: ',round(sum(accs_pruned)/5, 4))