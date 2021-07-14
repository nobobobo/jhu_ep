from util import read_file
import math
import numpy as np


def handle_missing(dataset, missing_label='?', hasColHeader=False):
    '''
    A function to inpute the missing value with the column's average

    Input: 
        dataset: 2D list of dataset
        missing_label='?': a label string to indicate missing
        hasColHeader=False: boolean value to skip the first column row or not

    Output: 
        dataset: dataset with missing values filled
        missing_idx: a list of index the data was missed
    '''
    if hasColHeader:
        dataset = dataset[1:]

    data_len = len(dataset)
    col_len = len(dataset[0])
    for i in range(col_len):

        if read_file.is_number(dataset[0][i]) == False:
            continue

        sum = 0
        missing_idx = []
        for j in range(data_len):
            if dataset[j][i] == missing_label:
                missing_idx = missing_idx + [j]
            else:
                dataset[j][i] = float(dataset[j][i])
                sum += dataset[j][i]

        mean = 1.0 * sum / (data_len - len(missing_idx))

        for idx in missing_idx: 
            dataset[idx][i] = mean


    return dataset


def handle_categorical_ordinal(dataset, col_idx, category_list):
    '''
    A function to convert oridinal categorical field to a list

    Input: 
        dataset: 2D list of dataset
        col_idx: index number of the categorical field
        category_list: a list of categeory in order such as ['small', 'medium', 'large']

    '''
    for data in dataset: 
        for i in range(len(category_list)):
            if data[col_idx] == category_list[i]:
                data[col_idx] = i


def handle_categorical_nominal(dataset, col_idx, category_list): 
    '''
    A function to convert nominal categorical field with one-hot encoding

    Input: 
        dataset: 2D list of dataset
        col_idx: index number of the categorical field
        category_list: a list of categeory in order such as ['small', 'medium', 'large']     
    '''
    for i in range(len(dataset)): 
        encode = [0] * len(category_list)

        pos = category_list.index(dataset[i][col_idx])
        encode[pos] = 1
        
        dataset[i] = dataset[i][:col_idx] + encode + dataset[i][col_idx+1:]


def discretization(dataset, col_idx, bin_num = 3, isEw=True): 

    '''
    A function to discretize the field into specific number of groups

    Input: 
        dataset: 2D list of dataset
        col_idx: index number of the target field
        bin_num: number of groups to distribute the target field values
        isEw: mode of the discretization, True for equal-width, false for equal frequency
    '''
    sorted_dataset = sorted(dataset, key = lambda l:l[col_idx])

    if isEw:
        min = sorted_dataset[0][col_idx]
        width = (sorted_dataset[-1][col_idx] - sorted_dataset[0][col_idx])/bin_num
        for data in sorted_dataset:
            data[col_idx] = math.floor((data[col_idx] - min)/width)

    else: 
        bin_size = math.floor(len(sorted_dataset)/bin_num)
        cnt = 0
        for data in sorted_dataset:
            cnt += 1
            data[col_idx] = math.floor(cnt/bin_size)

    return sorted_dataset


def standardization(train_data, test_data, col_idx): 
    '''
    A function to standardize the column values 

    Input: 
        Train_data: 2D list of train dataset
        test_data: 2D list of test dataset
        col_idx: index number of the target field

    Output: 
        trian_data: standardized train_data
        test_data: standardized test_data
    '''
    train_arr = np.array(train_data)
    col_data = np.transpose(train_arr)[col_idx].astype(float)
    mean = np.mean(col_data)
    std = np.std(col_data)

    for data in train_data: 
        data[col_idx] = (data[col_idx] - mean)/std

    for data in test_data: 
        data[col_idx] = (data[col_idx] - mean)/std

    return train_data, test_data
    


if __name__ == '__main__':
    # bc = read_file.read('../datasets/breast-cancer-wisconsin.data')
    # bc = handle_missing(bc)

    # car = read_file.read('../datasets/car.data')
    

    # print("The head of unprocessed data: ")
    # print(car[:10])

    # # handling categorical feature: buying (buying price), lug_boot (the size of luggage boot)
    # handle_categorical_nominal(car, 4, ['small', 'med', 'big'])


    # print("The head of processed data: ")
    # print(car[:10])


    abalone = read_file.read('../datasets/abalone.data')
    abalone = sorted(abalone, key = lambda l:l[0])
    print("Discretization test with abalone, Length column: ")
    print(abalone[:10])
    abalone = discretization(abalone, 1)
    abalone = sorted(abalone, key = lambda l:l[0])
    print(abalone[:10])


    # print('before: ')
    # print(abalone[:10])
    # abalone[:100], abalone[100:] = standardization(abalone[:100],abalone[100:], 1  )
    # print('after:')
    # print(abalone[:10])

