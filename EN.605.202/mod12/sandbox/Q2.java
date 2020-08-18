public class Q2 {
    static class BNode{
        private int cnt;
        private int[] keys;
        private BNode[] children;
        private boolean leaf;

        BNode(int n, BNode parent){
            this.cnt = 0; // count of keys 
            this.keys = new int[n-1]; // array list of keys
            this.children = new BNode[n]; // array list of children (BNode)
            this.leaf = true; // boolean for leaf node or not
            this.parent = parent; // a pointer to parent node
        }

    }

    static class BTree{
        
        private int order; // order of BTree
        private BNode head; // a pointer for BNode head

        BTree(int order){
            this.order = order;
            this.head = new BNode(order, null);
        }
    }

    public BNode search(BNode root, int key){
        int i = 0;
        while (i < root.cnt && root.keys[i] < key ){
            i++;
        }
        if (i<= root.cnt && root.keys[i] == key){
            return root;
        }

        if (root.leaf){
            return null;
        } else {
            return search(root.children[i], key);
        }
    }

    public void deleteKey(BTree root, int key){
        BNode tmp = search(root.head, key);

        // deletion occcurs at leaf node
        if (tmp.leaf) {
            int i = 0;

            // searching through keys
            while (tmp.keys[i] < key){
                i++;
            }

            while (i < tmp.cnt){
                tmp.keys[i] = tmp.keys[i+1];
                i++;
            }
            tmp.cnt--;
        } else {
            // deletion at non-leaf BNode
        }
    }
}