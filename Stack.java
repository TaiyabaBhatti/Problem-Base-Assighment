public class Stack {
    Node top;
    int size;

    public Object peek() {
//        if (size==0){
//            throw new IllegalStateException("LinkedList is empty");
//        }
        return 0;
    }

    public Node peekNode() {
        if (size == 0) {
            throw new IllegalStateException("LinkedList is empty");
        }
        return top;
    }

    public Object pop() {
//        if (size==0){
//            throw new IllegalStateException("LinkedList is empty");
//        }
//        Node oldtop=top;
//        top=top.next;
//        size--;
        return 0;
    }

    public Node popTop() {
        if (top == null) {
            throw new IllegalStateException("LinkedList is empty");
        }
        Node oldtop = top;
        top = top.next;
        size--;
        return oldtop;
    }

    public Node popBottom() {
        if (size == 0) {
            throw new IllegalStateException("LinkedList is empty");
        }
        Node current = top;
        while (current.next.next != null) {
            current = current.next;
        }
        Node bottom = current.next;
        current.next = null;
        size--;
        return bottom;
    }

    public void push(Node singleCrop) {

        singleCrop.next = top;
        top = singleCrop;
        size++;
    }








    public int size() {
        return size;
    }

    public void displayStack() {
        Node refvar = top;
        while (refvar != null) {
            System.out.println(refvar.cropName + "\t " + refvar.count);
            refvar = refvar.next;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node temp = top; temp != null; temp = temp.next) {
            sb.append(temp.cropName + ",");
        }
        sb.append("null]");
        return sb.toString();
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void sortStack() {
        if (size <= 1) {
            return; // Stack is already sorted or empty
        }
        Stack sortedStack = new Stack(); // Create a new stack for sorted elements
        while (!isEmpty()) {
            Node temp = popTop(); // Pop the top element of the original stack
            // Pop elements from the sorted stack and push them back into the original stack until
            // the top of the sorted stack is greater than the count of the popped element
            while (!sortedStack.isEmpty() && sortedStack.peekNode().count > temp.count) {
                push(sortedStack.popTop());
            }
            // Push the popped element into the sorted stack
            sortedStack.push(temp);
        }
        while (!sortedStack.isEmpty()) {
            push(sortedStack.popTop());
        }
    }
}
class Node{
    String cropName;
    int count;
    Node next;
    Node(String data,int count){
        this.cropName=data;
        this.count=count;
    }
    Node(String cropName){
        this.cropName=cropName;
    }
}
class Node2{
    String cropName;
    int year;
    Node next;
    Node2(String data,int year){
        this.cropName=data;
        this.year=year;
    }
    Node2(String cropName){
        this.cropName=cropName;
    }
}