package psa.naloga1;

public class Binarno {
	private psa.naloga1.NodeBinarno root;

	Binarno() {
		this.root = null;
	}

	public boolean insert(int element) {
		if(root == null) {
			root = new NodeBinarno(element);
			return true;
		}

		NodeBinarno node = new NodeBinarno(element);
		int comparison = root.compare(node);
		//System.out.println("compared this " + root.getKey() + " with a " + node.getKey() + " and got " + comparison);

		if (comparison == 0) return false;

		else if (comparison < 0) {
			if (root.getLeft() == null) {
				root.setLeft(node);
				return true;
			}
			else return root.getLeft().insert(node);
		}

		else {
			if (root.getRight() == null) {
				root.setRight(node);
				return true;
			}
			else return root.getRight().insert(node);
		}
	}

	public boolean delete(int element) {
		if(root == null) return false;

		NodeBinarno node = new NodeBinarno(element);
		int comparison = root.compare(node);
		//System.out.println("compared this " + root.getKey() + " with a " + node.getKey() + " and got " + comparison);

		if (comparison == 0) {
			//has no children
			if (root.getLeft() == null && root.getRight() == null) {
				root = null;
			}

			//has no left
			else if (root.getLeft() == null) {
				root = root.getRight();
			}

			//has no right
			else if (root.getRight() == null) {
				root = root.getLeft();
			}

			//has both
			else {
				//if right child has no left subtree
				if(root.getRight().getLeft() == null) {
					root.getRight().setLeft(root.getLeft());
					root = root.getRight();


					//putting deleting node's left subtree to the incoming node
					//System.out.println(this.right.right.left.toString());
				}

				else {
					System.out.println("findmin part");
					psa.naloga1.NodeBinarno minParent = root.getRight().findMin();
					System.out.println("min parent is " + minParent.getKey());
					System.out.println("min value node is " + minParent.getLeft().getKey());
					root.setKey(minParent.getLeft().getKey());
					minParent.setLeft(null);
				}
			}
			return true;
		}

		else return root.delete(node);
	}

	public boolean search(int element) {
		if (this.root == null) return false;

		NodeBinarno node = new NodeBinarno(element);
		int comparison = root.compare(node);

		if (comparison == 0)  return true;

		else if(comparison < 0) {
				return root.getLeft() != null && root.getLeft().search(node);
			}
		else
			return root.getRight() != null && root.getRight().search(node);
	}

	public int getCounter() {
		return root != null?root.getCounter():null;
	}
	
	public void resetCounter() {
		if(root!= null)
			root.resetCounter();
	}

	public void printBinarno() {
		String string;
		if(root == null) System.out.println(" ");

		else {
			string = "";
			string += root.getKey() + ", (l: ";
			string += root.getLeft() == null ? " " : root.getLeft().printNode();
			string += "), (r: ";
			string += root.getRight() == null ? " " : root.getRight().printNode();
			string += ")";

			System.out.println(string);
		}
	}
}

