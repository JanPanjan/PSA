package psa.naloga4;

import java.util.Vector;

/*
 * Binomsko kopico implementiramo s poljem, katerega elementi so binomska
 * drevesa. Na prvem mestu je binomsko drevo B0 z enim elementom, na drugem
 * drevo B1 z dvema elementoma, na tretjem drevo B2 s štirimi elementi
 * (2^n?).
 *
 * Vsako drevo inicializiramo rekurzivno z uporabo razreda BinomialNode.
 */
public class BinomialHeap {
	// Array, ki nosi pointerje na binomska drevesa
	BinomialNode[] data;
	
	// Ustvari data array velikosti 1 (ker je BinomialNode kinda vektor,
	// se dinamično alocira spomin, ko je treba).
	BinomialHeap(){
		data = new BinomialNode[1];
	}
	
	/**
	 * Sprejme celo število in ga vstavi v binomsko kopico. Če je treba
	 * polje pred vstavljanjem povečati, se uporabi metodd resizeArray.
	 * @param key število, ki bo vstavljeno.
	 * @return true, če je ključ uspešno vstavljen, false sicer.
	 */
	public boolean insert(int key) {
		return false;
	}
	
	/**
	 * Metoda vrne minimalni ključ v binomski kopici.
	 * @return min element; če je prazna Integer.MAX_VALUE
	 */
	public int getMin() {
		return Integer.MAX_VALUE;
	}
	
	/**
	 * Metoda izbriše minimalni ključ iz binomske kopice.
	 * @return true, če je bil ključ uspešno izbrisan, false sicer.
	 */
	public boolean delMin() {
		return false;
	}

	/**
	 * Metoda poveča polje, tako da ustvari novo polje, ki je dvakrat
	 * daljše kot staro polje in prepiše elemente vanj.
	 */
	private void resizeArray() {
	}
	
	/**
	 * Metoda zlije dve binomski drevesi enakih velikosti skupaj.
	 * @param t1 prvo binomsko drevo
	 * @param t2 drugo binomsko drevo
	 * @return novo binomsko drevo
	 */
	private BinomialNode merge(BinomialNode t1, BinomialNode t2) {
		return null;
	}
}

