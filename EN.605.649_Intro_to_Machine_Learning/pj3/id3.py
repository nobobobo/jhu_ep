import math
import numpy as np

class Node: 

    def __init__(self):
        self.value = None
        self.next = None
        self.childs = None

class Id3Classifier:

    def __init__(self, X, feature_names, labels):
        self.X = X
        self.feature_names = feature_names
        self.labels = labels
        self.labelSet = list(set(labels))
        self.labelCount = [list(labels).count(x) for x in self.labelSet]
        self.node = None 
        self.entropy = self._calculate_entropy()


    def _calculate_entropy(self): 
        entropy = sum([ -count/len(self.labels) * math.log(count/len(self.labels), 2) if count else 0 for count in self.labelCount])

        return entropy



if __name__ == '__main__':
    X = []
    feature_names = ['Outlook', 'Temp', 'Humidity', 'Wind']
    labels = [0,0,1,1,1,0,1,0,1,1,1,1,1,0]

    clf = Id3Classifier(X=X, feature_names=feature_names, labels=labels)
    