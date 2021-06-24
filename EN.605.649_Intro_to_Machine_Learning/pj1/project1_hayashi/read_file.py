import csv
import math

def is_number(s):
    '''
    function is check the field can be converted to a number (float) or not
    Input: 
        s: input string 
    '''
    try: 
        float(s)
        return True
    except ValueError: 
        return False

def read(file_path, col_headers=[], log_trsfm_cols =[]):

    '''
    csv reader function with the feature of assigning column headers & specifying columns index to perform log transformation
    Input: 
        file_path: path string for the input file
        col_headers: string list of column names
        log_trsfm_cols: index list of column index to perform log transformation

    Output: 
        l: 2D list of data (dim: # of sample x features of the sample)
    '''
    with open(file_path) as f:
        reader = csv.reader(f)
        l = []
        if len(col_headers) > 0: 
            l = [col_headers]

        for row in reader:
            if len(log_trsfm_cols) > 0: 
                for col in log_trsfm_cols:
                    row[col] = math.log(float(row[col]))

            for i in range(len(row)):
                if is_number(row[i]):
                    row[i] = float(row[i])

            l = l + [row]

    return l


if __name__ == '__main__':
    csv_path = '../datasets/car.data'
    data = read(csv_path)
    print(data[:10])