package psa.naloga1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Main {
    public static void linkedListMain() {
//        System.out.println("Private test:");
//        Seznam ll = new Seznam();
//
//        ll.insert(12);
//        ll.insert(43);
//        ll.insert(8);
//        ll.insert(5);
//        ll.insert(3);
//
//        System.out.print("(head)");
//        ll.printLL(ll.head);
//        System.out.println();
//
//        System.out.println(12 + " search " + ll.search(12));
//        System.out.println(13 + " search " + ll.search(13));
//        System.out.println(43 + " search " + ll.search(43));
//
//        System.out.println(12 + " delete " + ll.delete(12));
//
//        System.out.print("(head)");
//        ll.printLL(ll.head);
//        System.out.println();
//
//        System.out.println(8 + " delete " + ll.delete(8));
//
//        System.out.print("(head)");
//        ll.printLL(ll.head);
//        System.out.println();
//
//        System.out.println(3 + " delete " + ll.delete(3));
//
//        System.out.print("(head)");
//        ll.printLL(ll.head);
//        System.out.println();

        // ----- public test -----
//        System.out.println("\nPublic test:");
        Seznam seznam = new Seznam();
        seznam.insert(2);
        seznam.insert(5);
        seznam.insert(4);
        seznam.insert(1);
        seznam.insert(8);
        seznam.insert(3);
        seznam.printLL(seznam.head);
        System.out.println();

        assertTrue(seznam.search(3));
        assertFalse(seznam.search(10));
        seznam.delete(4);
        seznam.printLL(seznam.head);
        System.out.println();
        assertFalse(seznam.search(4));

//        System.out.print("(head)");
//        seznam.printLL(seznam.head);
//        System.out.println();
//
//        System.out.println("is head null? " + (seznam.head == null));
//        seznam.delete(3);
//        seznam.delete(10);
//        seznam.delete(4);
//
//        System.out.print("(head)");
//        seznam.printLL(seznam.head);
//        System.out.println();
//
//        seznam.search(4);
    }

    public static void binarnoMain() {
        Binarno dd = new Binarno();
        System.out.println(dd.getRoot() == null);
        assertFalse(dd.search(10));
        assertTrue(dd.insert(10));
        System.out.println(dd.getRoot().getKey());
        assertTrue(dd.search(10));
        assertTrue(dd.insert(12));
        assertTrue(dd.insert(8));
    }

    public static void main(String[] args) {
//        linkedListMain();
        binarnoMain();
    }
}
