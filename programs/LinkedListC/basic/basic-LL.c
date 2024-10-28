// Linked list je podatkovna struktura, sestavljen iz nodes. Vsak node nosi podatek o shranjeni 
// vrednosti (data) in podatek o naslednjem node (next, tail...).
// Na njemu bomo definirali funkcije vstavljanja, brisanja in iskanja elementov.
// Za začetek bo vse enostavno brez funkcij (z izjemo find), da osvojim osnove.
// (ja, moral bi delat v javi, ampak c je bolj zabaven)
#include <stdio.h>
#include <string.h>

// data je shranjena vrednost, pointer next pa kaže na nek 
// node (naslednji node v seznamu)
struct Node {
    int data;
    struct Node *next;
};
// da nam ni treba vedno pisati "struct Node ..."
typedef struct Node node;

void print_ll(node *head);
int find(node *head, int data);

int main() {
    // ustvari 3 nove nodes
    node n1, n2, n3;
    // hranimo pointer na head oziroma prvi node v seznamu
    node *head;
    // shranimo nove vrednosti
    n1.data = 12;
    n2.data = 13;
    n3.data = 14;
    // povežemo jih skupaj
    // hočemo, da so v obliki (head)-n3-n2-n1-NULL
    head = &n3;
    n3.next = &n2;
    n2.next = &n1;
    n1.next = NULL;
    // izpišemo
    print_ll(head);

    // ----- hočemo dodati node na sredino (med 13 in 12) -----

    // shema: (head)-n3-n2-----n1-NULL
    // shema: (head)-n3-n2-NEW-n1-NULL
    // n2 kaže zdaj na n1 in n1 na NULL. 
    // hočemo, da bo n2 kazal na novi node in novi node na n1
    // spremenimo next od n2 in kot next od novega node nastavimo n1

    // WARN: pomembno je, da najprej povežemo novi node z 12 (zdaj
    // kažeta 2 na 12). če najprej ustvarimo next na novo vrednost,
    // izgubimo next na 12!!!
    node n4;
    n4.data = 15;
    n4.next = &n1;
    n2.next = &n4;

    print_ll(head);

    // ----- hočemo dodati node na začetek -----

    // shema: (head)-----n3-n2-n4-n1-NULL
    // shema: (head)-NEW-n3-n2-n4-n1-NULL
    // najprej torej povežemo novi node z n3, da ne izgubimo link
    // nato head na novi node
    node n5 = { .data = 16, .next = &n3 };
    head = &n5;

    print_ll(head);

    // NOTE: ker je next pointer in head hrani adress od prvega, ali bi
    // lahko next od novega noda nastavili kot head? (ja)
    node n6 = { .data = 17, .next = head };
    head = &n6;

    print_ll(head);

    // ----- hočemo odstraniti prvi node -----

    // shema: (head)-n6-n5-n3-n2-n4-n1-NULL
    // hočemo odstraniti n6, torej vse kar moramo narediti je, da
    // nastavimo head na adress od n5, s čimer izgubimo pointer
    // na n6 => removed
    head = &n5;
    print_ll(head);

    // ----- hočemo odstraniti vmesni node -----

    // shema: (head)-n5-n3-n2-n4-n1-NULL
    //                     **
    // n2 odstranimo prav tako, da odstranimo pointer nanj, tako da
    // bo n3 kazal na n4
    n3.next = &n4;
    print_ll(head);

    // ----- hočemo preveriti ali je neka vrednost v seznamu -----
    // shema: (head)-16-14-15-12-NULL
    // recimo, da hočemo najti 12. delajmo se, da ne vemo na katerem
    // mestu se nahaja. moramo iti čez vsak node in preverit ali je
    // njen data == 12. 
    // definiramo funkcijo find(node *head, int data)
    printf("value 12 in list? %d\n", find(head, 12));

    // ali najdemo 1?
    printf("value 1 in list? %d\n", find(head, 1));

    return 0;
}

/// s funkcijo bomo izpisali seznam nodes
/// ustvarimo temporary node za pomoč
/// (head pointer preusmerimo v tmp pointer)
/// izpisujemo, dokler ne naletimo na prazen node (NULL)
/// vsakič prenesemo tmp na next node
void print_ll(node *head) {
    node *tmp = head;

    while (tmp != NULL) {
        printf("%d-", tmp->data);
        tmp = tmp->next;
    }
    printf("NULL\n");
}

/// s funkcijo bomo iterataril skozi elemente, dokler
/// ne najdemo željene vrednosti - v tem primeru vrnemo
/// true, drugače false (pridemo do konca lista)
int find(node *head, int data) {
    node *tmp = head;

    if (tmp->data == data) {
        return 1;
    }
    if (tmp->next == NULL) {
        return 0;
    }

    return find(tmp->next, data);
}
