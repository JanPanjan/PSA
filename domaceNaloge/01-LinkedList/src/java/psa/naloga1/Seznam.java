package psa.naloga1;

import org.w3c.dom.Node;

public class Seznam {
	public NodeSeznam head;

	public Seznam() {
		head = null;
	}

	public void printLL(NodeSeznam node) {
		if (node == null) {
			System.out.print("null");
			return;
		}
		System.out.print(node.getKey() + "-");
		printLL(node.rep);
	}

	/*
	 * Metoda sprejme celo stevilo in ga vstavi v seznam. Ce element ze obstaja v seznamu, vrne false
	 * Metoda vrne true, ce je bil element uspesno vstavljen in false sicer.
	 */
	public boolean insert(int element) {
		NodeSeznam newNode = new NodeSeznam(element);

		// prazen seznam, vstavimo prvi node na head
		if (head == null) {
			head = newNode;
			return true;
		}

		// če najde element v seznamu, elementa ne vstavi in vrne false
		if (head.search(newNode)) {
			return false;
		}

		// če ne najde elementa, vstavi na rep, ki je null
		head.insert(newNode);
		return true;
	}

	/*
	 * Metoda sprejme celo stevilo in izbrise element iz seznama. 
	 * Metoda vrne true, ce je bil element uspesno izbrisan iz seznama, in false sicer
	 */
	public boolean delete(int element) {
		NodeSeznam nodeToBeDeleted = new NodeSeznam(element);

		if (head.getKey() == element) {
			head = head.rep;
			return true;
		}

		NodeSeznam current = this.head;
		NodeSeznam previous = null;

		while (current != null && current.getKey() != element) {
			previous = current;
			current = current.rep;
		}

		if (current != null) {
			previous.rep = current.rep;
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Metoda sprejme celo stevilo in poisce element v seznamu. 
	 * Metoda vrne true, ce je bil element uspesno najden v seznamu, in false sicer
	 */
	public boolean search(int element) {
		NodeSeznam nodeToBeFound = new NodeSeznam(element);

		if (head == null) {
			return false;
		}

		return head.search(nodeToBeFound);
	}
	
	public int getCounter() {
		return head != null?head.getCounter():null;
	}
	
	public void resetCounter() {
		if(head!= null) {
			head.resetCounter();
		}
	}
}
