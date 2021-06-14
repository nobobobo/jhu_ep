import read_file 
from random import sample
import math

def k_fold(dataset, k=5, with_val_set=False):
    size = len(dataset)
    if with_val_set: 
        size = int(0.8 * size)

    fold_size = int(size / k)

    folds = [0] * k

    for i in range(k):
        samples = sample(dataset, fold_size)
        folds[i] = samples
        for row in samples:
            dataset.remove(row)

    if with_val_set: 
        val_size = int(len(dataset) * 0.2)
        val_set = sample(dataset, val_size)
        for row in val_set:
            dataset.remove(row)
        
    if len(dataset) != 0: 
        for i in range(len(dataset)):
            folds[i].append(dataset[i])
        dataset.clear()

    if with_val_set:
        return folds, val_set
    else: 
        return folds


if __name__ == "__main__":
    car = read_file.read('../datasets/car.data')
    
    print(len(car))
    folds = k_fold(car)

    print(len(folds[0]))

