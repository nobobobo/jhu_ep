import numpy as np
import pandas as pd
from sklearn.model_selection import train_test_split

def multiDiag(X1, X2):
    r1, c1 = X1.shape
    r2, c2 = X2.shape

    X1_tmp = X1.T
    X1_tmp = X1_tmp.flatten('F')
    X2_tmp = X2.flatten('F')

    X = (X1_tmp * X2_tmp).reshape(c1, r1)

    if len(X) > 1:
        xDiag = sum(X).T
    else: 
        xDiag = X.T

    return xDiag

 
class RBF:

    def __init__ (self, X, y, input_spread):
        self.X = X
        self.y = y
        self.input_spread = input_spread

    def train(self): 
        # initialize the dataset ~ O(n*d) 
        y = self.y
        n, d = self.X.shape
        X = self.X.T
        H = np.zeros([n, n])
        spread = np.sqrt(-np.log(.5))/self.input_spread

        # the loop cost O(n)
        # multiDiag() operates element-wise multiplication for matrix D and D', O(n*d)
        # so in total O(n^2*d)
        for j in range(n): 
            W = X[:, j]
            D = X - np.tile(W, (n, 1)).T
            D = D*spread 
            s = multiDiag(D.T, D)
            H[:, j] = np.exp(-s)

        # calculating weights
        H_tmp = np.concatenate((H, np.zeros([1, n])))
        W_tmp = np.linalg.lstsq(H_tmp.T, y)[0].T

        # extract weights and bias from W_hat
        W_hat = W_tmp[:-1]
        bias = W_tmp[-1]

        # classify with W and bias
        yt = (np.dot(H,W_hat.reshape(n, 1))).T[0] + bias
        ypred = np.ones(y.shape)
        ypred[yt < 0] = -1

        # calculate error
        predError = 1 - sum(y == ypred)/y.shape[0]

        self.W_hat = W_hat
        self.W = X
        self.bias = bias
        self.spread = spread
        self.error = predError

    def classify(self, X):
        n1, d1 = X.shape
        X = X.T
        n2, d2 = self.W.T.shape

        H = np.zeros([n1,n2])
        for j in range(n2):
            W = self.W[:, j]
            D = X - np.tile(W, (n1, 1)).T
            D = D*self.spread
            s = multiDiag(D.T, D)
            H[:, j] = np.exp(-s)

        y = (np.dot(H,self.W_hat)).T + self.bias
        ypred = np.ones(y.shape)
        ypred[y < 0] = -1

        return ypred

if __name__ == '__main__': 

    df = pd.read_csv('iris.csv')
    X = df.iloc[:100,:4].to_numpy()
    y = df.iloc[:100,4].to_numpy()

    y[y=='setosa'] = -1
    y[y=='versicolor'] = 1
   # y[y=='virginica'] = 3

    y=y.astype('float')

    X_train, X_test, y_train, y_test = train_test_split(X, y)
    model = RBF(X_train, y_train, 0.21)
    model.train()
    print('Train Error:', model.error)
    
    y_test_pred = model.classify(X_test)

    error = 1 - sum(y_test == y_test_pred)/y_test.shape[0]
    print('Test error: ', error)
