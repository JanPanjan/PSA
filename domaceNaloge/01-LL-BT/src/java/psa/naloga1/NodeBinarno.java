package psa.naloga1;

import org.w3c.dom.Node;

public class NodeBinarno {
    private static int counter;
    private int key;
    private NodeBinarno levi;
    private NodeBinarno desni;

    public NodeBinarno(int element) {
        this.key = element;
        this.levi = null;
        this.desni = null;
    }

    public void setLevi(NodeBinarno node) {
        this.levi = node;
    }

    public void setDesni(NodeBinarno node) {
        this.desni = node;
    }

    public NodeBinarno getLevi() {
        return levi;
    }

    public NodeBinarno getDesni() {
        return desni;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int newKey) {
        this.key = newKey;
    }

    public boolean insert(NodeBinarno node) {
        // če je v drevesu, ne vstavljamo
        if (this.compare(node) == 0) {
            return false;
        }

        // če je manjši gremo levo
        if (this.compare(node) < 0) {
            // če je levo null, vstavimo novi element
            if (this.levi == null) {
                this.levi = node;
                return true;
            }

            this.levi.insert(node);
        }
        // če je večji gremo desno
        if (this.compare(node) > 0) {
            // če je desno null, vstavimo novi element
            if (this.desni == null) {
                this.desni = node;
                return true;
            }

            this.desni.insert(node);
        }

        // če je ...
        return false;
    }

    public boolean search(NodeBinarno node) {
        // našli smo element v vozlišču
        if (this.compare(node) == 0) {
            return true;
        }

        // gledamo v levo poddrevo
        if (this.compare(node) < 0) {
            // če je null, nismo našli elementa
            if (this.levi == null) {
                return false;
            }
            else {
                return this.levi.search(node);
            }
        }

        // gledamo v desno poddrevo
        else {
            // če je null, nismo našli elementa
            if (this.desni == null) {
                return false;
            }
            else {
                return this.desni.search(node);
            }
        }
    }

    /*
    1. Poiščemo vozlišče s key, ki mora biti izbrisan
    2. Ločimo 3 primere:
        a) če nima naslednikov, ga enostavno izbrišemo
        b) če ima samo enega naslednika, vozlišče in njegov key zamenjamo
        z njegovim edinim otrokom
        c) če ima oba naslednika, ga zamenjamo s tistim naslednikom, ki hrani
        minimalni ključ (najbolj levi v desnem poddrevesu)
     */
    public boolean delete(int element) {
        return false;
    }

    public int findMin(NodeBinarno node) {
        return 0;
    }

    public int compare(NodeBinarno node) {
        counter++;
        return node.key - this.key;
    }

    public int getCounter() {
        return counter;
    }

    public void resetCounter() {
        counter=0;
    }
}