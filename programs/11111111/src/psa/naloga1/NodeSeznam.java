package psa.naloga1;

public class NodeSeznam {
	private static int counter;
	private int key;
	private NodeSeznam tail;

	NodeSeznam(int element) {
		this.key = element;
		this.tail = null;
	}

	//returns positive if node bigger than this
	//negative if this bigger than node
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

	public int getKey() {return this.key;}

	public void setKey(int element) {this.key = element;}

	public NodeSeznam getTail() {return this.tail;}

	public void setTail(NodeSeznam node) {this.tail = node;}

	public boolean insert(NodeSeznam node) {
		//if node is equal to this
		if(this.compare(node) == 0) {
			return false;
		}
		//if this is the last, insert here
		else if(this.tail == null) {
			this.tail = node;
			return true;
		}

		else
			return this.tail.insert(node);
	}

	public boolean search(NodeSeznam node) {
		int comparison = this.compare(node);

		if(comparison == 0) return true;
		else if(this.tail == null) return false;
		else return this.tail.search(node);
	}

	public boolean delete(NodeSeznam node) {
		//dealing one element ahead
		if(this.tail == null) return false;
		else if(this.tail.compare(node) == 0) {
			this.tail = this.tail.tail;
			return true;
		}
		else return this.tail.delete(node);
	}

	public String printNodeSeznam() {
		if(this.tail == null) return this.key + " -- Null\n";
		else return this.getKey() + " -- " + this.tail.printNodeSeznam();
	}
}
