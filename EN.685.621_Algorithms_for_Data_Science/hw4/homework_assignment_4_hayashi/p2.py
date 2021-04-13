import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split


if __name__ == '__main__':
    df = pd.read_csv('iris.csv')
    X = df.iloc[:,:4].to_numpy()
    y = df.iloc[:,4].to_numpy()

    y[y=='setosa'] = 0
    y[y=='versicolor'] = 1
    y[y=='virginica'] = 2

    y=y.astype('float')

    x = np.zeros(X.shape)

    # Normalize the data
    # O(n*d) 
    for i in range(len(X)):
        x[i, :] = X[i,:]/np.sqrt(np.dot(X[i,:] ,X[i,:].T))
    
    w = x
    w1 = w[:50, :]
    w2 = w[51:100, :]
    w3 = w[101:, :]

    temp = np.zeros([3, 1])
    sigma = 0.5
    ypred = np.zeros([1, 150])

    m1 = len(w1)
    m2 = len(w2)
    m3 = len(w3)


    # classification with possibilities
    # Outerloop: O(n)
    # Innerloops: O(n/3 * d) *3 = O(nd)
    for i in range(len(X)): 

        sum1 = 0 
        for j in range(m1):
            z1 = np.dot(w1[j, :],x[i,:].T)
            sum1 = sum1 + np.exp((z1-1)/sigma**2)

        temp[0] = sum1/m1

        sum2 = 0
        for j in range(m2):
            z2 = np.dot(w2[j, :], x[i, :].T)
            sum2 = sum2 + np.exp((z2-1)/(sigma**2))

        temp[1] = sum2/m2

        sum3 =0 
        for j in range(m3):
            z3 = np.dot(w3[j,:] , x[i, :].T)
            sum3 = sum3 + np.exp((z3-1)/sigma**2)

        temp[2] = sum3/m3

        ypred[0,i] = np.where(temp ==np.amax(temp))[0]

    accuracy = sum((ypred == y)[0])/150

    print('Classification accuracy: ', accuracy)

    # With the normalization and classification ,total cost will be O(n^2*d)



        



