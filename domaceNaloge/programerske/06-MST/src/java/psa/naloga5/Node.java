package psa.naloga5;

/*
 * Vsak node ima utež
 */
public class Node {
    public int w;
    public Node p;

    public Node(int w) {
        this.w = w;
        this.p = null;
    }
}