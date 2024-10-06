# Časovna odvisnost algoritmov

## Primer insertion sort

    1: for j = 2 to length(A) do          # zanka (1)
    2:   key = A[j]                        # operacija (2)
    3:   i = j - 1                         # operacija (3)
    4:   while i > 0 and A[i] > key do     # zanka (4)
    5:     A[i + 1] = A[i]                 # operacija (5)
    6:     i = i - 1                       # operacija (6)
    7:   A[i + 1] = key                    # operacija (7)

Zdaj določimo časovno odvisnost algoritma za najboljši in najslabši
primer.

### Najslabši primer

    for j = 2 to length(A)

Zunanja zanka teče $n-1$-krat, saj začnemo z drugim elementom. Število
iteracij je odvisno od dolžine seznama $A$.

    while i > 0 and A[i] > key

Notranja zanka mora v najslabšem primeru prestaviti vse prejšnje
elemente, torej teče $j-1$-krat ($j-1$ je najmanjši element)

Vsakič, ko zunanja zanka zmanjša $j$ za 1, ko gre na naslednjo
iteracijo, zmanjša število možnih iteracij notranje zanke, torej
notranja zanka izvede največ $j-1$ primerjav.

---

Tako imamo skupno število operacij kot vsoto:

$$
1 + 2 + 3 + \dots + (n-1) = \frac{n(n-1)}{2}
$$

Ta izraž aproksimiramo z $O(n^{2})$.

### Najboljši primer

Elementi so že urejeni, a vseeno gre algoritem skozi vsak
element v polju in naredi $n$ primerjav ($n$ je število elementov
v seznamu $A$).

## Primer merge sort

### Koncept

Lahko si predstavljaš, da imaš vrečo števil in nekomu rečeš, da
bo dobil 10 eur, če povleče neko številko iz vreče. Koliko možnosti je,
da povleče ven številko? $n!$, kjer $n = \text{število števil v vreči}$.

Ker mu hočeš (navidezno) olajšat izbiro, rečeš, da se lahko vsako rundo
odloči za to, da razdeli vsebino vreče na dve vreči. Tako bo namesto
naključne izbire med 10 števili z 10% verjetnostjo, da je pravo, razdelil
vsebini in tako povečal svojo verjetnost, da izbere pravo število.

> Ti kot game master veš točno kje je število, ki ga mora poiskati.
> Kako boš razdelil vreče, da čimbolj zmanjšaš verjetnost, da bo izbral
> pravo vrečo?

Recimo, da razdeliš vrečo 10 števil na dve vreči, eno s 3 in drugo s 7.
Kje je bolj verjetno, da se nahaja njegova številka? V vreči s sedmimi
števili. To pomeni, da se bo vedno odločil za vrečo z več števili.

> Kako bi torej dosegel, da vsakič izbere naključno vrečo? Vreči deliš
> na enako velikost (vsebino vreč prepoloviš).

Ko pride do tega, da vreč ne moremo delit več, se mora odločiti v kateri vreči
misli, da je število. Ker smo vedno delili na 2, ima na koncu 50% verjetnost,
da izbere pravo vrečo (vsaka vreča ima 1 število).

### Algoritem

    Merge-Sort(A, p, r):
        1: if p < r then
        2:   q = (p + r) // 2
        3:   Merge-Sort(A, p, q)
        4:   Merge-Sort(A, q + 1, r)
        5:   Merge(A, p, q, r)

*Merge sort* je bolj učinkovit kot *insertion sort*, saj seznam
razdeli na polovice in nato uredi vsako polovico, na koncu pa jih
združi nazaj.

Prav tako je rekurziven algoritem, zato ima manjšo prostorsko
zahtevnost.

### Analiza časovne zahtevnosti

Seznam vedno deli na 2 polovici, dokler je pride do podseznamov
velikosti 1 (deli dokler ne more več). Število delitev je odvisno od
*logaritma dolžine seznama*. Za seznam dolžine $n$, je globina delitev
enaka $\log_{2}n$.

---

V bistvu gre za to, da merge sort išče ustrezno permutacijo, ki je urejena.
Predstavljaj si seznam:

    A = {6,7,8,9,10,1,2,3,4,5}

Algoritem vsako iteracijo preveri, ali je seznam urejen. Vidimo, da ni.
Zdaj še dvakrat pokliče merge sort - prvič z levo polovico podatkov,
drugič z desno polovico podatkov:

    mergeSort(6,7,8,9,10)
    mergeSort(1,2,3,4,5,6)

Algoritem preveri ali sta polovici urejeni. Ker sta urejeni, ju združi v
pravem vrstnem redu (najprej drugo, nato prvo).

---

Zdaj pa si predstavljal, da ne poznaš permutacije podatkov. Vse možne
permutacije so $n!$.

Algoritem bo moral seznam deliti na pol največ dokler ne bo moral
več, t.j. ko bo podseznam dolžine 1.

                      n!
                      |
              -----------------
              |               |
             n!/2            n!/2
              |               |
         ------------       ------------
         |          |       |          |
       n!/2^2    n!/2^2   n!/2^2     n!/2^2

Najprej deli podatke na polovice ($2$), nato deli na četrtine ($2^2$), itd.
Deli dokler ne more delit več, torej, ko bo $\frac{n!}{2^k}=1$, kjer je
$k$ število korakov, da izvede urejanje.

To pomeni, da potrebuje algoritem vsaj...

$$
\frac{n!}{2^k}=1 \Rightarrow k = \log_{2} n!
$$

...vsaj $\log_{2} n!$ oziroma $\log n!$ korakov.

---

Vsakič, ko hoče združit seznama, mora primerjati elemente.
Združevanje dveh podseznamov velikosti $n/2$ zahteva čas $O(n)$,
saj vsak element primerjamo in prestavljamo enkrat.

$$
T(n) = 2T (\frac{n}{2}) + O(n)
$$

To pomeni, da vsakokrat razdelimo problem na dve polovici (deli in
vladaj), kar vodi do dveh rekurzivnih klicev $T(n/2)$, nato pa v
linearnem času $O(n)$ združimo podseznama.

### Master izrek

Rekurzivni algoritmi pogosto sledijo vzorcu, kjer se problem
deli na manjše podprobleme. Njihovo časovno zahtevnost pogosto
analiziramo s pomočjo *master izreka*:

$$
T(n) = a \times T(\frac{n}{b}) + O(n^{d})
$$

- $n$: velikost vhodnih podatkov
- $a$: število rekurzivnih klicev
- $b$: koliko zmanjšamo vhodne podatke v vsakem klicu
- $O(n^{d})$: čas potreben za združevanje rezultatov

> Kot je razvidno, T(n) je rekurzivna funkcija, saj se kliče
> sama znotraj sebe. a je število rekurzivnih klicev, kar pomeni,
> da 2 × T(n) pomeni, da bodo ustvarjeni 3-je klici funkcije.

---

Parametri za merge sort so torej:

- $a=2$: dva rekurzivna klica
- $b=2$: problem delimo na polovico
- $d=1$: združevanje zahteva $O(n)$, časa ($O(n^{d}) = O(n^{1}) = O(n)$)

Master izrek pravi potem, da je

$$
T(n) = 2 \times T \times (\frac{n}{2}) + O(n)
$$

Če še bolj opišem:

- $T(\frac{n}{2})$ je rekurzivni klic s polovico trenutnih elementov
- kličemo dvakrat vsako iteracijo, torej $2 \times T(\frac{n}{2})$
- ko sta seznama urejena, ju združimo v linearnem času $O(n)$

---

Da bi zdaj s pomočjo izreka določili kompleksnost algoritma, upoštevamo
naslednje 3 možne rešitve glede na $a$, $b$ in $d$:

$$
T(n) = \begin{cases}
a < b^{d} \Rightarrow O(n^{d}) \\
a = b^d \Rightarrow O(n^d \log n) \\
a > b^d \Rightarrow O(n^{\log_{b}a})
\end{cases}
$$

Ker $a=2$ in $b=2$ in $d=1$, imamo primer $a = b^d$ in rezultat
funkcije je $O(n \log n)$, tako kot smo ugotovili zgoraj.
