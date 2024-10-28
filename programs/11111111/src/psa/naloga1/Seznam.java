package psa.naloga1;

public class Seznam {
	private NodeSeznam head;

	Seznam() {
		this.head = null;
	}

	public NodeSeznam getHead() {return this.head;}


	public boolean insert(int element) {
		NodeSeznam node = new NodeSeznam(element);
		if(head == null) {
			head = node;
			return true;
		}
		else return head.insert(node);
	}

	public boolean delete(int element) {
		if(head == null) return false;
		else {
			NodeSeznam node = new NodeSeznam(element);
			if(head.compare(node) == 0) {
				head = head.getTail();
				return true;
			}
			else return head.getTail().delete(node);
		}
	}

	public boolean search(int element) {
		//System.out.println("in search seznam");
		if(head == null) return false;
		else {
			NodeSeznam node = new NodeSeznam(element);
			return head.search(node);
		}
	}
	
	public int getCounter() {
		return head != null?head.getCounter():null;
	}
	
	public void resetCounter() {
		if(head!= null)
			head.resetCounter();
	}

	public void printSeznam() {
		if(this == null) System.out.println(("Null"));
		else if(this.getHead().getTail() == null)
			System.out.println(this.head.getKey() + " -- Null" );

		else System.out.print(this.head.getKey() + " -- " + this.head.getTail().printNodeSeznam());
	}
}
