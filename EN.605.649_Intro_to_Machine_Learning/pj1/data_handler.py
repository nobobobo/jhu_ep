import read_file
import math
import numpy as np

def handle_missing(dataset, missing_label='?', hasColHeader=False):
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


def handle_catergorical_ordinal(dataset, col_idx, category_list):
    for data in dataset: 
        for i in range(len(category_list)):
            if data[col_idx] == category_list[i]:
                data[col_idx] = i


def handle_categorical_nominal(dataset, col_idx, category_list): 
    for i in range(len(dataset)): 
        encode = [0] * len(category_list)
        pos = category_list.index(dataset[i][col_idx])
        encode[pos] = 1
        
        dataset[i] = dataset[i][:col_idx] + encode + dataset[i][col_idx+1:]


def discretization(dataset, col_idx, bin_num = 3, isEw=True): 
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
    train_arr = np.array(train_data)
    col_data = np.transpose(train_arr)[col_idx].astype(float)
    mean = np.mean(col_data)
    std = np.std(col_data)

    for data in train_data:
        print((data[col_idx] - mean)/std)
        data[col_idx] = (data[col_idx] - mean)/std

    for data in test_data: 
        data[col_idx] = (data[col_idx] - mean)/std

    return train_data, test_data
    


if __name__ == '__main__':
    car = read_file.read('../datasets/car.data')

    handle_catergorical_ordinal(car, 0, ['vhigh','high', 'med', 'low'])

    abalone = read_file.read('../datasets/abalone.data')

    # print(abalone[:10])
    # abalone = discretization(abalone, 1)
    # print(abalone[:10])

