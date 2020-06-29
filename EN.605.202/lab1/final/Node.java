
/**
 * Node class
 */
public class Node{
    public Character data;
    public Node next;

    /**
     * constructor
     */
    Node(){
        this.data = null;
        this.next = null;
    }

    /**
     * initializer with given character ch
     * @param ch
     */
    Node(Character ch){
        this.data = ch;
        this.next = null;
    }

    /**
     * Main method for testing 
     * expect out put: 
     * A
     * B
     * @param args
     */
    public static void main(String args[]){
        Node nodeA = new Node('A');
        Node nodeB = new Node('B');
        nodeA.next = nodeB;
        System.out.println(nodeA.data);
        System.out.println(nodeA.next.data);
    }
}