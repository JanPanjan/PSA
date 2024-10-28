# Deli-in-vladaj ali divide-and-conquer

Večino uporabnih algoritmov je rekurzivnih, kar pomeni, da se kličejo znotraj
sebe, da rešijo celoten problem tako, da vsak klic reši svoj del problema,
zato so ponavadi ti algoritmi vrste **deli-in-vladaj**.

Problem razbijejo na manjše probleme, ki so podobni prvotnemu problemu, le
da dela algoritem z manj podatki. Na koncu združijo rešitve, da rešijo
prvotni problem.

Če je problem dovolj majhen (ko nastopi **base case**), se reši brez rekurzije.
Drugače se rekurzivni klic deli na 3 dele:

1. **divide the problem** na subprobleme, ki so manjši deli celega problema
2. **conquer the subproblems** tako, da jih reši rekurzivno
3. **combine the subproblem solutions**, da se ustvari rešitev za cel problem

## Merge sort

Merge sort algoritem deluje po teh smernicah. V vsakem koraku sortira podseznam
$A[p:r]$, tako da začne z $A[1:n]$ in dela naprej z vse manjšimi podseznami.
Potek izgleda tako:

1. **Divide:** 
Podseznam $A[p:r]$ razdeli na dva sorazmerna podseznama, vsak na polovico
prvotne velikosti. To naredi tako, da najde središčno točko (povprečje med
$p$ in $r$, ki naj bo $q$), nato pa razdeli s pomočjo te točke v podseznama
$A[p:q]$ in $A[q+1:r]$.

2. **Conquer**
Oba podseznama sortira rekurzivno z Merge sort metodo.

3. **Combine**
Podseznama združi nazaj v $A[p:r]$ v pravem vrstnem redu, posledično vrne
urejen seznam in odgovor.

---

Rekurzija doseže base case, ko je podseznam, ki se mora sortirati, velikosti
1, torej ima samo en element. To se zgodi, ko je $p=r$. Kot smo videli v
invarianci *insertion-sort*, je seznam dolžine 1 vedno urejen.

Ključni del algoritma je v *combine* ali *merge* koraku, kjer se dva podseznama združita.

### Analogija s kartami

Naj imamo na mizi dva kupa kart, obrnjene tako, da vidimo njihove vrednosti.
Vsak kup je sortiran, z najmanjšo vrednostjo na vrhu kupa. Hočem združiti dva
kupa v en sortiran urejen kup, ki ne bo imel vidnih vrednosti kart. 

**Osnovni korak** je, da izberemo manjšo od dveh trenutnih kart, vzamemo manjšo
in jo položimo face down na *output* kup. Tako ostane večja karta še vedno na
svojem kupu in nova se prikaže na tistem, kjer smo vzeli karto.
Korak ponavljamo, dokler so karte na *input* kupih. Ko enega zmanjka, lahko
drugega samo obrnemo in damo na output kup.

#### Časovna kompleksnost

Vsak osnovni korak vzame konstantno veliko časa, ker samo primerjamo dve karti.
Če imata kupa na začetku vsak $n/2$ kart, potem je število osnovnih korakov vsaj
$n/2$ (če ima en kup vse manjše karte od drugega, potem moramo primerjati samo
vsako karto iz tega kupa in nato obrniti karte iz drugega in jih dodati v output).

Največ je $n$ korakov, saj moramo primerjati vsako karto (oziroma $n-1$ korakov, saj
zadnje karte ni treba primerjati, ker ostane samo še en kupček).
Lahko rečemo, da vzame merging nekje med $n/2$ in $n$ korakov, kar je skoraj $n$,
to pa pomeni, da je časovna odvisnot $\Theta(n)$.

### Potek merge sort algoritma

Začnemo z glavnim delom - **MERGE**.

```
0  MERGE(A, p, q, r)

1    nL = q - p + 1 // dolžina A[p:q]
2    nR = r - q     // dolžina A[q+1:r]
3    let L[0:nL - 1] and R[0:nR - 1] be new arrays
-
4    for i = 0 to nL - 1 // copy A[p:q] into L[0:nL-1]
5       L[i] = A[p+1]
6    for j = 0 to nR - 1 // copy A[q+1:r] into R[0:nR-1]
7        R[j] = A[q+j+1]
-
8    i = 0 // i indexes the smallest remaining element in L
9    j = 0 // j indexes the smallest remaining element in R
10   k = p // k indexes the location in A to fill
-
11   // As long as each of the arrays L and R contains an unmerged element
-    // copy the smallest unmerged element back into A[p:r]
12   while i < nL and j < nR
13      if L[i] <= R[j]
14          A[k] = L[i]
15          i++
16      else A[k] = R[j]
17          j ++
18      k++
-
19   // having gone through one of L and R entirely, cope the remainder of
-    // of the other to the end of A[p:r]
20  while i < nL
21      A[k] = L[i]
22      i++
23      k++
24  while j < nR
25      A[k] = R[j]
26      j++
27      k++
```

Podseznama $A[p:q]$ in $A[q+1:r]$ kopira v tmp seznama L in R (left, right), nato
združi vrednosti iz L in R nazaj v $A[p:r]$. Nato vrstica 3 ustvari seznama L in R,
dolžine nL in nR. For loop na vrsticah 4-5 kopira podseznam $A[p:q]$ v L in for loop
na vrsticah 6-7 kopira podseznam $A[q+1:r]$ v R.

Vrstice 8-18 opravljajo osnovni korak. While loop na vrsticah 12-18 vsakič znova
najde najmanjšo vrednost v L ali R, ki se mora kopirati nazaj v $A[p:r]$. Indeks
k nosi položaj v $A$, ki se bo zapolnil, indeksa i in j pa nosita položaj elementa
v L in R.

Ščasoma se cel L ali R kopira nazaj v $A[p:r]$ in loop se ustavi. Če se ustavi, ker
je bil npr. cel R kopiran nazaj, je to zato, ker pogoj na vrstici 12 ne drži več 
za j. Še vedno pa velja, da je $i < nL$, torej se mora še nek del L kopirati nazaj in
vse te vrednosti so največje med L in R elementi.

V tem primeru loop na 20-23 kopira te ostale elemente iz L na zadnjih nekaj položajev
od $A[p:r]$. Ker je $j=nR$, se loop na 24-27 izvede 0-krat. 

#### Časovna zahtevnost

Da bi videl, da je časovna zahtevnost algoritma res $\Theta(n)$, kjer je $n=r-p+1$,
vidi, da vrstice 1-3 in 8-10 delajo konstantno časa in for loop na 4-7 teče
$\Theta(nL+nR) = \Theta(n)$ časa. 

Vsaka iteracija while loopov na 12-18, 20-23 in 24-27 kopira natanko en element 
iz L ali R nazaj v A in vsaka vrednost je kopirana v A natanko enkrat. To pomeni, da
je število korakov vseh treh loopov skupaj natanko $n$. Ker vzame vsaka iteracija
konstantno veliko časa, je časovna zahtevnost teh loopov $\Theta(n)$.

---

Zdaj lahko **MERGE** del uporabimo kot *subroutine* v merge sort algoritmu.

```
0 MERGE-SORT(A, p, r)
-
1   if p >= r             // zero or one element?
2       return
-
3   q = floor((p+r)/2)    // midpoint of A[p:r]
4   MERGE-SORT(A, p, q)   // recursively sort A[p:q]
5   MERGE-SORT(A, q+1, r) // recursively sort A[q+1:r]
-
6   // Merge A[p:q] and A[q+1:r] into A[p:r]
7   MERGE(A, p, q, r)
```

Base case je, ko je p enak r (podseznam ima samo en element in je sortiran), drugače
mora veljati $p \leq r$ in MERGE-SORT izvede divide, conquer, combine korake. Divide
korak izračuna nov q indeks, ki razdeli $A[p:r]$ v nova podseznama, oba z $n/2$
elementov.

Primer za 8 elementov (n = 8):

```
         12,3,7,9,14,6,11,2

      12,3,7,9       14,6,11,2

    12,3    7,9     14,6    11,2

   12   3  7   9   14   6  11   2

    3,12    7,9     6,14    2,11

      3,7,9,12       2,6,11,14

         2,3,6,7,9,11,12,14
```

Časovna odvisnost je torej $\Theta(n)$

## Analiziranje časovne odvisnosti deli-in-vladaj algoritmov

Rekurzivne klice funkcij opišemo z *recurrence* enačbami. Naj bo zdaj $T(n)$
worst-case running time na problemu velikosti $n$. Če je problem dovolj majhen,
npr. za $n < n_{0}$ za nek $n_{0} > 0$, vzame rešitev konstantno veliko
časa, torej $\Theta(1)$.

Naj pa delitev problema poda $a-$mnogo podproblemov, vsak velikosti $n/b$, torej
$1/b$ velikosti originalnega problema.

> Za merge sort je vsak podproblem velikosti $n/2$, torej $1/2$ originalnega
> problema.

Zdaj vzame $T(n/b)$, da se reši en podproblem (velikosti $n/b$), torej vzame
$a \times T(n/b)$ časa, da se rešijo vsi. Če vzame $D(n)$ časa, da se problem
razdeli na podprobleme in $C(n)$ časa, da se podproblemi združijo v rešitev,
dobimo to recurrence enačbo:

$$
T(n) = \begin{cases}
\Theta(1); &n < n_{0} \\
D(n) + a \times T(n/b) + C(n); &n \geq n_{0}
\end{cases}
$$

### Analiziranje časovne zahtevnosti merge sort

Worst-case running time za merge sort:

- **Divide**: ta korak samo izračuna sredino (pod)seznama, kar je konstantno,
torej je $D(n) = \Theta(1)$.
- **Conquer:** Rekurzivno reši dva podproblema, vsak velikosti $n/2$, kar je
potem $2 \times t(n/2)$.
- **Combine:** MERGE postopek na seznamu $n$ elementov potrebuje $\Theta(n)$
časa, torej je $C(n) = \Theta(n)$.

Recurrence enačba je zdaj enaka $T(n) = \Theta(1) + 2 \times T(n/2) + \Theta(n)$. 
Ker je $\Theta(1) + \Theta(n)$ lineana funkcija $n$, vzamemo samo $\Theta(n)$. Reccurence 
enačba za worst-case merge-sort je torej:

$$
T(n) = 2 \times T(n/2) + \Theta(n)
$$

> Glej poglavje 4 za master theorem.


