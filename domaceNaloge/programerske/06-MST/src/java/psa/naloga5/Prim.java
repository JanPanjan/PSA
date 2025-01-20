package psa.naloga5;

/*
 * Množica vozlišč V = {0,1,...,n-1}
 * Množica povezav E = Z\{0} (nenegativna števila)
 * 
 * A je simetrična utežena matrika sosednosti (Aij = Aji), ki 
 * predstavlja utežen usmerjen graf. 
 * 
 * Povezava med i in j obstaja takrat, ko Aij!=0; takrat je 
 * Aij enaka uteži povezave.
 */
public class Prim {
	public int[][] data; // matrika sosednosti
	public int n;     // Velikost matrike A

	public Prim(int n) {
		this.data = new int[n][n];
		this.n = n;
	}

	public Prim(int[][] d) {
		this.data = d;
		this.n = d[0].length;
	}

	public void displayA() {
		System.out.println("Displaying matrika sosednosti...");
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * V matriko sosednosti A doda povezavo med i in j z utezjo d
	 * @param i vozlisce
	 * @param j vozlisce
	 * @param d utez povezave
	 */
	public void addEdge(int i, int j, int d) {
		if (d <= 0) { // po navodilih
			return;
		}
		if (i == j) { // diagonala => isto vozlišče
			return;
		}
		this.data[i][j] = d;
		this.data[j][i] = d;
	}

	/**
	 * Vrne ceno minimalnega vpetega drevesa
	 * @return cena drevesa
	 */
	public int MSTcost() {
		// Zapeljem se čez uteži in jih seštejem. Zapeljem se samo po enem 
		// trikotniku, ker je simetrična matrika. Naj bo to spodnji trikotnik.
		int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
					sum += data[i][j];
                }
            }
        }
		return sum;
	}

	public int minWeigthNode(int key[], boolean[] checked) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;

		for (int i = 0; i < this.n; i++) {
			if (!checked[i] && key[i] < min) {
				min = key[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	/**
	 * Vrne minimalno vpeto drevo s korenom v `s`. Minimalno vpeto drevo
	 * je podano s poljem staršev `P`. Starš vozlišča `i` v drevesu je enak
	 * P[i]. Predpostavimo, da je starš korena `s` vedno enak 0, torej
	 * P[s] = 0.
	 * @param s vozlišče, ki predstavlja koren drevesa
	 * @return integer array, ki predstavlja minimalno vpeto drevo s korenom v s
	 */
	public int[] prim(int s) {
		if (s >= this.n) {
			throw new IllegalArgumentException("s ne more bit večji kot n cmon...");
		}

		int[] parents = new int[this.n];
		int[] key = new int[this.n];
		boolean[] checked = new boolean[this.n];

		// Initializira vse ključe (uteži) in postavi vsa vozlišča na false (nepregledana)
		for (int i = 0; i < this.n; i++) {
			key[i] = Integer.MAX_VALUE;
			checked[i] = false;
		}

		key[s] = 0;
		parents[s] = 0; //  s je sam sebi starš, zato je 0

		for (int i = 0; i < this.n; i++) {
			int u = minWeigthNode(key, checked);
			checked[u] = true;

			for (int v = 0; v < this.n; v++) {
				if (this.data[u][v] != 0 && !checked[v] && this.data[u][v] < key[v]) {
					parents[v] = u;
					key[v] = this.data[u][v];
				}
			}
		}
		return parents;
	}
}
