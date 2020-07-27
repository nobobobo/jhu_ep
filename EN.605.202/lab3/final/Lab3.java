import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab3 {
    /**
     * main method: 
     * read input text and parse the matrix as a linked list
     * then call calcDet to calculate its determinant
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException  {
        File text = new File("./Lab3RequiredInput.txt");
        Scanner sc = new Scanner(text);

        int matCnt = 0;
        while (sc.hasNextLine()) {
            matCnt++;
            int order = sc.nextInt();
            sc.nextLine();
            Node mat = new Node();
            System.out.println("Matrix " + matCnt + ": ");
            System.out.println("---------");
            for (int i = 0; i < order; i++) {
                for (int j = 0; j < order; j++) {
                    mat.add(new Node(sc.nextInt()));
                }
                sc.nextLine();
            }

            mat = mat.getNext();
            System.out.println(mat.toString(order));

            System.out.println("determinant: " + calcDet(mat, order));
            System.out.print("\n\n");

        }

    }

    /**
     * Recursive function takes matrix and its order 
     * to calculate the determinant of the matrix. 
     * @param mat
     * @param order
     * @return determinant
     */
    public static int calcDet(Node mat, int order) {
        if (order == 1) {
            return mat.getData();
        }

        int sign = 1;
        int det = 0;

        Node node = mat;
        int skipAt = 0;

        while (skipAt != order) {
            det += sign * node.getData() * calcDet(mat.reduce(skipAt, order), order - 1);
            sign *= -1;
            node = node.getNext();
            skipAt++;
        }
        return det;
    }
}

/**
 * Node class: a linked list object representing a matrix
 * the node with next pointer that traverse the matrix 
 * from left to right, and top to bottom.
 */
class Node {
    private int data;
    private Node next;

    /** 
     * constructor
     */
    Node() {
        this.data = 0;
        this.next = null;
    }

    /**
     * constructor with data
     * @param data
     */
    Node(int data) {
        this.data = data;
        this.next = null;
    }

    /**
     * setter method for next
     * @param next
     */
    public void setnext(Node next) {
        this.next = next;
    }

    /** 
     * Getter method for data
     */
    public int getData() {
        return this.data;
    }

    /**
     * Getter method for next node
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * Takes an order of the matrix as the argument
     * format the matrix as string
     * @param order
     * @return
     */
    public String toString(int order) {
        Node trav = this;
        String res = "";
        int cnt = 0;
        while (trav != null) {
            res += trav.data + " ";
            trav = trav.next;
            cnt++;
            if (cnt % order == 0) {
                res += "\n";
            }
        }
        return res;
    }

    /**
     * Add a new node to the tail
     * @param node
     */
    public void add(Node node) {
        Node trav = this;
        while (trav.next != null) {
            trav = trav.next;
        }
        trav.next = node;
    }

    /**
     * Get a node with index starts with 0 
     * @param index
     * @return
     */
    public Node get(int index) {
        Node trav = this;
        while (index != 0) {
            trav = trav.next;
            index--;
        }
        return trav;
    }

    /**
     * reduce matrix with the info of which vertical row to skip and the order of the matrix
     * @param skipAt
     * @param order
     * @return
     */
    public Node reduce(int skipAt, int order) {
        Node res = new Node();
        Node trav = this.get(order);
        int indexCnt = order;

        while (trav != null) {
            if ((indexCnt - skipAt) % order != 0) {
                res.add(new Node(trav.data));
            }
            trav = trav.next;
            indexCnt++;
        }
        return res.next;
    }
}