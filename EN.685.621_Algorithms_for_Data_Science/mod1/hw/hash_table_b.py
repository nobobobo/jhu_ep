from linked_list import Node

class HashTable:
    def __init__(self, slot_size=12):
        # store size of the hash table
        self.slot_size = slot_size
        # store a list as a hash table 
        self.hash_table = [None] * slot_size

    def hash_and_store(self, ob_id, node):
        # division method to hash the key: observation id
        slot = ob_id % self.slot_size
        node.next = self.hash_table[slot] 
        self.hash_table[slot] = node


if __name__ == '__main__':

    with open("iris.csv") as file: 
        
        file.readline() # skip header line
        ob_id = 0
        h = HashTable()

        # read rest of the lines from the file, 
        for line in file:
            # read and initialize a node
            # different from Part a, the list of 4 features is stored as data of the node
            data = line.strip().split(',')[0:4]
            next_node = Node(data)

            # hash the element with its key and store in a hash table
            h.hash_and_store(ob_id, next_node)

            # increment the key
            ob_id = ob_id + 1 

