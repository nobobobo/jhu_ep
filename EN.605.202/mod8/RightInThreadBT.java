import java.util.*;
public class RightInThreadBT {
    public int rootPointer;
    public ArrayList<TreeNode> treeArray;

    RightInThreadBT(){
        this.rootPointer = 0;
        this.treeArray = new ArrayList<TreeNode>();
    }

    public void makeTree(String data){
        this.treeArray.add(new TreeNode(data, -1, -1, false));
    }

    public void setLeft(String data, int nodeIdx){
        if (nodeIdx >= this.treeArray.size()) return;
        TreeNode pNode = this.treeArray.get(nodeIdx);
        if (pNode.leftIdx != -1) return;

        TreeNode lNode = new TreeNode(data, -1, nodeIdx, true);
        int newIdx = this.treeArray.size();
        this.treeArray.add(lNode);
        pNode.leftIdx = newIdx;
    }

    public void setRight(String data, int nodeIdx){
        if (nodeIdx >= this.treeArray.size()) return;
        TreeNode pNode = this.treeArray.get(nodeIdx);
        if (pNode.rightIdx != -1 && !pNode.rThread) return;

        int sucIdx = pNode.rightIdx;
        int newIdx = this.treeArray.size();

        TreeNode rNode = new TreeNode(data, -1, sucIdx, true);
        this.treeArray.add(rNode);
        pNode.rightIdx = newIdx;
        pNode.rThread = false;
    }

    public void traverse(){
        TreeNode node = this.treeArray.get(this.rootPointer);
        Stack<String> visited = new Stack<String>();

        while (node.rightIdx != -1 || !node.rThread){
            // if the node is not rThread, check the node is visited or not
            // if not visited, traverse left child
            // if visited, visit the current node and traverse right child
            if (!node.rThread){
                if (visited.isEmpty() || visited.peek() != node.data){
                    visited.push(node.data);
                    node = this.treeArray.get(node.leftIdx);
                } else {
                    System.out.print(visited.pop());
                    node = this.treeArray.get(node.rightIdx);
                }

            // if the node is rThread, the node is a leaf
            // so access the data and traverse to the rightIdx(in-order successor)
            } else {
                System.out.print(node.data);
                node = this.treeArray.get(node.rightIdx);
            }
        }

        // access last element
        System.out.println(node.data);

    }

    public static void main(String[] args) {
        RightInThreadBT myRightInThreadBT = new RightInThreadBT();

        myRightInThreadBT.makeTree("A");
        myRightInThreadBT.setLeft("B",0);
        myRightInThreadBT.setRight("C",0);
        myRightInThreadBT.setLeft("D",1);
        myRightInThreadBT.setRight("E",1);
        myRightInThreadBT.setLeft("F",2);
        myRightInThreadBT.setRight("G",2);

        myRightInThreadBT.traverse();
    }
}

class TreeNode {
    public String data;
    public int leftIdx;
    public int rightIdx; 
    public boolean rThread;

    TreeNode(String data, int leftIdx, int rightIdx, boolean rThread){
        this.data = data;
        this.leftIdx = leftIdx;
        this.rightIdx = rightIdx;
        this.rThread = rThread;
    }
}