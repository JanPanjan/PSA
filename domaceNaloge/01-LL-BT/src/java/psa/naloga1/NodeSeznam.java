package psa.naloga1;

public class NodeSeznam {
	private static int counter;
	private int key;
	public NodeSeznam rep;

	public NodeSeznam(int key) {
		this.key = key;
		rep = null;
	}

	public int getKey() {
		return key;
	}

	/*
	* Funkcija vstavi node na zadnji rep v seznamu (ta, ki je null)
	 */
	public void insert(NodeSeznam node) {
		// če je rep prazen, vstavi na rep
		if (rep == null) {
			rep = node;
			return;
		}
		// iščemo prazen rep
		rep.insert(node);
	}

	// T O
	// M O R A
	// B I T
	// R E K U R Z I V N O
	public boolean search(NodeSeznam node) {
		NodeSeznam current = this;

		if (current.key == node.key) {
			return true;
		}
		if (current.rep == null) {
			return false;
		}

		current = current.rep;
		return current.search(node);
	}
	// T O
	// M O R A
	// B I T
	// R E K U R Z I V N O

	public int compare(NodeSeznam node) {
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
