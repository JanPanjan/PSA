package psa.naloga2;


public class UnionFind {
	public int[] id;
	public int[] rang;

	public UnionFind(int N) {
		id = new int[N];
		rang = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;   // vsak element je svoj lastni predstavnik
			rang[i] = 0; // začetna globina je 0
		}
	}

	/*
	 * Metoda sprejme index in vrne predstavnika mnozice, katere clan je index, poleg
	 * path compression.
	 */
	public int find(int i) {
		if (id[i] != i) {
			id[i] = find(id[i]);
		}
		return id[i];
	}

	/*
	 * Metoda sprejme da indexa in naredi unijo množic, ki vsebujeta p in q
	 * Združi ju glede na rang.
	 */
	public void unite(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if (rootP != rootQ) {
			if (this.rang[rootP] > this.rang[rootQ]) {
				id[rootQ] = rootP;
			} 
			else if (this.rang[rootP] < this.rang[rootQ]) {
				id[rootP] = rootQ;
			} 
			else {
				id[rootQ] = rootP;
				this.rang[rootP]++;
			}
		}
	}
	
	/*
	 * Metoda vrne true, ce sta p in q v isti mnozici
	 */
	public boolean isInSameSet(int p, int q) {
		return find(p) == find(q);
	}
}
