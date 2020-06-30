public class Node{
    public Character data;
    public Node next;

    Node(){
        this.data = null;
        this.next = null;
    }

    Node(Character ch){
        this.data = ch;
        this.next = null;
    }

    public static void main(String args[]){
        Node nodeA = new Node('A');
        Node nodeB = new Node('B');
        nodeA.next = nodeB;
        System.out.println(nodeA.data);
        System.out.println(nodeA.next.data);
    }
}