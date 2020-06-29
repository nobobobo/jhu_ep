/**
 * Stack class
 */

public class Stack {
    public Node head;

    /**
     * Constructor
     */
    Stack() {
        this.head = null;
    }

    /**
     * check if the stack is empty
     * @return
     */
    public Boolean isEmpty() {
        return this.head == null;
    }

    /**
     * peek method
     * get the data of the top of the stack
     * @return
     */
    public Character peek() {
        if (!isEmpty())
            return this.head.data;
        return null;
    }

    /**
     * push method
     * generate new node object and push to the top of the stack
     * @param ch
     */
    public void push(Character ch) {
        Node node = new Node(ch);
        node.next = this.head;
        this.head = node;
    }

    /**
     * pop method
     * remove and get the top of the stack
     * @return
     */
    public Character pop() {
        if (!isEmpty()) {
            Node node = this.head;
            this.head = this.head.next;
            node.next = null;
            return node.data;
        }
        return null;
    }

    /**
     * main method for testing 
     * expected output: 
     * A
     * C
     * B
     * @param args
     */
    public static void main(String args[]) {
        Stack stack = new Stack();

        System.out.println(stack.isEmpty());

        stack.push('A');
        System.out.println(stack.peek());
        stack.push('B');
        stack.push('C');
        System.out.println(stack.pop());
        System.out.println(stack.peek());

    }
}