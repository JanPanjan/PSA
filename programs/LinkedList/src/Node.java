public class Node {
    int data;
    Node next;

    public Node(int val) {
        this.data = val;
        next = null;
    }

    public boolean find(int data) {
        // empty or end of list
        if (this == null) {
            return false;
        }
        // found data
        if (this.data == data) {
            return true;
        }
        // get next node
        return next.find(data);
    }
}