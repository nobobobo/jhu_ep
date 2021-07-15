import numpy as np 

class Node: 

    def __init__(self):
        self.value = None
        self.next = None
        self.left = None
        self.right = None 
        

class CARTRegressor:

    def __init__(self, X, feature_names, y):
        self.X = X 
        self.feature_names = feature_names
        self.y = y 

        self.node = None

        init_data_ids = [x for x in range(len(self.y))]
        self.mse = self._calc_mse(init_data_ids)

        
    def _calc_mse(self, data_ids):

        ytrue = [self.y[i] for i in data_ids]
        yhat = sum(ytrue) / len(ytrue)

        mse = sum([(y - yhat)**2 for y in ytrue]) / len(ytrue)

        return mse

    def _get_midpoints(self, feature_vals):
        midpoints = [] 
        if len(feature_vals) == 0: return midpoints
        if len(feature_vals) == 1: return feature_vals

        for i in range(len(feature_vals)-1):
            midpoints.append((feature_vals[i]+ feature_vals[i+1])/2)

        return midpoints

    def _get_best_feature(self, data_ids, feature_ids):
        mse_base = self.mse
        best_feature_id = None 
        best_feature_val = None

        for feature_id in feature_ids:
            feature_vals = [self.X[i][feature_ids] for i in data_ids].sort()
            midpoints = self._get_midpoints(feature_vals)

            for midpoint in midpoints: 
                left_data_ids = [i for i in data_ids if self.X[i][feature_id] < midpoint]
                right_data_ids = [i for i in data_ids if self.X[i][feature_id] >= midpoint]

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
        data_ids = [x for x in range(len(self.X))]
        feature_ids = [x for x in range(len(self.feature_names))]

        self.node = None 

    






