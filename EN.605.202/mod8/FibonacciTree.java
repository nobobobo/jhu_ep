public class FibonacciTree{
    public Node fibTree(int n){
        if (n == 0 || n == 1){
            return new Node(n);
        }
        Node head = new Node(n);
        head.left = fibTree(n-1);
        head.right = fibTree(n-2);
        return head;
    }

}

class Node{ 
    int data;
    Node left;
    Node right;

    Node(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}