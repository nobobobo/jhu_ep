from __future__ import print_function

class Node: 
    def __init__(self, data=None):
        self.data = data
        self.next = None

class Stack: 
    def __init__(self, top_node=None):
        # set a pointer: top for top of the stack
        self.top = top_node   

if __name__ == '__main__':

    with open("iris.csv") as file: 
        
        file.readline() # skip header line

        # initialize a stack with its top node, O(1)
        top_val = file.readline().strip().split(',')[0]
        top_node = Node(top_val)
        stack = Stack(top_node)

        # read rest of the lines from the file, 
        # create a new node and link the stack's top to its next
        # then change the pointer to point the new node
        # Cost of read, store, and stack is O(1) per each element
        for line in file:
            next_val = line.strip().split(',')[0]
            next_node = Node(next_val)
            next_node.next = stack.top
            stack.top = next_node

    # scan through the stack by popping out the top node from it
    # Output: 5.9 -> 6.2 -> 6.5 -> 6.3 -> 6.7 -> 6.7 -> 6.8 -> 5.8 -> 6.9 ->. .. 

    while stack.top != None: 
        print(stack.top.data,end=" -> ")
        # pop action
        stack.top = stack.top.next    

# As mentioned above, inside the loop of read lines, cost of read, store, and stack is O(1) per each element
# so the total running time is O(n) 

