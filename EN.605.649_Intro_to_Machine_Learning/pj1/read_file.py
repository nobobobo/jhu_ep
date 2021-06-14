import csv
import math

def is_number(s):
    try: 
        float(s)
        return True
    except ValueError: 
        return False

def read(file_path, col_headers=[], log_trsfm_cols =[]):

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