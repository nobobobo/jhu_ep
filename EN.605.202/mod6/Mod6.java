public class Mod6 {
    public static void main(String args[]) {
        int arrSize = 10;
        Hybrid hyb = new Hybrid(arrSize);
        // HybridQueue queue1 = new HybridQueue(hyb);
        // HybridQueue queue2 = new HybridQueue(hyb);
        // queue1.add(45);
        // queue2.add(32);
        HybridStack stack1 = new HybridStack(hyb);
        HybridStack stack2 = new HybridStack(hyb);
        stack1.push(1);
        stack2.push(3);
        //queue2.add(21);
        stack1.push(8);
        stack2.push(5);
        stack2.push(4);
        stack1.push(2);
        //queue2.remove();
        stack2.pop();
        printArr(stack1.hyb);

    }

    public static void printArr(Hybrid hyb) {
        for (int i = 0; i < 10; i++) {
            System.out.println(hyb.arr[i][0] + ", " + hyb.arr[i][1]);
        }
    }
}

class Hybrid {
    public int[][] arr;

    Hybrid(int size) {
        this.arr = new int[size][2];
        for (int i = 0; i < size; i++) {
            this.arr[i][1] = -1;
        }
    }
}

class HybridQueue {
    public int head;
    public int tail;
    public Hybrid hyb;

    HybridQueue(Hybrid hyb) {
        this.hyb = hyb;
        this.head = -1;
        this.tail = -1;
    }

    public void add(int item) {
        if (isFull()) return;
        for (int i = 0; i < this.hyb.arr.length; i++) {
            if (this.hyb.arr[i][1] == -1) {
                if (this.head == -1) {
                    this.head = i;
                } else {
                    this.hyb.arr[tail][1] = i;
                }
                this.tail = i;
                this.hyb.arr[tail][0] = item;
                this.hyb.arr[tail][1] = -2;
                break;
            }
        }
    }

    public int remove() {
        if (!isEmpty()) {
            int tmp = this.hyb.arr[this.head][0];
            int nextHead = this.hyb.arr[this.head][1];
            this.hyb.arr[this.head][0] = 0;
            this.hyb.arr[this.head][1] = -1;
            this.head = nextHead;
            return tmp;
        } else {
            return 0;
        }
    }

    public int peek(){
        return this.hyb.arr[this.head][0];
    }

    public boolean isEmpty() {
        return this.head == -1;
    }

    public boolean isFull(){
        for (int i =0 ;i < this.hyb.arr.length; i++){
            if (this.hyb.arr[i][1]== -1){
                return false;
            } 
        }
        return true;
    }

}

class HybridStack {
    public int top;
    public Hybrid hyb;

    HybridStack(Hybrid hyb) {
        this.hyb = hyb;
        this.top = -1;
    }

    public void push(int item) {
        if (isFull()) return;
        for (int i = 0; i < this.hyb.arr.length; i++) {
            if (this.hyb.arr[i][1] == -1) {
                if (this.top == -1) {
                    this.hyb.arr[i][1] = -2;
                } else {
                    this.hyb.arr[i][1] = this.top;
                }
                this.hyb.arr[i][0] = item;
                this.top = i;
                break;
            }
        }
    }

    public int pop() {
        if (!isEmpty()) {
            int tmp = this.hyb.arr[this.top][0];
            this.hyb.arr[this.top][0] = 0;
            this.hyb.arr[this.top][1] = -1;
            this.top = this.hyb.arr[this.top][1];
            return tmp;
        } else {
            return 0;
        }
    }

    public int peek(){
        return this.hyb.arr[this.top][0];
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public boolean isFull(){
        for (int i =0 ;i < this.hyb.arr.length; i++){
            if (this.hyb.arr[i][1]== -1){
                return false;
            } 
        }
        return true;
    }
}
