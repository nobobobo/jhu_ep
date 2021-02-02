from __future__ import print_function

class Node: 
    def __init__(self, data=None):
        self.data = data
        self.next = None

class LinkedList: 
    def __init__(self, head_node=None):
        # set 2 pointers: head and last
        self.head = head_node   
        self.tail = head_node

if __name__ == '__main__':

    with open("iris.csv") as file: 
        
        file.readline() # skip header line

        # initialize a linked list with its head node, O(1)
        head_val = file.readline().strip().split(',')[0]
        head_node = Node(head_val)
        list = LinkedList(head_node)

        # read rest of the lines from the file, 
        # create a new node and link to the tail of the linked list, 
        # Cost of read, store, and link is O(1) per each element

        for line in file:
            next_val = line.strip().split(',')[0]
            next_node = Node(next_val)
            list.tail.next = next_node
            list.tail = list.tail.next

    # scan through the linked list from the head node for testing. 
    # Output:    5.1 -> 4.9 -> 4.7 -> 4.6 -> 5.0 -> 5.4 -> 4.6 -> 5.0 -> 4.4 -> 4.9 -> 5.4 -> 4.8 -> ... 
    nd = list.head
    while nd != None: 
        print(nd.data,end=" -> ")
        nd = nd.next      

# As mentioned above, inside the loop of read lines, cost of read, store and link is O(1) per element. 
# so for reading in the data, storing the keys and linking the feature data has total running time is O(n) 

