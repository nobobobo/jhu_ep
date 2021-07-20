import numpy as np


class CARTRegressorNode:
    '''
        Class CART Regressor Node:
        A class of regression trees, stores feature data, X and target values y, with other info: features_names, node_type, depth, a string rule, and stopping threshold value. 
        
        Within initialization: 
        A root node is created with depth 0, 
        Mean value of y (as the predicted value at the root node), and MSE are calculated,
        pointers to the left and the right trees are initialized.
        Other supportive values (split_feature_id, split_feature_val, data length n) are initialized.

        Input: 
            X: 2D list, data values 
            y: a list of target feature values 
            feature_names: a list of feature names
            node_type: an identifier of the node type, possible values: root, Left Node, Right Node
            depth: a depth of corresponding node
            rule: a string to explain the split rule 
            stopping_threshold: a numerical hyperparameter for early-stopping
    '''

    def __init__(self, X, y, feature_names, node_type=None, depth=None, rule=None, stopping_threshold=None):
        self.X = [x for x in X]
        self.feature_names = feature_names
        self.y = y

        self.node_type = node_type if node_type else 'root'

        self.y_mean = sum(y) / len(y)
        self.mse = self._calc_mse(y, self.y_mean)

        self.n = len(y)
        self.depth = depth if depth else 0

        self.left = None
        self.right = None

        self.split_feature_id = None
        self.split_feature_value = None
        self.split_rule = rule if rule else ""

        self.stopping_threshold = stopping_threshold if stopping_threshold else 0

    def _calc_mse(self, y_true, y_hat):
        '''
            _calc_mse():
            A method to calculate Mean Square Error given a list of y values and y hat value. 

            Input: 
                y_true: a list of y values 
                y_hat: a numerical value of y prediction

            Output: 
                mse: A mean square error value     
        '''

        mse = sum([(y - y_hat)**2 for y in y_true]) / len(y_true)

        return mse


    def _get_midpoints(self, feature_vals):
        '''
            _get_midpoints():
            This method returns a list of values representing the midpoints of the unique values for the feature pointed in the input argument.

            Input: 
                feature_vals: a list of distinct feature values 

            Output: 
                midpoints: a list of midpoint values 
        '''
        midpoints = []
        if len(feature_vals) == 0:
            return midpoints
        if len(feature_vals) == 1:
            return feature_vals

        for i in range(len(feature_vals)-1):
            midpoints.append((feature_vals[i] + feature_vals[i+1])/2)

        return midpoints


    def _get_best_split(self):
        '''
            _get_best_split():
            A method to get the best split by calculating midpoints for each feature, then splitting the data and calculating MSE after the split. If the MSE is improved, return the index number and value of the best split feature.

            output: 
            best_feature_id: a index of the feature to split 
            best_feature_value: a value of the split
        '''
        mse_base = self.mse
        best_feature_id = None
        best_feature_val = None

        if mse_base < self.stopping_threshold: 
            print(f'Early Stopped at: {self.split_rule}')
            print(f'Base MSE: {mse_base}')
            print(f'Threshold: {self.stopping_threshold}')
            return None, None

        for feature_id in range(len(self.feature_names)):
            feature_vals = list(set([x[feature_id] for x in self.X]))
            feature_vals.sort()
            midpoints = self._get_midpoints(feature_vals)

            for midpoint in midpoints:
                left_data_ids = [i for i in range(
                    len(self.X)) if self.X[i][feature_id] < midpoint]
                right_data_ids = [i for i in range(
                    len(self.X)) if self.X[i][feature_id] >= midpoint]

                left_y = np.array([self.y[i] for i in left_data_ids])
                right_y = np.array([self.y[i] for i in right_data_ids])

                left_mean = np.mean(left_y)
                right_mean = np.mean(right_y)

                left_res = left_y - left_mean
                right_res = right_y - right_mean

                res = np.concatenate((left_res, right_res))

                mse_split = np.average(res**2)

                if mse_split < mse_base:
                    best_feature_id = feature_id
                    best_feature_val = midpoint

                    mse_base = mse_split

        return best_feature_id, best_feature_val


    def fit(self):
        '''
            fit():
            This method recursively operates tree splitting and calls itself on the left and the right node, until no more split is operated by _get_best_split()
        '''

        best_feature_id, best_feature_val = self._get_best_split()

        if best_feature_id:
            self.split_feature_id = best_feature_id
            self.split_feature_value = best_feature_val
            best_feature_name = self.feature_names[best_feature_id]

            new_feature_names = [x for x in self.feature_names]
            del new_feature_names[self.split_feature_id]

            left_ids = [i for i in range(
                len(self.X)) if self.X[i][self.split_feature_id] < self.split_feature_value]
            right_ids = [i for i in range(
                len(self.X)) if self.X[i][self.split_feature_id] >= self.split_feature_value]

            left_X = [self.X[i][:best_feature_id] + self.X[i][best_feature_id + 1:]  for i in left_ids]
            right_X = [self.X[i][:best_feature_id] + self.X[i][best_feature_id + 1:] for i in right_ids]

            left_y = [self.y[i] for i in left_ids]
            right_y = [self.y[i] for i in right_ids]

            left_node = CARTRegressorNode(left_X, left_y, new_feature_names, node_type='Left Node', depth=self.depth+1,
                                          rule=f'{best_feature_name} < {round(best_feature_val, 2)}', stopping_threshold=self.stopping_threshold)
            self.left = left_node
            self.left.fit()

            right_node = CARTRegressorNode(right_X, right_y, new_feature_names, node_type='Right Node', depth=self.depth+1,
                                           rule=f'{best_feature_name} >= {round(best_feature_val, 2)}',stopping_threshold=self.stopping_threshold)
            self.right = right_node
            self.right.fit()


    def print_info(self):
        '''
            print_info(): 
            A method to print node's Split rule, MSE, # of observations and Prediction
        '''
        size = self.depth * 8 + 2
        spaces = "-" * size

        if self.node_type == 'root':
            print("Root")
        else:
            print(f"|{spaces} Split Rule: {self.split_rule}")
        print(f"{' ' * size}   | MSE: {round(self.mse, 2)}")
        print(f"{' ' * size}   | # of observations: {self.n}")
        print(f"{' ' * size}   | Prediction: {round(self.y_mean, 2)}")


    def print_tree(self):
        '''
            print_tree():
            A recursive function to traverse the tree and display the information by calling print_info()
        '''
        self.print_info()

        if self.left is not None:
            self.left.print_tree()

        if self.right is not None:
            self.right.print_tree()


    def predict(self, data): 
        '''
            predict():
            Given the feature values of one data point, this tree traverses through the regression tree, and returns the prediction.

            Input: 
            data: a list of feature values

            Output: 
            prediction: a numerical prediction based on the input feature values
        '''
        node = self 

        while node.left and node.right:
            val = data[node.split_feature_id]
            del data[node.split_feature_id]
            if val < node.split_feature_value:
                node = node.left
            else: 
                node = node.right

        return node.y_mean
