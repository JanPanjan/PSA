package psa.naloga1;

public class Binarno {
	private NodeBinarno root;

	public Binarno() {
		this.root = null;
	}

	public void setRoot(NodeBinarno root) {
		this.root = root;
	}

	public NodeBinarno getRoot() {
		return this.root;
	}

	/*
	 * Metoda sprejme celo stevilo in ga vstavi v drevo. Ce element ze obstaja v drevesu, vrne false
	 * Metoda vrne true, ce je bil element uspesno vstavljen in false sicer.
	 */
	public boolean insert(int element) {
		NodeBinarno nodeToBeInserted = new NodeBinarno(element);

		// drevo ne obstaja, zato ga ustvarimo
		if (root == null) {
			this.setRoot(nodeToBeInserted);
			return true;
		}

		// vstavimo v levo ali desno poddrevo
		return this.root.insert(nodeToBeInserted);
	}

	/*
	 * Metoda sprejme celo stevilo in izbrise element iz drevesa.
	 * Metoda vrne true, ce je bil element uspesno izbrisan iz drevesa, in false, če ni bil v drevesu
	 * če ima vozlišče levo in desno poddrevo, ga zamenjajte z minimalnim elementom v strukturi, ki
	 * je že večji od brisanega elementa (torej z minimalnim elementom v desnem poddrevesu)
	 */
	public boolean search(int element) {
		NodeBinarno nodeToBeFound = new NodeBinarno(element);

		// drevo je prazno, ni elementov
		if (this.root == null) {
			return false;
		}

		return this.root.search(nodeToBeFound);
	}

	/*
	 * Metoda sprejme celo stevilo in poisce element v drevesu. 
	 * Metoda vrne true, ce je bil element uspesno najden v drevesu, in false sicer
	 */
	public boolean delete(int element) {
		NodeBinarno nodeToBeDeleted = new NodeBinarno(element);

		// drevo je prazno, ni elementov
		if (root == null) {
			return false;
		}

		// začnemo pri root
		NodeBinarno parent = null;
		NodeBinarno current = this.getRoot();

		// Najdemo node z elementom, ki ga želimo izbrisati
		// moramo se premakniti v enega od dveh repov glede na isakni ključ
		// če elementa ni, je current null (ni poddrevesa)
		while (current.compare(nodeToBeDeleted) != 0) {
			parent = current;

			// ----- levo -----
			if (current.compare(nodeToBeDeleted) < 0) {
				if (current.getLevi() == null) {
					return false;
				}
				current = current.getLevi();
			}
			// ----- desno -----
			else {
				if (current.getDesni() == null) {
					return false;
				}
				current = current.getDesni();
			}
		}

		// CASE a: drevo nima naslednikov
		if (current.getLevi() == null && current.getDesni() == null) {
			// root je edini brez parent
			if (parent == null) {
				this.setRoot(null);
			}

			else if (parent.getLevi() == current) {
				parent.setLevi(null);
			}

			else {
				parent.setDesni(null);
			}

			return true;
		}

		// CASE b: drevo ima enega naslednika
		if (current.getLevi() != null || current.getDesni() != null) {
			// najdemo naslednika
			// (remember C conditionals)
			NodeBinarno child = current.getLevi() != null ?  current.getLevi() : current.getDesni();

			// root je edini brez starša
			if (parent == null) {
				this.root.setKey(child.getKey());
				this.root.setLevi(child.getLevi());
				this.root.setDesni(child.getDesni());
			}

			// levi subtree ni null
			else if (parent.getLevi() == current) {
				parent.setLevi(child);
			}

			// desni subtree ni null
			else {
				parent.setDesni(child);
			}

			return true;
		}

		// CASE c: drevo ima oba naslednika
		if (current.getLevi() != null && current.getDesni() != null) {
			// Najti moramo inorder successor (najmanjši v desnem poddrevesu) in njegov parent node
			NodeBinarno succParent = current;
			NodeBinarno succ = current.getDesni();

			// ko najde levega, bo njegov levi rep null
			while (succ.getLevi() != null) {
				succParent = succ;
				succ = succ.getLevi();
			}

			// zamenjamo key od trenutni node (ta, ki ga brišemo) s key od succ
			current.setKey(succ.getKey());

			// izbrišemo referenco na successorja
			if (succParent.getLevi() == succ) {
				succParent.setLevi(succ.getDesni());
			}
			else {
				succParent.setDesni(succ.getDesni());
			}

			return true;
		}

		System.out.println("nekaj je narobe");
		// če je...
		return false;
	}

	public int getCounter() {
		return root != null?root.getCounter():null;
	}
	
	public void resetCounter() {
		if(root!= null)
			root.resetCounter();
	}
}

