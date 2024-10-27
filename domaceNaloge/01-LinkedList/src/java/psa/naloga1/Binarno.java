package psa.naloga1;

public class Binarno {
	private NodeBinarno root;

	public Binarno() {
		this.root = null;
	}

	public NodeBinarno getRoot() {
		return this.root;
	}

	/*
	 * Metoda sprejme celo stevilo in ga vstavi v drevo. Ce element ze obstaja v drevesu, vrne false
	 * Metoda vrne true, ce je bil element uspesno vstavljen in false sicer.
	 */
	public boolean insert(int element) {
		// drevo ne obstaja, zato ga ustvarimo
		if (root == null) {
			root = new NodeBinarno(element);
			return true;
		}

		// vstavimo v levo ali desno poddrevo
		return this.root.insert(element);
	}

	/*
	 * Metoda sprejme celo stevilo in izbrise element iz drevesa.
	 * Metoda vrne true, ce je bil element uspesno izbrisan iz drevesa, in false, če ni bil v drevesu
	 * če ima vozlišče levo in desno poddrevo, ga zamenjajte z minimalnim elementom v strukturi, ki
	 * je že večji od brisanega elementa (torej z minimalnim elementom v desnem poddrevesu)
	 */
	public boolean delete(int element) {
		return true;
	}

	/*
	 * Metoda sprejme celo stevilo in poisce element v drevesu. 
	 * Metoda vrne true, ce je bil element uspesno najden v drevesu, in false sicer
	 */
	public boolean search(int element) {
		// drevo je prazno, ni elementov
		if (root == null) {
			return false;
		}

		// gledamo levo ali desno poddrevo
		return this.root.search(element);
	}

	public int getCounter() {
		return root != null?root.getCounter():null;
	}
	
	public void resetCounter() {
		if(root!= null)
			root.resetCounter();
	}
}

