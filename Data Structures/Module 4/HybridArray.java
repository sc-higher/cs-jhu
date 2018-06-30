public class HybridArray{

    private int size;
    Node[] data = new Node[size];
    Stack free = new Stack(size);

    public HybridArray(int size){
        this.size = size;
        for(int i = 0; i < size; i++){
            free.push(i);
        }
    }

    // Implement stack as an array
    private class Stack{
        private int arr[];
        private int size;
        private int top = 0;

        private Stack(int size){
            this.size = size;
            arr = new int[size];
        }

        private void push(int element){
            if(top == size) {
                System.out.println("Invalid.");
            }
            else {
                arr[top] = element;
                top++;
            }
        }

        private int pop(){
            if (isEmpty()){
                System.out.println("Invalid.");
            }
            return arr[top-1];
        }

        private boolean isEmpty(){
            if (top == 0) {
                return true;
            }
            return false;
        }
    }

    // Implement node to hold data and pointer
    private class Node{
        int data;
        int next;

        private Node(int data, int next){
            this.data = data;
            this.next = next;
        }
    }


    public int freeIndex(){
        if(free.isEmpty()){
            System.out.println("Invalid.");
        }
        return free.pop();
    }

    public void returnFree(int index){
        free.push(index);
    }

    // Stack methods
    public void push(int data, int next){
        int freeIndex = free.pop();
        Node node = new Node(data, 0)
        data[freeIndex] = node;
    }

    public Node pop(){
        if(data.isEmpty()){
            throw exception;
        }
        node = startNode
        while(node.next != 0){
            prevNode = node;
            node = data[node.next];
        }
        data[prevNode].next = 0; // set pointer of new last item to 0
        returnFree(node);
        return data[node];
    }

    // Queue methods
    public void add(int data, int next){
        int freeIndex = free.pop();
        Node node = new Node(data, 0)
        data[freeIndex] = node;
    }

    public void remove(){
        temp = startNode;
        startNode = startNode.next;
        returnFree(temp);
        return data[temp];
    }

}