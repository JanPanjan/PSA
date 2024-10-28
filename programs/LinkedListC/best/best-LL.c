// Do zdaj smo hranili seznam nekako v main funkciji brez strukture. Imeli
// smo node *head, ki je vedno kazal na prvi element v seznamu. Zdaj bi 
// radi linked list zapakirali v svojo strukturo LinkedList. 
// Kaj potrebujemo?
//
// Potrebujemo nek node * head, ki bo kazal na začetek seznama (od tu se
// vedno začnejo operacije na seznamu). Kako bomo hranili naslednje nodes
// v seznamu?
//
// shema seznama: (head)-n1-n2-n3-n4-...-nn
//                ------ ------------...---
//                       -- ---------...---
//                          -- ------...---
//                             -- ---...---
//                                -- ...---
//                                   ...    
//                                   ... --
//
// Kar to pomeni je, da smo as deep as it gets. head hrani podatek o naslednjem
// node, kar smo želeli doseč. Up to me, če se zdaj odločim, da naredim novo
// strukturo:
//
// struct LinkedList {
//     node *head;
// };
//
// Head bo node pointer, ki bo kazal na nek node v seznamu in nato bo vsak node
// kazal na naslednji node v seznamu, therefore imamo seznam tudi brez te strukture,
// samo na NULL ga damo lahko na začetku.
//
// Lahko pa posodobimo naše funkcije insert, remove in find.
//
// Najprej združimo create_node, dumb_insert in better_insert v insert, ki bo nosila
// dobre lastnosti vseh.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
    int data;
    struct Node *next;
};
typedef struct Node node;

void print_ll(node *head);
node *find(node *head, int data);
void insert(node **head, int data);
// void remove(node **head, int data);

// Ustvarimo prazen seznam (en node brez vrednosti).
node *head = NULL;

int main() {
    // ----- vstavimo elemente -----
    insert(&head, 12);
    insert(&head, 54);

    // ----- izpišemo elemente -----
    printf("(head)-");
    print_ll(head);

    // ----- najdemo elemente ----- 
    // NOTE: find vrne node pointer
    printf("found node with data %d\n", find(head, 12)->data);
    printf("found node with data %d\n", find(head, 54)->data);

    // probamo najt enega, ki ne obstaja
    printf("found node with data %d\n", find(head, 9)->data);

    return 0;
}

/// s funckijo izpisemo vse elemente v seznamu
void print_ll(node *head) {
    node *tmp = head;
    printf("%d-", tmp->data);

    if (tmp->next == NULL) { 
        printf("NULL\n");
    } else {
        print_ll(tmp->next);
    }
}

/// s funkcijo bomo iterejtali skozi elemente, dokler
/// ne najdemo željene vrednosti - v tem primeru vrnemo
/// node z vrednostjo, drugače null (pridemo do konca lista)
node *find(node *head, int data) {
    node *tmp = head;

    if (tmp->data == data) {
        return tmp;
    }
    return find(tmp->next, data);
}

/// na začetku ni bilo nič. in potem je poknilo. BOOM! bilo je nekaj
/// tako mora izgledat, ko pokličemo insert funkcijo na našem linked list.
/// nodes dajemo na konec seznama, torej, če je seznam prazen, postane new
/// node novi head, drugače moramo najti kje je konec seznama.
/// NOTE: **head -> *head -> &node(data, next)
/// &(**head) = *head
void insert(node **head, int data) {
    node *new = malloc(sizeof(node));
    new->data = data;
    new->next = NULL;

    if (*head == NULL) {
        *head = new;
    } else {
        node *tmp = *head;
        if (tmp->next == NULL) {
            tmp->next = new;
        } else {
            insert(&tmp, data);
        }
    }
}
