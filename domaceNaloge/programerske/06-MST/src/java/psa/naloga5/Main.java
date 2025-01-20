package psa.naloga5;

import static org.junit.Assert.assertEquals;

public class Main {
    public static void displayMatrix(int n) {
        System.out.println("n = " + n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void displaySimetricMatrix(int n) {
        System.out.println("n = " + n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        displayMatrix(6);
        displaySimetricMatrix(6);

        Prim mst = new Prim(2);
		mst.addEdge(0, 1, 2);
        mst.displayA();
        int mstCost = mst.MSTcost();
        System.out.println("cost of mst: " + mstCost);

        Prim mst2 = new Prim(4);
		mst2.addEdge(0, 1, 2);
		mst2.addEdge(2, 0, 1);
		mst2.addEdge(2, 3, 1);
		mst2.addEdge(2, 1, 3);
        mst2.displayA();
        int mstCost2 = mst2.MSTcost();
        System.out.println("cost of mst: " + mstCost2);

        /*
		assertEquals(2, mst.data.length);
		assertEquals(2, mst.data[0].length);
		assertEquals(2, mst.data[1].length);
        mst.displayA();

		mst.addEdge(0, 1, 2);
		assertEquals(2, mst.data[0][1]);
		assertEquals(2, mst.data[1][0]);
		assertEquals(0, mst.data[0][0]);
		assertEquals(0, mst.data[1][1]);
        mst.displayA();
        */
    }
}
