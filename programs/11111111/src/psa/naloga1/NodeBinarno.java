package psa.naloga1;

public class NodeBinarno {
	private static int counter;
	private int key;
	private NodeBinarno left, right;

	NodeBinarno(int element) {
		this.key = element;
		this.left = null;
		this.right = null;
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

	public NodeBinarno getLeft() {
		return this.left;
	}

	public NodeBinarno getRight() {
		return this.right;
	}

	public int getKey() {
		return key;
	}

	public void setLeft(NodeBinarno node) {
		left = node;
	}

	public void setRight(NodeBinarno node) {right = node;}

	public void setKey(int element) {key = element;}


	public boolean insert(NodeBinarno node) {
		int comparison = this.compare(node);
		if(comparison == 0) return false;

		else if(comparison < 0) {
			if(this.left == null) {
				this.left = node;
				return true;
			}
			else return this.left.insert(node);
		}

		else {
			if (this.right == null) {
				this.right = node;
				return true;
			}
			else return this.right.insert(node);
		}
	}

	public boolean delete(NodeBinarno node) {
		System.out.println("trying to delete ");
		if(this.left == null && this.right == null) return false;

		else {
			int comparison = this.compare(node);
			//System.out.println("compared this " + this.key + " with a " + node.getKey() + " and got " + comparison);

			//look right
			if (comparison > 0 ) {
				//System.out.println(this.compare(node) + " for " + this.getKey());
				if (right == null) return false;

				else if(this.right.compare(node) == 0) {
					//System.out.println("compared this right " + this.right.key + " with a " + node.getKey() + " and got something");
					//has no children
					if(this.right.right == null && this.right.left == null) {
						this.right = null;
					}

					//doesn't have right child
					else if(this.right.right == null) {
						this.right = this.right.left;
					}

					//doesn't have left child
					else if(this.right.left == null) {
						this.right = this.right.right;
					}

					//has both children
					else {
						System.out.println(this.right.key + " should be the culprit");
						//if right subtree has no left subtree
						if(this.right.right.left == null) {
							//putting deleting node's left subtree to the incoming node
							//System.out.println(this.right.right.left.toString());
							System.out.println(this.right.left.key);
							this.right.right.left = this.right.left;
							this.right = this.right.right;
						}

						else {
							//System.out.println("findmin part");
							NodeBinarno minParent = this.right.findMin();
							//System.out.println("min parent is " + minParent.key);
							//System.out.println("min value node is " + minParent.left.key);
							this.right.key = minParent.left.key;
							minParent.left = null;
						}
					}
					return true;
				}
				else return right.delete(node);
			}

			//look left
			else if (comparison < 0 ) {
				if (left == null) return false;

				else if (this.left.compare(node) == 0) {
					//System.out.println("compared this left " + this.left.key + " with a " + node.getKey() + " and got something");
					//has no children
					if (this.left.right == null && this.left.left == null) {
						this.left = null;
					}

					//doesn't have a right child
					else if (this.left.right == null) {
						this.left = this.left.left;
					}

					//doesn't have left child, so putting right
					else if (this.left.left == null) {
						this.left = this.left.right;
					} else {
						NodeBinarno min = this.right.findMin();
						this.left.key = min.key;
						this.delete(min);
					}
					return true;
				}
				else return left.delete(node);
			}
			else return false;
		}
	}

	public boolean search(NodeBinarno node) {
		int comparison = this.compare(node);
		//System.out.println("compared this " + this.getKey() + " with a " + node.getKey() + " and got " + comparison);
		if(comparison == 0) return true;

		else if (comparison < 0) return left != null && left.search(node);

		else return right != null && right.search(node);
	}

	//finds the first parent of the min node (that min node is on the left of the result node)
	public NodeBinarno findMin() {
		NodeBinarno result = this;
		System.out.println("initial result is " + result.key);
		while(result.left.left != null) result = result.left;
		return result;

	}

	public String printNode() {
		return key + ", (l: " + (left == null ? " " : left.printNode()) + "), (r: " + (right == null ? " " : right.printNode()) + ")";

	}
}
