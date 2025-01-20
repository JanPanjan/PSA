package psa.naloga3;

/*
 * Razred mora imeplementirati podatkovno strukturo Razprsilne tabele (HashTable).
 * Za funkcijo uporabite: h(x) = x * 701 mod 2000
 * V primeru kolizij uporabite VERIZENJE in sicer kot Slovar uporabite podatkovno
 * strukturo Razprsilne tabele, ki ga morate implementirati (razred HashTable2).
 * Pazite, ker je lahko ključ tudi negativno število
 */
public class HashTable {
	public HashTable2[] data;

	public HashTable() {
		this.data = new HashTable2[2000];

		for (int i = 0; i < this.data.length; i++) {
			this.data[i] = new HashTable2();
		}
	}

	/**
	 * Metoda sprejme key in ga hasha v neko vrednost na intervalu [0,2000) po
	 * izračunu enačbe h(x) = x * 701 mod 2000.
	 * @param x integer vrednost, ki jo želimo hashat
	 * @return int med 0 (inclusive) in 2000 (exclusive)
	 */
	public int hash(int x) {
		return (x * 701) % 2000;
	}

	/*
	 * Metoda sprejme število in ga vstavi v tabelo. Metoda vrne true, ce je
	 * bilo ustavljanje uspešno in false sicer
	 */
	public boolean insert(int key) {
		int i = hash(key);
		return this.data[i].insert(key);
	}

	/*
	 * Metoda sprejme število in ga poišče v tabeli. Metoda vrne true, ce je
	 * bilo ustavljanje uspešno in false sicer
	 */
	public boolean search(int key) {
		int i = hash(key);
		return this.data[i].search(key);
	}

	/*
	 * Metoda sprejme število in ga izbriše iz tabele. Metoda vrne true, ce je
	 * bilo ustavljanje uspešno in false sicer
	 */
	public boolean delete(int key) {
		int i = hash(key);
		return this.data[i].delete(key);
	}
}
