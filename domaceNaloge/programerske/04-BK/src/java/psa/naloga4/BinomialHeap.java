package psa.naloga4;

import java.util.Random;

public class BinomialHeap {
    // Array, ki nosi pointerje na binomska drevesa
    BinomialNode[] data;
    Random rand = new Random();

    // Konstruktor, inicializira array velikosti 1
    BinomialHeap() {
        data = new BinomialNode[1];
    }

    public boolean insert(int key) {
        BinomialNode newNode = new BinomialNode(key);
        BinomialHeap tempHeap = new BinomialHeap();
        tempHeap.data = new BinomialNode[1];
        tempHeap.data[0] = newNode;
    
        this.mergeHeaps(tempHeap);
        return true;
    }
    

    public int getMin() {
        int min = Integer.MAX_VALUE;
        for (BinomialNode node : data) {
            if (node != null && node.getKey() < min) {
                min = node.getKey();
            }
        }
        return min;
    }
    
    public boolean delMin() {
        int minIndex = -1;
        int min = Integer.MAX_VALUE;
    
        // Poiščemo drevo z minimalnim elementom
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null && data[i].getKey() < min) {
                min = data[i].getKey();
                minIndex = i;
            }
        }
    
        if (minIndex == -1) {
            return false; // Kopica je prazna
        }
    
        BinomialNode minNode = data[minIndex];
        data[minIndex] = null;
    
        BinomialHeap tempHeap = new BinomialHeap();
        tempHeap.data = new BinomialNode[minNode.getChilds().size()];
    
        for (int i = 0; i < minNode.getChilds().size(); i++) {
            tempHeap.data[i] = minNode.getChilds().get(minNode.getChilds().size() - 1 - i);
        }
    
        this.mergeHeaps(tempHeap);
        return true;
    }
    
    

    private void resizeArray() {
        BinomialNode[] newData = new BinomialNode[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    private BinomialNode merge(BinomialNode t1, BinomialNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        if (t1.getKey() > t2.getKey()) {
            BinomialNode temp = t1;
            t1 = t2;
            t2 = temp;
        }
        t1.addChild(t2);
        return t1;
    }

    private void mergeHeaps(BinomialHeap otherHeap) {
        int maxLength = Math.max(this.data.length, otherHeap.data.length);
    
        // Razširi array trenutne kopice, če je treba
        while (this.data.length < maxLength) {
            resizeArray();
        }
    
        BinomialNode carry = null; 
        for (int i = 0; i < maxLength || carry != null; i++) {
            if (i >= this.data.length) {
                resizeArray();
            }
    
            BinomialNode t1 = i < this.data.length ? this.data[i] : null;
            BinomialNode t2 = i < otherHeap.data.length ? otherHeap.data[i] : null;
    
            int count = (t1 != null ? 1 : 0) + (t2 != null ? 1 : 0) + (carry != null ? 1 : 0);
    
            switch (count) {
                case 0: // Ni dreves za združit
                    break;
                case 1: // Eno drevo
                    this.data[i] = t1 != null ? t1 : t2 != null ? t2 : carry;
                    carry = null;
                    break;
                case 2: // Dve drevesi
                    carry = merge(t1 != null ? t1 : t2, t2 != null && t1 == null ? t2 : carry);
                    this.data[i] = null;
                    break;
                case 3: // Tri drevesa
                    this.data[i] = carry;
                    carry = merge(t1, t2);
                    break;
            }
        }
    }
}
