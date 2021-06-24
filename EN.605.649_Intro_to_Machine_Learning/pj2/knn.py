import math

def euclidian_distance(p1, p2):
    ret = 0.0
    for i in range(len(p1)-1):
        ret += (p1[i] - p2[i])**2
    
    return math.sqrt(ret)

def plurality_vote(neighbors):
    data = [neighbor["data"] for neighbor in neighbors]
    classes = [row[-1] for row in data]
    
    return max(set(classes), key= classes.count)

def rbf_kernel(neighbors, sigma): 
    pred = 0 

    for neighbor in neighbors:
        kernel = math.exp(neighbor["distance"]**2/(2*sigma**2)*-1)
        pred += kernel * neighbor["data"][-1]
    
    return pred


def get_neighbors(train_data, test_p, k):
    distances = []
    for p in train_data:
        dist = euclidian_distance(p, test_p)
        distances.append({"data":p, "distance":dist})
    
    distances.sort(key=lambda dict: dict["distance"])

    neighbors = []
    for i in range(k):
        neighbors.append(distances[i])
    return neighbors


def predict_classification(train_data, test_p, k):
    neighbors = get_neighbors(train_data, test_p, k)
    pred = plurality_vote(neighbors)

    return pred


def predict_regression(train_data, test_p, k, sigma):
    neighbors = get_neighbors(train_data, test_p, k)
    pred = rbf_kernel(neighbors, sigma)

    return pred


def knn(train_data, test_data, k, isClassification, sigma=0.001):
    predictions = []
    for test_p in test_data:
        if isClassification:
            pred = predict_classification(train_data, test_p, k)
        else: 
            pred = predict_regression(train_data, test_p, k, sigma)
        
        predictions.append(pred)
    return predictions


if __name__ == '__main__':
    dataset = [[2.7810836,2.550537003,0],
	[1.465489372,2.362125076,1],
	[3.396561688,4.400293529,2],
	[1.38807019,1.850220317,3],
	[3.06407232,3.005305973,4],
	[7.627531214,2.759262235,5],
	[5.332441248,2.088626775,6],
	[6.922596716,1.77106367,7],
	[8.675418651,-0.242068655,8],
	[7.673756466,3.508563011,9]]

    for sigma in [100, 10, 1, 0.5, 0.1, 0.05, 0.01, 0.001]:
        pred = predict_regression(dataset, dataset[3], 3, sigma)
        print('Sigma: ', sigma, ', Actual: 3, Prediction: ', pred)