# Časovna zahtevnost

Za primer bomo vzeli algoritem z vstavljanjem ali *insertion sort*. Algoritem je 
učinkovit za delo z malo podatki.

    1:  for j ← 2 to A.length do
    2:      key ← A[j]
    3:      i ← j − 1
    4:      while i > 0 and A[i] > key do
    5:          A[i + 1] ← A[i]
    6:          i ← i − 1
    7:      end while
    8:      A[i + 1] ← key
    9:  end for

Preden začnemo analizirati algoritem, se moramo odločiti kateri model
računanja bomo izbrali. Osredotočali se bomo večinoma na *generic one-
processor, RAM* model, saj so naši algortimi računalniški programi.

V RAM modelu se ukazi izvajajo eden za drugim, ne izvajajo se naenkrat
in predstavljamo, da je za vsak ukaz potrebno enako veliko časa (inicializacija
spremenljivk ali pa if ukaz, ipd.).

---

## Kako analiziramo časovno zahtevnost algoritma

Časovna zahtevnost nam pove kako bo rasel čas potreben za izvedbo algoritma,
s tem ko raste število vhodnih elementov. 

Par *pravil* za pomoč:

- Naj bo $T_{A}(n)$ število izvedenih korakov algoritma $A$ pri vhodnih podatkih
velikosti $n$. Funkciji $T$ rečemo *časovna zahtevnost (ali čas izvajanja)
algoritma $A$*. Predpostavljali bomo, da za izvajanje vsakega koraka
potrebujemo konsntanto veliko časa (izajanje i-te vrstice zahteva $c_{i}$ časa).

- Naj velja tudi, da je $t_{j}$ vrednost za $j = 2, 3, ... , n$ enaka številu primerjav
notranje while zanke na vrstici 4 pri nekem določenem $j$ ($j$ pripada zunanjem
for loopu, $i$ pripada notranjem while loopu).

- Ko je eno iteracijo pogoj FALSE, se primerjava v *loop headerju* (vrstica s for ali while),
izvede enkrat več kot *loop body* (vse kar je v loopu).

- Časovna zahtevnost algoritma je vsota časov izvajanja vseh ukazov (ukaz, ki potrebuje
za izvajanje $c_{k}$ korakov in se izvede $m-$krat, ima časovno odvisnost enako $c_{k}m$)

- Časovno zahtevnost algoritma označimo s $T(n)$, kjer je $n$ velikost vhodnih podatkov.

```
    for j ← 2 to A.length do                c1  n
        key ← A[j]                          c2  n - 1
        i ← j − 1                           c3  n - 1
        while i > 0 and A[i] > key do       c4  $\sum_{i=2}^n t_{i}$
            A[i + 1] ← A[i]                 c5  $\sum_{i=2}^n (t_{i}-1)$
            i ← i − 1                       c6  $\sum_{i=2}^n (t_{i}-1)$
        end while
        A[i + 1] ← key                      c8  n - 1
    end for
```

$$
T(n) = c_{1}n + c_{2}(n-1) + c_{3}(n-1) + c_{4}\sum^{n}_{j=2} t_{j} +
c_{5}\sum^{n}_{j=2} (t_{j} - 1) + c_{6}\sum^{n}_{j=2} (t_{j} - 1) +
c_{8}(n-1)
$$

Funkcija $T(n)$ je odvisna od podatkov. Izračunajmo rezultat za najbolšji
primer (podatki so urejeni) in v najslabšem primeru (podatki so urejeni
v obratnem vrstnem redu).

## Najboljši primer

Podatki so urejeni,  kar pomeni, da se primerjava v while headerju izvede 
enkrat na iteracijo $j-$ja in telo zanke se nikoli ne izvede. Velja torej 
$t_{j}=1$ za $j = 2, 3, ... , n$ in najbolša časovna odvisnost je:

$$
\begin{align}
T(n) &= c_{1}n + c_{2}(n-1) + c_{3}(n-1) + c_{4}(n-1) + c_{8}(n-1) \\
     &= (c_{1} + c_{2} + c_{3} + c_{4} + c_{8})n -
(c_{2} + c_{3} + c_{4} + c_{8}) \\
     &= an + b
\end{align}
$$

Vidimo, da je $T(n)$ linearna funkcija. V najboljšem primeru ima algoritem
**linearno časovno zahtevnost**.

## Najslabši primer

Podatki so urejeni v obratnem vrstnem redu. Vsak loop se bodo izvedli
vsi ukazi. To pomeni, da se vsak $A[j]$ element primerja z vsakim elementov
v urejenem podseznamu $A[1:j-1]$, torej $t_{j} = j$ za $j = 2, 3, ... , n$.

Velja torej $t_{j} = j$. Vsak element se primerja z vsakim. While loop exita
samo, ko je $j = 0$. To pomeni:

$$
\sum_{i=2}^n i = \left(\sum_{i=1}^{n} i \right) - 1 = \frac{n(n+1)}{2} - 1
$$

in...

$$
\sum_{i=2}^{n} (i-1) = \sum_{i=1}^{n-1} i
$$

> glej [enačbo (1) v matematične-osnove.md](matematične-osnove.md) 

Najslabši čas izvajanja je zdaj:

$$
\begin{align}
T(n) &= c_1 n + c_2 (n-1) + c_3 (n-1) + c_4 \left(\frac{n(n+1)}{2} -
1\right) + \\
&c_5 \frac{n(n+1)}{2} + c_6 \frac{n(n+1)}{2} + c_8 (n-1) \\
&= \left(\frac{c_4}{2} + \frac{c_5}{2} + \frac{c_6}{2}\right) n^2 +
(c_1 + c_2 + c_3 + \\
&\frac{c_4}{2} + \frac{c_5}{2} + \frac{c_6}{2} +
c_8) n - (c_2 + c_3 + c_4 + c_8) \\
&= a_{2}n^{2} + a_{1}n + a_{0}
\end{align}
$$

Zdaj je $T(n)$ kvadratna funkcija. Algoritem ima v najslabšem primeru
**kvadratno časovno zahtevnost**. To pomeni, če se vhodni podatki podvojijo, 
se časovna zahtevnost poštiri.

---

Pogledali smo najbolši in najslabši čas izvajana. Lahko rečemo, da ima
insertion sort najboljši čas izvajanja $\Theta(n)$ in najslabši čas $\Theta(n^2)$.

Glej naprej za podrobne definicije.

## Ali se da bolje?

> # Glej *designing algorithms na strani 34*.

- z $n$ elementi obstaja $n!$ permutacij
- vsak algoritem za urejanje mora znati urediti vsako permutacijo
- z drugimi besedami, poiskati mora vsako permutacijo
- računanja algoritma si lahko predstavljamo kot drevo, kjer so listi
najdene permutacije in notranja vozlišča primerjave znotraj algoritma
- pri danem $A$ sledi izvajanje algoritma eni poti do lista (imamo eno
permutacijo)
- višina drevesa je najkrajši čas potreben za urejanje in je $\log n$

Po neki enačbi od *Stirling*-a vemo, da je

$$
n! \approx \sqrt{2 \pi n} (\frac{n}{e})^n \cdot C
$$

To pomeni, da je $\log n!$ potem:

$$
\log n! \approx n \log n + n \times C_{1}
$$

## Ocena velikosti funkcij

Za oceno časovne kompleksnosti algoritmov si pomagamo z družinami funkcij:

- $O$: upper-bound (worst case)
- $\Omega$: lower-bound (best case)
- $\Theta$: tight-bound (average case)

### Veliki $O$

Če je neka $f(n) = O(g(n))$, potem rečemo, da $g(n)$ **asimptotično od
zgoraj omejuje $f(n)$**. Vrednosti $f(n)$ nikoli ne bodo presegle
vrednosti $O(g(n))$ od nekega $n$ dalje.

$$
O(g(n)) = \{ f(n) ; (\exists c>0)(\exists n_{0} \in N)(\forall n)
(n>=n_{0} \Rightarrow f(n) <= c \times g(n))\}
$$

### Mali $o$

Če je neka $f(n) = o(g(n))$, potem je $f(n)$ **vedno manjša kot $g(n)$**, ko
večamo $n$.

$$
o(g(n)) = \{ \ f(n) ; (\exists c>0)(\exists n_{0} > 0)(\forall n \geq n_{0})
(f(n) < c \times g(n)) \ \}
$$

Enakovredna definicija:

$$
\lim_{n \rightarrow \infty} \frac{f(n)}{g(n)} = 0
$$

### Velika $\Omega$

Če je neka $f(n) = \Omega(g(n))$, potem rečemo, da $g(n)$ **asimptotično od
spodaj omejuje $f(n)$**. Vrednosti $f(n)$ bodo vedno presegale vrednosti
$\Omega(g(n))$ od nekega $n$ dalje.

$$
\Omega(g(n)) = \{ f(n) ; (\exists c>0)(\exists n_{0} \in N)
(\forall n)(n \geq n_{0} \Rightarrow f(n) >= c \times g(n))\}
$$

### Velika $\Theta$

Če je neka $f(n) = \Theta(g(n))$, potem rečemo, da je $f(n)$ **asimptotično
omejena z g(n)**.

$$
\Theta(g(n)) = \{ \ f(n) ; (\exists c_{1}, c_{2}, n_{0})
(\forall n \ge n_{0} \Rightarrow c_{1}f(n) \ge c_{2}g(n)) \ \}
$$

To je enakovredno enačbi:

$$
( \ f(n) = O(g(n)) \land f(n) = \Omega(g(n)) \ ) \Leftrightarrow f(n) = \Theta(g(n))
$$

### Mali $\omega$

Če je neka $f(n) = \omega(g(n))$, potem je $f(n)$ **vedno večja kot $g(n)$**, ko
večamo $n$.

$$
\omega(g(n)) = \{ \ f(n) ; (\exists c>0)(\exists n_{0} > 0)(\forall n \geq n_{0})
(f(n) > c \times g(n)) \ \}
$$

Enakovredna definicija:

$$
\lim_{n \rightarrow \infty} \frac{f(n)}{g(n)} = \infty
$$

## Intuitivni pomen funkcij

$$
\begin{align}
f(n) &= \omega(g(n)) \approx f(n) > g(n) \\
f(n) &= \Omega(g(n)) \approx f(n) \ge g(n) \\
f(n) &= \Theta(g(n)) \approx f(n) = g(n) \\
f(n) &= O(g(n)) \approx f(n) \le g(n) \\
f(n) &= o(g(n)) \approx f(n) < g(n) \\
\end{align}
$$
