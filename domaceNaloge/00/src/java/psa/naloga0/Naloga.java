package psa.naloga0;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.BitSet;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class Naloga {
	boolean[] podatki;
	String hashName = "MD5";
	final MessageDigest digestFunc;
	int k;
	int velikost;
	
	public Naloga(int velikost, int k) {
		MessageDigest tmp; 
		try {
			tmp = java.security.MessageDigest.getInstance(hashName);
		} catch (NoSuchAlgorithmException e) {
			tmp = null;
		}
		digestFunc = tmp;
	}
	
	public void clear() {
		// vse vrednosti nastavimo na false
		for (boolean i : this.podatki) {
			i = false;
		}
	}
	
	public void add(String input) {
		byte[] bytes = input.getBytes();
		int[] hashes = createHashes(bytes, k);

		// dodamo niz v polje
		for (int hash : hashes) {
			podatki[hash] = true;
		}
	}
	
	public boolean contains(String input) {
		byte[] bytes = input.getBytes();
		int[] hashes = createHashes(bytes, k);

		// preverimo ali je niz v polju
		for (int hash : hashes) {
			// če en bit ni v podatkih, niz ni v seznamu
			if (podatki[hash] == false) {
				return false;
			}
		}
		// če so vsi biti v podatkih je verjetno tudi niz
		return true;
	}
	
	public int[] createHashes(byte[] data, int hashes) {
        int[] result = new int[hashes];

        int k = 0;
        while (k < hashes) {
            byte[] digest;
            digest = digestFunc.digest(data);
        
            for (int i = 0, j = 0; i < digest.length && k < hashes; i+=2, j++) {
                result[j] = Math.abs(((int)digest[i] << 8) | ((int)digest[i+1] & 0xFF))%velikost;
                k++;
            }
        }
        return result;
    }
}
