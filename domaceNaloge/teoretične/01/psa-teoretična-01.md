# 1. naloga

1. Naslednje funkcije razvrsti v **nepadajočem vrstnem redu** glede na **asimptotično rast** (veliki $O$).

- $f_{1}(n) = 2^{2^{1000000}}$
- $f_{2}(n) = 2^{1000000n}$
- $f_{3}(n) = \dbinom{n}{2}$
- $f_{4}(n) = n \sqrt{ n }$

**Primer:** $f(n) = n$ raste asimptotično počasneje kot $g(n) = n^{2}$. Z drugimi besedami, $f(n) \in O(g(n))$, toda $g(n) \not \in O(f(n))$.

---
## $f_{1}(n)$

$f_{1}(n)$ je konstantna funkcija, saj $2^{2^{1000000}}$ ni odvisno od $n$, zaradi česar velja 

$$
f_{1}(n) \in O(1) \Longleftrightarrow f_{1}(n) = O(1)
$$

## $f_{2}(n)$

Po pravilu $a^{m \cdot n} = (a^{m})^{n} = (a^{n})^{m}$ velja:

$$
f_{2}(n) = 2^{1000000n} = (2^{1000000})^{n} = (2^{n})^{1000000}
$$

Velja, da funkcija raste tako hitro, kot raste $n$.  To pomeni, da $(2^{n})^{1000000}$ raste hitreje kot $(2^{n})^{99999}$ in ta počasneje kot $99998$, itn.

To vodi do tega, da funkcija $f_{2}(n)$ raste vsaj tako hitro, kot $2^{n}$, torej:

$$
f_{2}(n) = O(2^{n})
$$

## $f_{3}(n)$

$f_{3}(n) = \dbinom{n}{2}$ lahko razvijemo po enačbi:

$$
\begin{align}
\dbinom{n}{r} &= \frac{n!}{(n - r)! \cdot r!} \\ \\
\implies \dbinom{n}{2} &= \frac{n!}{(n - 2)! \cdot 2!} \\ \\
&= \frac{{n(n - 1)}\cancel{(n - 2)!}}{\cancel{(n - 2)!} \cdot 2!} \\ \\
&= \frac{n(n - 1)}{2!} \\ \\
&= \frac{n(n-1)}{2}
\end{align}
$$

Torej $f_{3}(n) = \frac{n(n-1)}{2}$ in velja naprej:

$$
\frac{n(n-1)}{2} = \frac{n^{2} - n}{2} = \frac{1}{2} n^{2} - \frac{1}{n} n 
$$

$O-$notacija opisuje zgornjo mejo asimptotičnega obnašasnja funkcij - kako se funkcija obnaša, ko $n$ raste - funkcija ne raste hitreje, kot največji člen v enačbi.

V naši enačbi je največji člen $n^{2}$, saj $n^{2} = n \cdot n = 2n \gt n$. Funkcija ne raste hitreje kot $n^{2}$, zato je to zgornja meja in velja, da je asimptotična rast funkcije:

$$
f_{3}(n) = O(n^{c}); \quad c \geq 2
$$

za vsak $c \geq 2$, oziroma $O(n^{2})$.

## $f_{4}(n)$

Če preuredimo zapis funkcije:

$$
f_{4}(n) = n \sqrt{ n } = n^{1} \cdot n^{1 / 2}
$$

Po pravilu $a^{n} \cdot a^{m} = a^{n + m}$ velja:

$$
n^{1} \cdot n^{1 / 2} = n^{1 + 1 / 2} = n^{3/2}
$$

Funkcija grafično izgleda tako:

```functionplot
---
xLabel: n
yLabel: čas izvajanja
bounds: [0,5,0,10]
disableZoom: true
grid: true
---
f(x) = x^3/2
```

Podobno kot prej rečemo, da funkcija ne raste hitreje kot člen $n^{3/2}$, torej

$$
f_{4}(n) = O(n^{c}); \quad c \geq \frac{3}{2}
$$

## Katera od $f_{3}$ in $f_{4}$ ima večjo asimptotično rast?

$f_{3} = O(n^{2})$ raste hitreje kot $f_{4} = O(n^{3/2})$, saj $n^{3/2} = n^{1.5}$ raste hitreje kot $n^{1}$, a počasneje kot $n^{2}$ (glede na primer iz navodil).

$$
\begin{gathered}
n^{1} < n^{1,5} < n^{2} \\ \\
n < n \cdot n^{1/2} < n \cdot n \\ \\
n < n \cdot n^{1/2} < 2n \\ \\
\end{gathered}
$$

Grafično predstavljeno (modra = $f_{3}$, rdeča = $f_{4}$):

```functionplot
---
xLabel: n
yLabel: čas izvajanja
bounds: [0,1.6,0,2]
disableZoom: false
grid: true
---
b(x) = x^2
r(x) = x^(3/2)
```

## Nepadajoči vrstni red funkcij glede na njihove asimptotične rasti

$$
O(1) < O(n^{3/2}) < O(n^{2}) < O(2^{n}) \implies f_{1} < f_{4} < f_{3} < f_{2}
$$

---

2. Z uporabo definicije velikega $O$ pokaži $n^{1+0,001} \not \in O(n)$.
