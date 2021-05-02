import pandas as pd 
from sklearn.feature_selection import SelectKBest
from sklearn.feature_selection import f_classif


def selectFeatures(X, y, k=10):
    print('Original shape of X: ', X.shape)
    model = SelectKBest(f_classif, k).fit(X,y)
    features = model.get_support(indices=True)
    X_new = model.transform(X)
    print(k, 'features are selected. Indices of features: ', features)
    print('The shape of new X is: ', X_new.shape)
    return X_new

if __name__ == '__main__': 
    df = pd.read_csv('trainFeatures42k.csv', header=None)
    y = df.iloc[:, 0]
    X = df.iloc[:, 1:]
    X_new = selectFeatures(X,y,10)