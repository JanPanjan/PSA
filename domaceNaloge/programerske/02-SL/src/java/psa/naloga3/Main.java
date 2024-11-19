package psa.naloga3;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Main {
    public static void main(String[] args) {
		SkipList sl = new SkipList(64);
		sl.insert(1);
		assertTrue(sl.search(1));
		sl.insert(2);
		assertFalse(sl.insert(2));
        String text = "1 2 4 45 23 3 6 34 25 8 12 10 -21 -43 -22 100 105 -99 -67 13 99 -18 10453 19532 -2981 -21374 -91238 21341";
        for (String i: text.split(" ")){
			sl.insert(Integer.parseInt(i));
		}
		assertTrue(sl.search(-21));
		assertTrue(sl.search(6));
		assertFalse(sl.search(7));
		assertTrue(sl.search(45));
		assertTrue(sl.search(-22));
		assertFalse(sl.search(-100));
		assertTrue(sl.search(8));
		assertFalse(sl.search(9));
		assertTrue(sl.search(12));
		assertFalse(sl.search(0));
		assertFalse(sl.search(-145));
		assertTrue(sl.search(1));
		assertTrue(sl.search(34));
    }
}
