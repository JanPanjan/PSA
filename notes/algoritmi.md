# Algoritem

Vsak dobro definiran računski postopek, s katerim rešimo kak problem
Vhodne podatke spremeni v izhodne. Pozorni moramo biti na 4 stvari:

1. ali pravilno deluje?
2. kakšna je časovna zahtevnost? (število korakov glede na velikost problema)
3. kakšna je prostorska zahtevnost (velikost problema)
4. ali se da narediti bolje?

>Delujoče ne pomeni pravilno.

## Pravilnost delovanja

Za primer bomo vzeli algoritem z vstavljanjem:

    for j ← 2 to A.length do
        key ← A[j]
        i ← j − 1
        while i > 0 and A[i] > key do
            A[i + 1] ← A[i]
            i ← i − 1
        end while
        A[i + 1] ← key
    end for

Da algoritem pravilno deluje, ponavadi dokažemo z indukcijo.
To je *izjava o stanju spremenljivk v algoritmu, ki velja za vse
ponovitve (iteracije)*. Dokaz za loop lahko naredimo z indukcijo po
zunanji zanki. To pomeni, če imamo znotraj loopa več loopov, gledamo
samo zunanjega. BŠZS predpostavimo, da so elementi med sabo različni.

1. **Baza indukcije:** če je samo en element ($a_{1}$), je polje urejeno.

2. **Hipoteza:** predpostavimo, da algoritem uredi elemente
$a_{1}, a_{2}, ... , a_{j-1} \Rightarrow a'_{1}, a'_{2}, ... , a'_{j-1}$.

3. **Indukcijski korak:** imamo neko polje elementov $a_{1}, a_{2}, ... , a_{j}$.
Prvih $j-1$ po predpostavki znamo urediti v $a'_{i}$. While zanka se izvaja
dokler je `key` manjši (key je `A[j]`, torej prvi pivot, primerjamo ga z
vsakim naslednjim `A[i]`). Ustavi se pri i-tem elementu in vstavi na to mesto. Tedaj
so elemeti $a'_{1} < a'_{2} < ... < a'_{i} < key$ urejeni. ... in some bollocks.

## Časovna zahtevnost

Časovna zahtevnost nam pove kako bo rasel čas potreben za izvedbo algoritma,
s tem ko raste število vhodnih elementov.

Naj bo $T_{A}(n)$ število izvedenih korakov algoritma $A$ pri vhodnih podatkih
velikosti $n$. Funkciji $T$ rečemo *časovna zahtevnost (ali čas izvajanja)
algoritma $A$*. Predpostavljali bomo, da za izvajanje vsakega koraka
potrebujemo konsntanto veliko časa (izajanje i-te vrstice zahteva $c_{i}$ časa).

    for j ← 2 to A.length do                c1  n
        key ← A[j]                          c2  n - 1
        i ← j − 1                           c3  n - 1
        while i > 0 and A[i] > key do       c4  $\sum_{i=2}^n t_{i}$
            A[i + 1] ← A[i]                 c5  $\sum_{i=2}^n (t_{i}-1)$
            i ← i − 1                       c6  $\sum_{i=2}^n (t_{i}-1)$
        end while
        A[i + 1] ← key                      c8  n - 1
    end for

Velja, da je$n$ dolžina niza $A$, $t_{j}$ naj bo število ponovitev zanke
while pri danem $j$. Zdaj velja torej:

$$
T(n) = c_{1}n + c_{2}(n-1) + c_{3}(n-1) + c_{4}\sum^{n}_{j=2} t_{j} +
c_{5}\sum^{n}_{j=2} (t_{j} - 1) + c_{6}\sum^{n}_{j=2} (t_{j} - 1) +
c_{8}(n-1)
$$

Funkcija $T(n)$ je odvisna od podatkov. Izračunajmo rezultat za najbolšji
primer (podatki so urejeni) in v najslabšem primeru (podatki so urejeni
v obratnem vrstnem redu).

### Najboljši primer

Podatki so urejeni, torej velja $t_{j}=1$. Vsak element se primerja enkrat.
Časovna zahtevnost je $O(n)$.

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

### Najslabši primer

Podatki so urejeni v obratnem vrstnem redu. Vsak loop se bodo izvedli
vsi ukazi. Velja torej $t_{j} = j$. Vsak element se primerja z vsakim.
Časovna zahtevnost je $O(n^{2})$.

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

Ugl, zdaj je $T(n)$ kvadratna funkcija. Algoritem ima v najslabšem primeru
**kvadratno časovno zahtevnost**.

To pomeni, če se vhodni podatki podvojijo, se časovna zahtevnost
poštiri.

## Prostorska zahtevnost

Dodatni potrebni spomin pri izvajanju. To je samo inicializacija spremenljivk
i in j in key. Ker se vsako iteracijo inicializirajo enkrat, neodvisno od
števila vhodnih podatkov, je prostorska zahtevnost v tem primeru
**konstantna - $O(1)$**.

## Ali se da bolje?

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
