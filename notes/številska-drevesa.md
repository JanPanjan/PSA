# Številska drevesa

Številska drevesa so rekurzivne podatkovne strukture. Sestavljajo jih ključi
in vrednosti. Od binarnih dreves se razlikujejo po tem, da imajo **vrednosti
shranjene v listih**, binarna drevesa pa v vozliščih. Notranja vozlišča so
uporabljena le za **usmerjanje poti** (razdelitev na podmnožice. glej primere).

Rekurzivnost je definirana na podlagi enega znaka ključa. **Ključ** je sestavljen
iz abecede (pri biologiji je lahko to npr. $A,C,T,G$, v računalništvu so to
ponavadi biti $0,1$).

Primerjave na vozliščih nas vodijo od korena drevesa do lista. Primerjamo npr.
vrednost trenutnega bita.

Takšni strukturi rečemo **trie** (re-*trie*-val), ker je uporabna za iskanje.
Ko bomo iskali časovno zahtevnost strukture, bomo šteli število poizvedovanj
po črki ključa in ne primerjav ($O(m)$, kjer je $m$ velikost elementov).

---

**Primer:** imamo binarne (bitne) črke abecede $\{0,1\}$. Imamo naslednjo bitno
predstavitev ključev:

| črka | bitna predstavitev | črka | bitna predstavitev |
| -------------- | --------------- | ----------- | ---- |
| A | 00001 | C | 00011 |
| E | 00101 | G | 00111 |
| H | 01000 | I | 01001 |
| J | 01010 | K | 01011 |
| L | 01100 | M | 01101 |
| N | 01110 | O | 01111 |
| P | 10000 | R | 10010 |
| S | 10011 | X | 11000 |
| Z | 11010 |   |       |

Imejmo ključe $\{A,C,E,G,H,I,L,M,R,S\}$. Na polagi te množice izgradimo
podatkovno strukturo.

## Gradnja

- Črke jemljemo po vrsti od prve naprej
- Ob vsaki črki razdelimo množico na dve podmnožici, dokler ni velikost vsake
podmnožice samo 1

Tako smo definirali **invarianco podatkovne strukture** (glej v zvezek za skice).

## Iskanje

Poiščimo `L = 01100`.

1. prvi bit: 0, gremo v levo podstrukturo.
2. drugi bit: 1, gremo v desno podstrukturo.
3. tretji bit: 1, gremo v desno podstrukturo.
4. četrti bit: 0, gremo v levo podstrukturo.
5. peti bit: 0, gremo levo in najdemo 
kandidata za  `L` .

Poiščimo `J = 01010`.

1. prvi bit: 0, gremo v levo podstrukturo.
2. drugi bit: 1, gremo v desno podstrukturo.
3. tretji bit: 0, gremo v levo podstrukturo.
4. četrti bit: 1, ne moremo desno, ker tam ni poddrevesa - `J` ni v strukturi!

Če gremo zdaj levo dokler ne moremo desno, pridemo do ključa `I` (01001). On je
**najbližji sosed** od `J`.

Če iščemo ključ $K = 01011$, tudi najdemo ključ `I`, toda on ni najbližji sosed
od $K$. Zakaj?
