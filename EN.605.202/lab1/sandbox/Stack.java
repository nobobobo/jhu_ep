public class Stack {
    public Node head;

    Stack() {
        this.head = null;
    }

    public Boolean isEmpty() {
        return this.head == null;
    }

    public Character peek() {
        if (!isEmpty())
            return this.head.data;
        return null;
    }

    public void push(Character ch) {
        Node node = new Node(ch);
        node.next = this.head;
        this.head = node;
    }

    public Character pop() {
        if (!isEmpty()) {
            Node node = this.head;
            this.head = this.head.next;
            node.next = null;
            return node.data;
        }
        return null;
    }

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