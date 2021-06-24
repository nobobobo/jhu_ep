import numpy as np

def very_naive_algo(train_data, output_col, mode='classification'):
    '''
    Majority Prediction algo, examine the dataset to return the major class or the mean value depends on the mode of the task

    Input: 
        train_data: 2D list of train_data set
        output_col: the column index of the class field 
        mode: mode of the task: 'classification' or 'regression'
    '''
    outputs = train_data[:, output_col]

    if mode=='classification': 
        dict = {}
        for cls in outputs:
            dict[cls] = 1 if cls not in dict else dict[cls] + 1
        
        major = max(dict, key=dict.get)
        return major


    if mode=='regression':
        return outputs.mean()

