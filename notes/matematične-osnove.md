# Matematične osnove

Koncepti in izrazi, ki jih je potrebno znat za analiziranje
algoritmov. Glej *introduction to algorithms, Part VIII, 
Appendix: Mathematical Background*.

## Formule in lastnosti za vsote

### Aritmetične vrste

$$
\begin{align}
\sum_{k=1}^{n} k &= 1 + 2 + ... + n = \frac{n(n+1)}{2} = \Theta(n^2) \\
\sum_{k=0}^{n} k^2 &= \frac{n(n+1)(2n+1)}{6} \\
\sum_{n=0}^{n} k^3 &= \frac{n^2(n+1)^4}{4}
\end{align}
$$

### Linearnost

Naj bo $c \in \mathbb{R}$ in $a_{1}, ... , a_{n}$ ter $b_{1}, ... , b_{n}$,
kjer $n \in \mathbb{N}$:

$$
\sum_{k=1}^{n} (ca_{k} + b_{k}) = c \sum_{k=1}^{n} a_{k} + \sum_{k=1}^{n} b_{k}
$$


