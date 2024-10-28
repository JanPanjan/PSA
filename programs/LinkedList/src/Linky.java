public class Linky {
    Node head;

    public boolean insert(int data) {
        if (head == null) {
            head = new Node(data);
            return true;
        }
        if (head.next == null) {

        }
    }

    public boolean find(int data) {
        return head.find(data);
    }

    public void printLinky(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }

        Node current = head;
        System.out.println(current.data + "-");
        if (current.next == null) {
            System.out.println("null");
            current = current.next;
        } else {
            printLinky(current.next);
        }
    }
}
