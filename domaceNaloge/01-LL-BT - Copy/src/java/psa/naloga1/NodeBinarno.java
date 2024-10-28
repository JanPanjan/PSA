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

	public boolean insert(int element) {
<<<<<<< HEAD:domaceNaloge/01-LinkedList/src/java/psa/naloga1/NodeBinarno.java
     // element je v seznamu, zato ga ne vstavimo
     if (this.getKey() == element) {
         return false;
 }
=======
		// če je v drevesu, ne vstavljamo
		if (this.getKey() == element) {
			return false;
		}

>>>>>>> 261b263 (01):domaceNaloge/01-LL-BT/src/java/psa/naloga1/NodeBinarno.java
		// če je manjši gremo levo
		if (element < this.getKey()) {
			// če je levo null, vstavimo novi element
			if (this.levi == null) {
				this.levi = new NodeBinarno(element);
				return true;
			}

			this.levi.insert(element);
		}
		// če je večji gremo desno
		if (element > this.getKey()) {
			// če je desno null, vstavimo novi element
			if (this.desni == null) {
				this.desni = new NodeBinarno(element);
				return true;
			}

			this.desni.insert(element);
		}

		// če je ...
		return false;
	}

	public boolean search(int element) {
		// našli smo element v vozlišču
		if (this.key == element) {
			return true;
		}

		// gledamo v levo poddrevo
		if (element < this.key) {
			// če je null, nismo našli elementa
			if (this.levi == null) {
				return false;
			}
			else {
				return this.levi.search(element);
			}
		}

		// gledamo v desno poddrevo
		else {
			// če je null, nismo našli elementa
			if (this.desni == null) {
				return false;
			}
			else {
				return this.desni.search(element);
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
