package psa.naloga3;

public class NodeSkipList {
    public int key;              // key v node
    public NodeSkipList[] next;  // kazalci na sezname v nižjih nivojih

    /** e.g. ustvari node z 9 in 4 nivoji => next = {0,1,2,3} */
    public NodeSkipList(int key, int levels) {
        this.key = key;
        this.next = new NodeSkipList[levels];
    }
}
