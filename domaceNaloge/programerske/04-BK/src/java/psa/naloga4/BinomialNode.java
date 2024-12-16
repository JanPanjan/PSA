package psa.naloga4;

import java.util.Vector;

/*
 * Metode tega razreda so potrebne v BinomialHeap, tu ni potrebno 
 * inicializirati nič
 */
public class BinomialNode {
	// Vektor je dinamična struktura. V tem primeru so njeni elementi
	// BinomialNode-s. Vsak node ima tudi key.
	public Vector<BinomialNode> childs;
	public int key;
	
	// Ko ustvarimo node, se shrani key in inicializira se vektor childs 
	public BinomialNode(int key) {
		this.key = key;
		childs = new Vector<BinomialNode>();
	}
	
	/**
	 * Metoda vstavi novi node v vektor childs
	 * @param child node, ki bo vstavljen
	 */
	public boolean addChild(BinomialNode child) {
		return childs.add(child);
	}
	
	/**
	 * @returns vektor otrok tega noda
	 */
	public Vector<BinomialNode> getChilds() {
		return this.childs;
	}
	
	/**
	 * @returns key tega noda
	 */
	public int getKey() {
		return this.key;
	}
	
}
