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

    public static void main(String[] args) {
        RightInThreadBT myRightInThreadBT = new RightInThreadBT();

        myRightInThreadBT.makeTree("A");
        System.out.println("Data: " + myRightInThreadBT.treeArray.get(0).data);
        System.out.println("leftIdx: " + myRightInThreadBT.treeArray.get(0).leftIdx);
        System.out.println("rightIdx: " + myRightInThreadBT.treeArray.get(0).rightIdx);
        System.out.println("rThread: " + myRightInThreadBT.treeArray.get(0).rThread);
        myRightInThreadBT.setLeft("B", 0);
        System.out.println("Data: " + myRightInThreadBT.treeArray.get(1).data);
        System.out.println("leftIdx: " + myRightInThreadBT.treeArray.get(1).leftIdx);
        System.out.println("rightIdx: " + myRightInThreadBT.treeArray.get(1).rightIdx);
        System.out.println("rThread: " + myRightInThreadBT.treeArray.get(1).rThread);
        myRightInThreadBT.setRight("C",0);
        System.out.println("Data: " + myRightInThreadBT.treeArray.get(2).data);
        System.out.println("leftIdx: " + myRightInThreadBT.treeArray.get(2).leftIdx);
        System.out.println("rightIdx: " + myRightInThreadBT.treeArray.get(2).rightIdx);
        System.out.println("rThread: " + myRightInThreadBT.treeArray.get(2).rThread);
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