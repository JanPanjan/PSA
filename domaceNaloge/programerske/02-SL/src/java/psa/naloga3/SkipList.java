package psa.naloga3;

import java.util.Random;

public class SkipList {
	// head nima key vrednosti, ampak ima next seznam podseznamov
	private NodeSkipList head;

	// število nivojev, ki jih ima lahko seznam (npr. 4)
	private final int maxLevel;

	// najvišji trenutni nivo (npr. 2)
	// prepreči, da bi začeli iskanje v nivoju, kjer ni vozlišč
	// npr. ne začnemo v 4, ampak v 2
	// dalo bi se brez spremenljivke, tako da bi vedno začeli iskanje v
	// maxLevel - 1, ampak tako bi iskali tudi v nivojih, kjer ni vozlišč
	private int curLevel;

	// za met kovanca
	private Random rand;

	// ko ustvarimo skipList, je prvotno prazen in ima samo base level zato bo curLevel 1,
    // maxLevel pa bo tak, kot ga uporabnik želi. head bo novi skipList, ki bo imel next podseznam
	// dolžine maxLevel (ki bo prav tako null na začetku, dokler se vozlišča ne dodajo)
	public SkipList(long maxLevels) {
		this.head = new NodeSkipList(Integer.MIN_VALUE, (int) maxLevels);
		this.maxLevel = (int) maxLevels;
		this.curLevel = 1;
		this.rand = new Random();  // SPOMNI SE ZBRISAT SEED
	}

	public void setHead(NodeSkipList head) {
		this.head = head; 
	}

	public NodeSkipList getHead() {
		return head; 
	}

	public int getMaxLevel() {
		return maxLevel; 
	}

	public void setCurLevel(int curLevel) { 
		this.curLevel = curLevel; 
	}

	public int getCurLevel() {
		return curLevel; 
	}

	/*
	 * Metoda sprejme stevilo in poisce element v preskocnem seznamu. Metoda
	 * vrne true, ce je bil element uspesno najden v podatkovni strukturi, in
	 * false sicer.
	 */
	public boolean search(int key) {
		// Step 1: Empty list check
		if (head.next[0] == null) {
			return false;
		}

		// Step 2: search for key
		NodeSkipList cur = head;
		int curLvl = getCurLevel();
	
		for (int level = curLvl - 1; level >= 0; level--) {  // Vertically 
			while (cur.next[level] != null) {            // Horizontally
				if (cur.next[level].key == key) {        // Check for key
					return true;
				}
				if (cur.next[level].key > key) {         // Drop down if key is more
					break;
				}
				cur = cur.next[level];                   // Move right if key is less
			}
		}
		return false;
	}

	/*
	 * Metoda sprejme stevilo in ga vstavi v preskocni seznam. Ce element ze
	 * obstaja v podatkovni strukturi, vrne false. Metoda vrne true, ce je bil
	 * element uspesno vstavljen in false sicer.
	 */
	public boolean insert(int key) {
		// step 1: find insertion place
		NodeSkipList cur = this.head;                                 // pointer to current node
		NodeSkipList[] update = new NodeSkipList[getMaxLevel()]; // array of nodes to update after insertion

		// head in base level doesn't point to anything, so we have to update head on base level
		if (cur.next[0] == null) {
			update[0] = cur;
		}
		// head isn't null, so we have to traverse the list to find an insertion place.
		// it starts with the first node in current list level and traverse right, until cur node is smaller
		// than search key. before it goes right, cur pointer is updated to this node.
		// when it finds a null tail, it will have to go a level down, where it will start it's search with
		// the (new) cur node. it will go down if it reaches a node with a bigger key than the search key.
		else {
			for (int level = getCurLevel() - 1; level >= 0; level--) {          // vertically over every level
				while (cur.next[level] != null && cur.next[level].key <= key) {  // horizontally over nodes
					// don't insert duplicates
					if (cur.next[level].key == key) {                    
						return false;
					}
					// move a level down early
					if (cur.next[level].key > key) {
						break;
					}
					// update cur pointer
					cur = cur.next[level];
				}
				// update pointer every iteration to cur node
				update[level] = cur;
			}
		}
		// step 2: level calculations
		// coinFlip returns us the size for new node next array (min 1, max maxLevel).
		int newNodeLevel = coinFlip();

		// if it calculated more levels, than are currently used, references in upper levels are null
		// and it has to link them to head later.
		if (newNodeLevel > getCurLevel()) {
			for (int level = getCurLevel(); level < newNodeLevel; level++) {
				update[level] = this.head;
			}
		}
		// set new current level
		setCurLevel(newNodeLevel);

		// step 3: insert new node
		NodeSkipList newNode = new NodeSkipList(key, newNodeLevel);
		// to insert a node, we first have to link it to next of a level of all references stored in update
		// (update[level] is esentially a node pointer that has next[] and key) before linking the stored 
		// references to new node, so links aren't lost in the process.
		// next new node becomes next of current reference and next of reference then links to new node.
		// it traverses down from the top level to base level.
		System.out.println("inserting key " + key);
		for (int level = getCurLevel() - 1; level >= 0; level--) {
			newNode.next[level] = update[level].next[level];
			update[level].next[level] = newNode;
		}

		return true;
	}

	/*
	 * Metoda sprejme stevilo in izbrise element iz preskocnega seznama. Metoda
	 * vrne true, ce je bil element uspesno izbrisan iz podatkovne strukture, in
	 * false sicer
	 */
	public boolean delete(int key) {
		NodeSkipList current = head;
		int currentLevel = getCurLevel();
		int maxLevel = getMaxLevel();
		NodeSkipList[] update = new NodeSkipList[maxLevel + 1];

		for (int level = currentLevel; level >= 0; level--) {
			while (current.next[level] != null && current.next[level].key < key) {
				current = current.next[level]; // Move forward
			}
			update[level] = current;
		}

		current = current.next[0];

		if (current != null && current.key == key) {
			for (int level = 0; level <= currentLevel; level++) {
				if (update[level].next[level] == current) {
					update[level].next[level] = current.next[level];
				}
			}

			while (currentLevel > 0 && head.next[currentLevel] == null) {
				currentLevel--;
			}

			return true; // Successfully deleted the node
		}
		else {
			return false; // Node with the specified key does not exist
		}
	}

	/*
	* Metoda simulira met kovanca oz. geometrična slučajna spremenljivka. Vrne
	* število dogodkov, ko kovanec ni padel na cifro (false), temveč je padel na
	* grb (true), kar simulira nextBoolean metoda. Največji return value je maxLevel.
	* Vrne VSAJ 1, saj ne moremo imeti 0 nivojev.
	 */
	public int coinFlip() {
		int n = 1;
		while (n <= getMaxLevel() && rand.nextBoolean()) {
			n++;
		}
		return n;
	}

	/**
	 * Metoda izpiše skip list
	 */
	public void printSkipList() {
		int curLvl = getCurLevel();
		System.out.println("Skip List:");

		for (int level = curLvl; level >= 0; level--) {
			StringBuilder levelOutput = new StringBuilder("Level " + level + ": [ ");
			NodeSkipList current = head.next[level];

			while (current != null) {
				levelOutput.append(current.key).append(" ");
				current = current.next[level];
			}

			System.out.print(levelOutput);
			System.out.println("]");
		}
	}
	public void psl() {
		int curLvl = getCurLevel();
		System.out.println("Skip List:");

		for (int level = curLvl; level >= 0; level--) {
			StringBuilder levelOutput = new StringBuilder("Level " + level + ": ");
			NodeSkipList current = head.next[level];

			while (current != null) {
				levelOutput.append(current.key).append(" -> ");
				current = current.next[level];
			}

			levelOutput.append("null");
			System.out.println(levelOutput);
		}
	}
}