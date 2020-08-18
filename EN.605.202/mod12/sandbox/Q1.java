public class Q1 {
    static class Node {
        private int data;
        private Node left;
        private Node right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    static Node findLeftMost(Node root){
        // helper function to traverse

        if (root == null){
            return null;
        }

        while (root.left!= null){
            root = root.left;
        }
        return root;
    }

    static Node deleteNode(Node root){
        // node with only one child or none
        if (root.left == null){
            Node child = root.right;
            root = null;
            return child;
        } else if (root.right == null){
            Node child = root.left;
            root = null;
            return child;
        }

        // Node with two children, get inorder successor in the right sub-tree
        Node next = findLeftMost(root.right);
        root.data = next.data;
        root.right = deleteNode(root.right);

        // return root pointer
        return root;
    }

    static Node delete(Node root, int key1, int key2){
        // base
        if (root == null){
            return null;
        }

        // traverse BST
        root.left = delete(root.left, key1, key2);
        root.right = delete(root.right, key1,key2);

        // once root's data is in the range, call deletenode
        if (root.data >= key1 && root.data<= key2){
            return deleteNode(root);
        }

        return root;
    }

    public static void main(String[] args) {
        Node bst = new Node(4);
        bst.left = new Node(2);
        bst.right = new Node(6);
        bst.left.left = new Node(1);
        bst.left.right = new Node(3);
        bst.right.left = new Node(5);
        bst.right.right = new Node(7);

    }

}