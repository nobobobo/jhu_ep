import numpy as np

def very_naive_algo(train_data, test_data, output_col, mode='classification'):

    outputs = train_data[:, output_col]

    if mode=='classification': 
        dict = {}
        for cls in outputs:
            dict[cls] = 1 if cls not in dict else dict[cls] + 1
        
        major = max(dict, key=dict.get)
        return major


    if mode=='regression':
        return [outputs.mean()] * len(test_data)

