import numpy as np
import pandas as pd 
import math

# minumum 
def my_min(df):
    return df.min().to_numpy()

# maximum
def my_max(df):
    return df.max().to_numpy()

# mean
def my_mean(df):
    return df.sum().to_numpy()/len(df)

# trimmed mean with p
def my_trimmed_mean(df, p):
    ret = [0,0,0,0]
    for i in range(len(ret)):
        arr = df[df.columns[i]].to_numpy()
        arr = np.sort(arr)
        trimmed_arr = arr[p-1:-(p-1)]
        ret[i] = sum(trimmed_arr)/len(df)

    return ret

# standard deviation
def my_std(df):
    n = len(df)
    mu = my_mean(df)

    diff = df - mu 
    var = (diff**2).sum()/(n-1)
    return np.sqrt(var).to_numpy()

# skewness
def my_skewness(df):
    n = len(df)
    sigma = my_std(df)
    mu = my_mean(df)

    diff = df - mu 
    
    a = ((diff**3).sum())/n
    b = sigma ** 3

    return (a/b).to_numpy()

# kurtosis
def my_kurtosis(df):
    n = len(df)
    sigma = my_std(df)
    mu = my_mean(df)

    diff = df - mu 

    a = ((diff**4).sum())/n
    b = sigma ** 4

    return (a/b).to_numpy()

# test code 
if __name__ == '__main__':
    df = pd.read_csv('iris.csv')
    obs = df.iloc[:, :4]

    print('min: ', my_min(obs))
    print('maz: ', my_max(obs))
    print('mean: ', my_mean(obs))
    print('trimmed mean (p=5): ', my_trimmed_mean(obs, 5))
    print('std: ', my_std(obs))
    print('skewness: ', my_skewness(obs))
    print('kurtosis: ', my_kurtosis(obs))
