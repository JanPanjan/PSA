package psa.naloga3;

import java.util.Arrays;

/*
 * Razred mora imeplementirati podatkovno strukturo Razprsilne tabele.
 * Za funkcijo uporabite: h(x) = x * 53 mod 100
 * V primeru kolizij uporabite LINEARNO NASLAVLJANJE.
 */
public class HashTable2 {
	public int[] data;

	public HashTable2() {
		this.data = new int[100];
		Arrays.fill(this.data, Integer.MIN_VALUE);
	}

	/**
	 * Metoda sprejme key in ga hasha v neko vrednost na intervalu [0,100) po
	 * izračunu enačbe h(x) = x * 53 mod 100.
	 * @param x integer vrednost, ki jo želimo hashat
	 * @return int med 0 (inclusive) in 100 (exclusive)
	 */
	public int hash(int x) {
		return (x * 53) % 100;
	}

	/**
	 * Metoda sprejme število in ga vstavi v tabelo. Prazna mesta v data so
	 * označena z Integer.MIN_VALUE, medtem ko so polna mesta označena s key.
	 * @param key vrednost, ki jo hočemo vstaviti v hash table
	 * @return true, če je bilo vstavljanje uspešno, false sicer
	 */
	public boolean insert(int key) {
	    // Check if key already exists
		if (this.search(key)) {
			return false;
		}

	    // Get initial hash position
	    int startPos = hash(key);
	    int pos = startPos;

	    // While we haven't wrapped around to our starting position
		do {
			if (this.data[pos] == Integer.MIN_VALUE) {
				this.data[pos] = key;
				return true;
			}
			// Move to next position, wrapping around if necessary
			pos = (pos + 1) % 100;
		} while (pos != startPos);

		return false;
	}

	/*
	 * Metoda sprejme število in ga poišče v tabeli. Metoda vrne true, ce je
	 * bilo ustavljanje uspešno in false sicer
	 */
	public boolean search(int key) {
		int startPos = hash(key);
		int pos = startPos;

		do {
			if (this.data[pos] == key) {
				return true;
			}
			if (this.data[pos] == Integer.MIN_VALUE) {
				return false;
			}
			pos = (pos + 1) % 100;
		} while (pos != startPos);

		return false;
	}

	/*
	 * Metoda sprejme število in ga izbriše iz tabele. Metoda vrne true, ce je
	 * bilo ustavljanje uspešno in false sicer
	 */
	public boolean delete(int key) {
		// najprej dobi hash index preko key parametra
		int i = hash(key);

		// poiskuša najt element med podatki
		while (this.data[i] != Integer.MIN_VALUE && i < this.data.length) {
			if (this.data[i] == key) {
				this.data[i] = Integer.MIN_VALUE;
				return true;
			}
		}
		return false;
	}
}