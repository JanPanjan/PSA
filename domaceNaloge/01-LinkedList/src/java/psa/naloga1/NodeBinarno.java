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
		// če je manjši gremo levo
//		System.out.println("--- insert:");
		System.out.println(element);
		System.out.println(this.getKey());
		if (element < this.getKey()) {
//			System.out.println("left");
			// če je levo null, vstavimo novi element
			if (this.levi == null) {
				System.out.println("inserting");
				this.levi = new NodeBinarno(element);
				return true;
			}

			// če ni null, kličemo naprej
			this.levi.insert(element);
		}
		// če je večji gremo desno
		if (element > this.getKey()) {
//			System.out.println("right");
			// če je desno null, vstavimo novi element
			if (this.desni == null) {
				System.out.println("inserting");
				this.desni = new NodeBinarno(element);
				return true;
			}

			// če ni null, kličemo naprej
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
		if (this.key < element) {
			// če je null, nismo našli elementa
			if (this.levi == null) {
				return false;
			} else {
				return this.levi.search(element);
			}
		}

		// gledamo v desno poddrevo
		if (this.key > element) {
			// če je null, nismo našli elementa
			if (this.desni == null) {
				return false;
			} else {
				return this.desni.search(element);
			}
		}

		// če je...
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
