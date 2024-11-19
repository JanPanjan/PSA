================
==== INSERT ====
================

------------------------------------------
----- step 1: find insertion position ----
------------------------------------------

Začne tako, da v cur shrani head node. ko je samo head v seznamu, ima vse
next elemente na null.
Naredi seznam updateNodes dolžine max levels, ki bo hranil reference vseh
nodes, ki jih je treba posodobit zaradi novo vstavljenega. Na začetku so vsi
elementi null.

S for loopom gre vertikalno čez vse obstoječe nivoje - če je current max 
level 2, bo šel čez drugi in prvi nivo (kar sta indexa 0 in 1 v head.next[]).

Z while loopom gre horizontalno čez nodes v trenutnem levelu. Pomika se dokler
ne najde null repa (nivoji se obnašajo podobno kot povezani seznami). Za vsak
non-null node preveri, ali ima ključ, ki ga želimo vstaviti. Če ima manjši ključ
kot ta, ki ga vstavljamo, pomeni, da se lahko premaknemo naprej horizontalno.

    (za base case - head je edini node) loop se terminira prvo iteracijo, saj
    so vsi next enaki null.

Posodobi update seznam na indexu od level (npr. 0 in 1 nastavi na head, saj
so vsi next == null). Prav tako se posodobi samo prvi element na indeksu 0,
saj je current level enak 0.

------------------------------------------
----- step 2: current and new levels -----
------------------------------------------

Izračuna level za novi node s coinFlip, ki vedno vrne število v [1, maxLevel].
Naj bo novi level = 2, kar pomeni, da bi morali samo index 0 in 1 posodobiti na
head. To naredi for loop, ki gre od curlevel + 1 (naslednji nivo, ki ga je treba
posodobiti na head) do novega nivoja.

    npr.:
    (curlevel) 0<-     1 nivo
    (newlevel) 0 1<-   2 nivoja
    loop gre od indexa 1 (prvi naslednji prazen nivo), pred indexom 2 pa se ustavi.

Nakoncu posodobi trenutni current max level na novi level in nadaljuje na 
tretji korak.

----------------------------------------------
----- step 3: create and insert new node -----
----------------------------------------------

Ustvari novi node s key in z novimi levels. Zdaj je treba povezati novi node v 
seznam. To naredi tako, da gre s for loopom čez vsak nivo v seznamu (ker se je 
current max nivo posodobil na novi nivo, v našem primeru na 2, gre loop čez prvi 
in drugo nivo - indeksa 0 in 1).

Podobno kot v linked list, poveže najprej novi node na rep od parent node in nato
parent node rep na novi node, da se reference ne izgubijo. To mora narediti za
vsak nivo in sicer tako, da new.next[0] poveže na updateNodes[0].next[0] (kjer je
updateNodes[0] = head, torej head.next[0]), kar pomeni, da je rep od new enak
null (v tem primeru), nato pa še rep od head poveže na new.

    Če bi bilo več nivojev in več različnih nodes, bi se v vsakem nivoju new
    povezal na ta node.

----- end -----

=================
==== SEARCH =====
=================

Diagram trenutnega seznama, po insert ([n] = node s ključem n):

    level index next 
    ----- ----- ----
    64    63    head: --------> null
    63    62    head: --------> null
    ...
    2     1     head: -> [1] -> null
    1     0     head: -> [1] -> null

-----------------------------
----- step 1: empty list -----
-----------------------------

Najprej preveri, ali je list prazen oziroma, če je base nivo (0) od head
povezan na nul, kar implicira, da ni naslednjih nodes.

----------------------------------
----- step 2: search for key -----
----------------------------------

Zdaj se zapelje skozi nivoje s for loopom, tako da začne z najvišjim nivojem
(level = cur max level), glede na insert prej, je najvišji nivo 2.

    Ker je curLevel 2, pomeni, da ima next 2 elementa na indeksih 0 in 1, torej
    level v for loopu nastavimo na 1, namesto 2.

While loop nato preverja nodes horizontalno v vsakem nivoju, dokler ne najde null
repa. Če se ključa ujemata, vrne true, če je ključ od trenutnega node večji od 
iskanega, pomeni, da mora it en nivo nižje, kjer je več nodes.

Primer: search za 9

    level index next 
    ----- ----- ----
    3     1     head: --------> [12] -> [21] -> null
    2     1     head: -> [8] -> [12] -> [21] -> null
    1     0     head: -> [8] -> [9] --> [12] -> [21] -> null

    Loop začne pri level 2 na indeksu 1 in gleda, če sta key od node 12 in 9 enaka.
    Ker nista in ker je 9 manjši od 12, pomeni, da mora biti node z 9 nekje med
    head od nivoja nižje in 12 v nižjem nivoju, zaradi česar dropne en level dol.

    Naslednji nivo ima za head node z 8, torej 9 != 8 in 9 je več kot 8. To pomeni,
    da mora biti node z 9 nekje med 8 in 12, zato gre en node desno in najde 12. Spet
    isti scenarij kot prej, zato dropne en level.

    Zdaj je na najnižjem nivoju, kjer poteka search kot v linked list, zato gre mimo
    node 8 in najde node 9.


Primer: insert 4

    level index next 
    ----- ----- ----
    2     1     head: --------> [2] -> null
    1     0     head: -> [1] -> [2] -> null

    head.next od 1 ni null, ampak je 2, torej primerja 2 s 4 in gre na naslednji if.
    Ker je 2 manj kot 4, pomeni, da se mora 4 vstaviti za 2, zato se mora shraniti
    referenca na drugi node, namesto na head, da bo izgledalo tako:

    level index next 
    ----- ----- ----
    2     1     head: --------> [2] -> [4] -> null
    1     0     head: -> [1] -> [2] -> [4] -> null

    Bug je, ker mi najprej ustvari update seznam, ki je velikosti maxLevel, ampak se
    zasedejo samo nodes do curLevel. Ko se nato izračuna level za novi node, je lahko
    manjši od update arraya, zato mora zadnji loop it do newNodeLevel, namesto do curLevel.