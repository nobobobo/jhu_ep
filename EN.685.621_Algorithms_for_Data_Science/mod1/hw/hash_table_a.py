from linked_list import Node, LinkedList

class HashTable:
    def __init__(self, slot_size=12):
        # store size of the hash table
        self.slot_size = slot_size
        # store a list as a hash table 
        self.hash_table = [None] * slot_size

    def hash_and_store(self, ob_id, node):
        # division method to hash the key: observation id, O(1)
        slot = ob_id % self.slot_size

        # link the head of the list next to the new node, O(1)
        node.next = self.hash_table[slot] 
        # and change the head pointer to the new node, O(1)
        self.hash_table[slot] = node


if __name__ == '__main__':

    with open("iris.csv") as file: 
        
        file.readline() # skip header line
        ob_id = 0
        h = HashTable()

        # read rest of the lines from the file, 
        for line in file:
            # read and initialize a node
            data = line.strip().split(',')[0]
            next_node = Node(data)

            # hash the element with its key and store in a hash table
            h.hash_and_store(ob_id, next_node)

            # increment the key
            ob_id = ob_id + 1 

    # As mentioned above, hash_and_store method is O(1)
    # and this method is operated at each line,
    # and given reading one line cost constant time O(1)
    # The overall time to read, hash and store is O(1) * n = O(n)

