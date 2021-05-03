import numpy as np
import pandas as pd 
import scipy as sp
from scipy.stats import chi2

# libraries for feature selections 
from sklearn.feature_selection import SelectKBest
from sklearn.feature_selection import f_classif


# library for data preprocessing 
from sklearn.preprocessing import normalize


def selectFeatures(X, y, k=10):
    print('Original shape of X: ', X.shape)
    model = SelectKBest(f_classif, k).fit(X,y)
    features = model.get_support(indices=True)
    X_new = model.transform(X)
    print(k, 'features are selected. Indices of features: ', features)
    print('The shape of new X is: ', X_new.shape)
    return X_new

def normal(X): 
    return normalize(X)


def mahalanobis_method(class_data):
    x_minus_mu = class_data - np.mean(class_data, axis=0) 
    cov = np.cov(x_minus_mu.T)
    inv_covmat = np.linalg.inv(cov)
    left_term = np.dot(x_minus_mu, inv_covmat)
    mahal = np.dot(left_term, x_minus_mu.T)
    md = np.sqrt(mahal.diagonal())
    outlier = []
    C = np.sqrt(chi2.ppf((1-0.01), df=class_data.shape[1])) 
    for index, value in enumerate(md):
        if value > C:
            outlier.append(index)
        else:
            continue 
    return outlier, md


def removeOutliers(class_data, outlier): 
    idx = set(range(len(class_data)))
    remain = list(idx - set(outlier))

    return [class_data[i] for i in remain]



if __name__ == '__main__': 
    df = pd.read_csv('trainFeatures42k.csv', header=None)
    y = df.iloc[:, 0]
    X = df.iloc[:, 1:]
    X_new = selectFeatures(X,y,10)
    X_new = normal(X_new)

    tmp_X = []
    tmp_y = []
    for i in range(10):
        class_data = X_new[y==i] 
        outliers, md = mahalanobis_method(class_data)
        new_class_data = removeOutliers(class_data, outliers)
        cls = np.ones(len(new_class_data)) * i
        tmp_X = tmp_X + new_class_data
        tmp_y = tmp_y + cls.tolist()

    X_new = pd.DataFrame(tmp_X)
    y_new = pd.DataFrame(tmp_y)
    
    output = X_new 
    output['class'] = y_new

    output.to_csv('processed_train.csv')
    # X_new = pd.DataFrame(tmp_X[0])
    # y_new = pd.DataFrame(tmp_y[0])

    # print(X_new)
    # test = pd.DataFrame(tmp_X[1])
    # print(test)
    # X_new.append(test, ignore_index = True)
    # print(X_new) 

    
    # print(len(tmp_y))
    print('done')