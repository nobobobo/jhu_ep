import math
from collections import deque

from pandas.core import base
import numpy as np

from util.evaluation import evaluation_metric


class Node:

    def __init__(self):
        self.value = None
        self.next = None
        self.childs = None
        self.isPruned = False
        self.labelAfterPruned = None


class Id3Classifier:

    def __init__(self, X, feature_names, labels):
        self.X = X
        self.feature_names = feature_names
        self.labels = labels
        self.labelSet = list(set(labels))
        self.labelCounts = [list(labels).count(label)
                            for label in self.labelSet]
        self.node = None

        init_data_ids = [x for x in range(len(self.labels))]
        self.entropy = self._calculate_entropy(init_data_ids)

    def _calculate_entropy(self, data_ids):

        labels = [self.labels[i] for i in data_ids]
        label_counts = [labels.count(label) for label in self.labelSet]

        entropy = sum([-count/len(data_ids) * math.log(count /
                                                       len(data_ids), 2) if count else 0 for count in label_counts])

        return entropy

    def _calculate_information_gain(self, data_ids, feature_id):
        info_gain = self._calculate_entropy(data_ids)

        features = [self.X[i][feature_id] for i in data_ids]
        feature_vals = list(set(features))
        feature_val_counts = [features.count(val) for val in feature_vals]

        feature_val_ids = [[data_ids[i] for i, x in enumerate(
            features) if x == y] for y in feature_vals]

        info_gain = info_gain - sum([val_counts/len(data_ids) * self._calculate_entropy(
            val_ids) for val_counts, val_ids in zip(feature_val_counts, feature_val_ids)])

        return info_gain

    def _get_max_ig_feature(self, data_ids, feature_ids):
        information_gains = [self._calculate_information_gain(
            data_ids, feature_id) for feature_id in feature_ids]
        max_ig = max(information_gains)
        max_id = feature_ids[information_gains.index(max_ig)]

        return max_id

    def fit(self):
        data_ids = [x for x in range(len(self.X))]
        feature_ids = [x for x in range(len(self.feature_names))]

        self.node = self._fit_helper(data_ids, feature_ids, self.node)

    def _fit_helper(self, data_ids, feature_ids, node):
        if not node:
            node = Node()

        labels = [self.labels[i] for i in data_ids]
        node.labelAfterPruned = max(set(labels), key=labels.count)

        if len(set(labels)) == 1:
            node.value = self.labels[data_ids[0]]
            return node

        if len(feature_ids) == 0:
            node.value = max(set(labels), key=labels.count)
            return node

        best_feature_id = self._get_max_ig_feature(data_ids, feature_ids)
        best_feature_name = self.feature_names[best_feature_id]
        node.value = best_feature_name
        node.childs = []

        features = [self.X[i][best_feature_id] for i in data_ids]
        feature_vals = list(set(features))

        for val in feature_vals:
            child = Node()
            child.value = val
            node.childs.append(child)

            child_data_ids = [
                i for i in data_ids if self.X[i][best_feature_id] == val]
            if not child_data_ids:
                child.next = max(set(labels), key=labels.count)
            else:
                if feature_ids and best_feature_id in feature_ids:
                    idx = feature_ids.index(best_feature_id)
                    feature_ids.pop(idx)
                child.next = self._fit_helper(
                    child_data_ids, feature_ids, child.next)

        return node

    def print(self):
        print("ID3 Tree: ")
        if not self.node:
            return
        nodes = deque()
        nodes.append(self.node)
        while len(nodes) > 0:
            node = nodes.popleft()
            if node.isPruned:
                print(node.labelAfterPruned)
            else: 
                print(node.value)
                if node.childs:
                    for child in node.childs:
                        print('({})'.format(child.value))
                        nodes.append(child.next)
                elif node.next:
                    print(node.next)

        print()


    def predict(self, data):
        if not self.node:
            return

        node = self.node

        while not node.isPruned and node.childs:
            best_feature_name = node.value
            best_feature_id = self.feature_names.index(best_feature_name)
            child_value = data[best_feature_id]

            for child in node.childs:
                if child.value == child_value:
                    node = child.next

        if node.isPruned: return node.labelAfterPruned
        return node.value

    def reduced_error_pruning(self, pruning_data, pruning_labels):
        if not self.node:
            return

        print("Operating Reduced Error Pruning: ")
        tree_pruned = True

        while tree_pruned:
            tree_pruned = self._pruning_helper(pruning_data, pruning_labels, self.node)

        print('')

    def _pruning_helper(self, pruning_data, pruning_labels, node):
        if not node: 
            return False
        
        if not node.childs:
            return False

        isBottomRoot = True
        i = 0 
        
        while i < len(node.childs) and isBottomRoot:
            child = node.childs[i] 
            if child.next.childs and not child.next.isPruned:
                isBottomRoot = False
            i += 1

        if isBottomRoot:
            predictions_before_pruning = [self.predict(data) for data in pruning_data]
            acc_before_pruning = evaluation_metric(
            'accuracy', pruning_labels, predictions_before_pruning)

            node.isPruned = True
            predictions_after_pruning = [self.predict(data) for data in pruning_data]
            acc_after_pruning = evaluation_metric(
            'accuracy', pruning_labels, predictions_after_pruning)
            if acc_after_pruning < acc_before_pruning:
                node.isPruned = False
                return False
            else: 
                print('Pruned! Node: ', node.value)
                return True

        ret = False
        for child in node.childs:
            tmp = self._pruning_helper(
                pruning_data, pruning_labels, child.next)
            if tmp: ret=True

        return ret


if __name__ == '__main__':
    X = []
    feature_names = ['Outlook', 'Temp', 'Humidity', 'Wind']
    labels = [0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0]

    clf = Id3Classifier(X=X, feature_names=feature_names, labels=labels)
