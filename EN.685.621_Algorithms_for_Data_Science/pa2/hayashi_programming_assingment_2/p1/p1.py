import numpy as np
import pandas as pd 


# libraries for feature selections 
from sklearn.feature_selection import SelectKBest
from sklearn.feature_selection import f_classif


# library for data preprocessing 
from sklearn.preprocessing import normalize
import scipy as sp
from scipy.stats import chi2


# classifier model libraries 
from sklearn.naive_bayes import GaussianNB
from sklearn.discriminant_analysis import LinearDiscriminantAnalysis
from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.gaussian_process.kernels import RBF
from sklearn.svm import SVC


# libary for 5-fold
from sklearn.model_selection import cross_val_score


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


def bayes(X, y): 
    gnb = GaussianNB().fit(X,y)
    y_pred = gnb.predict(X)
    tot = len(y) 
    acc = 1.0* (y==y_pred).sum() / tot
    print('Accuracy of Naive Bayes Classifier is: ', acc)


def lda(X, y): 
    clf = LinearDiscriminantAnalysis().fit(X, y)
    y_pred = clf.predict(X)
    tot = len(y) 
    acc = 1.0* (y==y_pred).sum() / tot
    print('Accuracy of LDA is: ', acc)


def rbfnn(X, y): 
    kernel = 1.0 * RBF(1.0)
    gpc = GaussianProcessClassifier(kernel=kernel,random_state=1).fit(X,y)
    print('Accuracy of RBFNN is: ', gpc.score(X,y))


def sup_vec_mac(X, y): 
    kernel = 'poly'
    clf = SVC(kernel=kernel, gamma='auto').fit(X,y)
    y_pred = clf.predict(X)
    tot = len(y) 
    acc = 1.0* (y==y_pred).sum() / tot
    print('Accuracy of SVM is: ', acc)    
    

def five_fold_1(X,y):
    clf = GaussianNB()
    scores = cross_val_score(clf, X, y, cv=5)
    print("Bayes with normalization & outlier removal: ")
    print("%0.2f accuracy with a standard deviation of %0.2f\n" % (scores.mean(), scores.std()))

def five_fold_2(X,y):
    clf = GaussianNB()
    scores = cross_val_score(clf, X, y, cv=5)
    print("Bayes with normalization: ")
    print("%0.2f accuracy with a standard deviation of %0.2f\n" % (scores.mean(), scores.std()))

def five_fold_3(X,y):
    clf = GaussianNB()
    scores = cross_val_score(clf, X, y, cv=5)
    print("SVM(Poly) with normalization: ")
    print("%0.2f accuracy with a standard deviation of %0.2f\n" % (scores.mean(), scores.std()))

    




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

    X_rmv = pd.DataFrame(tmp_X)
    y_rmv = pd.Series(tmp_y)
    
    output = X_rmv
    output['class'] = y_rmv
    output.to_csv('processed_train.csv')


    bayes(X_rmv, y_rmv)
    lda(X_rmv, y_rmv)
    # rbfnn(X_new, y_new)
    sup_vec_mac(X_rmv,y_rmv)

    five_fold_1(X_rmv,y_rmv)
    five_fold_2(X_new,y)
    five_fold_3(X_new,y)

    print('done')