import math
from collections import deque
import numpy as np

from util.evaluation import evaluation_metric


class Node:
    '''
        Class Node: 
        A data structure to store the corresponding value, next node pointer, children pointer, isPruned identifier, label value after pruning, and the tree depth. 
    '''
    def __init__(self):
        self.value = None
        self.next = None
        self.childs = None
        self.isPruned = False
        self.labelAfterPruned = None
        self.depth = None


class Id3Classifier:
    '''
        Class Id3Classifier: 
        A Classifier class stores the source data and corresponding labels, a list of feature names. After initialization, the label set and the counts of labels are stored, and based on the label data, entropy is calculated with the use of _calculate_entropy()
    '''

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
        '''
            _calculate_entropy():
            A method to calculate entropy given the range of data to scan

            Input: 
                data_ids: a list of index of the source data to scan

            Output: 
                entropy: a numerical value of entropy
        '''


        labels = [self.labels[i] for i in data_ids]
        label_counts = [labels.count(label) for label in self.labelSet]

        entropy = sum([-count/len(data_ids) * math.log(count /
                                                       len(data_ids), 2) if count else 0 for count in label_counts])

        return entropy

    def _calculate_information_gain(self, data_ids, feature_id):
        '''
            _calculate_information_gain():
            A method to calculate a specific feature’s IG (information gain) given the range of data IDs.

            Input: 
                data_ids: a list of index of the source data to scan
                feature_id: int, an index of feature for the calculation

            Output: 
                info_gain: a numerical value of IG
        '''   
        
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
        '''
            _get_max_ig_feature():
            With the use of _calculate_infromation_gain(), this method calculates IG for each feature, and returns the ID of the feature with the max IG. 

            Input: 
                data_ids: a list of index of the source data to scan
                feature_ids: a list of index of feature for the calculation

            Output: 
                info_gain: a numerical value of IG
        '''   

        information_gains = [self._calculate_information_gain(
            data_ids, feature_id) for feature_id in feature_ids]
        max_ig = max(information_gains)
        max_id = feature_ids[information_gains.index(max_ig)]

        return max_id

    def fit(self):
        '''
            fit():
            A method to fit the data.
        '''

        data_ids = [x for x in range(len(self.X))]
        feature_ids = [x for x in range(len(self.feature_names))]

        self.node = self._fit_helper(data_ids, feature_ids, self.node, 0)

    def _fit_helper(self, data_ids, feature_ids, node, depth):
        '''
            _fit_helper()
            A recursive helper method uses the feature with max IG as the split, and creates child nodes for each distinct value for that feature. Then recursively call itself on this child node, until no more feature or data sample is left for the downstream.

            Input: 
                data_ids: a list of index of the source data  
                feature_ids: A list of index of features
                node: a node pointer where this method expand the tree
                depth: a depth of the next node
        '''

        if not node:
            node = Node()
            node.depth = depth

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
            child.depth = node.depth + 1
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
                    child_data_ids, feature_ids, child.next, depth+1)

        return node

    def print_tree(self):
        '''
            print_tree():
            A printer method to visually show the tree structure.    
        '''

        print("ID3 Tree: ")
        if not self.node:
            return
        nodes = deque()
        nodes.append(self.node)
        while len(nodes) > 0:
            node = nodes.popleft()
            size = node.depth * 4 + 2
            if node.isPruned:
                print(f"{' ' * size} {node.labelAfterPruned}")
            else: 
                
                print(f"{' ' * size} -- {node.value}")
                if node.childs:
                    for child in node.childs:
                        print(f"{' ' * (size)} ({child.value})")
                        nodes.append(child.next)
                    print()
                elif node.next:
                    print(f"{' ' * size} {node.next}")
            
        print()


    def predict(self, data):
        '''
            predict(): 
            A method to traverse the decision tree given the feature values of the datapoint to predict.

            Input: 
                data: a list, representing feature values of one data point
            
            Output: 
                label: a class label for the data predicted

        '''
        if not self.node:
            return

        node = self.node

        tmp = None
        while not node.isPruned and node.childs:
            best_feature_name = node.value
            best_feature_id = self.feature_names.index(best_feature_name)
            child_value = data[best_feature_id]

            tmp = None
            for child in node.childs:
                if child.value == child_value:
                    tmp = child.next

            if not tmp: break
            node = tmp

        
        if not tmp: return None
        if node.isPruned: return node.labelAfterPruned
        return node.value

    def reduced_error_pruning(self, pruning_data, pruning_labels):
        '''
            reduced_error_pruning():
            A method to prune the decision tree given the set of data. 

            Input: 
                pruning_data: 2D list of data samples' feature values 
                pruning_labels: a list of pruning data's label values    
        '''

        if not self.node:
            return

        self._pruning_helper(pruning_data, pruning_labels, self.node)

    def _pruning_helper(self, pruning_data, pruning_labels, node):
        '''
            _pruning_helper(): 
            A recursive helper method, which first traverses the tree to the bottom most parent node, and calculates the accuracies for pre-pruning/post-pruning tree, and if the accuracy is improved, prunes the tree by marking the corresponding node’s identifier isPruned.

            Input: 
                pruning_data: 2D list of data samples' feature values 
                pruning_labels: a list of pruning data's label values
                node: A node pointer to scan 

        '''
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
                print(f'Pruning at node: {node.value}')
                print(f'accuracy before pruning: {acc_before_pruning}')
                print(f'accuracy after pruning: {acc_after_pruning}')               
                return True

        ret = False
        tmp = None
        for child in node.childs:
            if child.next.childs:
                tmp = self._pruning_helper(
                pruning_data, pruning_labels, child.next)
            if tmp: ret=True

        return ret


if __name__ == '__main__':
    X = []
    feature_names = ['Outlook', 'Temp', 'Humidity', 'Wind']
    labels = [0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0]

    clf = Id3Classifier(X=X, feature_names=feature_names, labels=labels)
