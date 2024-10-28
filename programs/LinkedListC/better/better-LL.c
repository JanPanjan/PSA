#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Node {
    int data;
    struct Node *next;
};
typedef struct Node node;

void print_ll(node *head);
// new/modified:
node *find(node *head, int data);
node *create_node(int data);
node *dumb_insert(node *head, node *new);
void *better_insert(node **head, node *new);

int main() {
    // ponavadi uporabimo linked list, ko ne vemo, koliko
    // nodes bomo potrebovali, zato jih ne moremo eksplicitno
    // ustvarjati v programu.

    // ----- boljša tvorba nodes -----
    // Ustvarimo funkcijo create_node, ki bo vrnila novi node,
    // s podano vrednostjo (not insert, we get to this later),
    // da lahko začnemo graditi seznam.
    
    // še vedno potrebujemo head (not what I meant)
    // tmp bo placeholder za nove nodes
    node *head;
    node *tmp;

    tmp = create_node(42);
    head = tmp;

    // ----- hočemo vstaviti nov node -----
    // shema: (head)-----n1-NULL
    // shema: (head)-NEW-n1-NULL
    // ko ustvarimo še en node, moramo njegov next shranimi kot
    // n1 (tja kamor kaže head) in head kot novi node
    tmp = create_node(3);
    tmp->next = head;
    head = tmp;

    print_ll(head);

    // proces vstavljanja hočemo poenostaviti, zato ustvarimo funkcijo
    // dumb_insert, ki bo vstavila novi node na začetek seznama.
    tmp = create_node(88);
    head = dumb_insert(head, tmp);

    print_ll(head);

    // da bi še head spremenili v funkciji, moramo poslati noter
    // pointer na pointer ××. to bo nova funkcija better_insert
    tmp = create_node(4);
    better_insert(&head, tmp);

    print_ll(head);

    // NOTE: better_insert je lahko void ali pa node, idk, ker ni
    // važno kaj vrne, ona samo naredi novi node in posodobi head

    // ----- boljši find -----
    // prejšnji find je vrnil 1 ali 0, če je našel oz. ni našel node
    // z vrednostjo, kar je v redu, ampak če želimo, da vrne node
    // z vrednostjo, jo posodobimo tako, da vrne node *.
    tmp = find(head, 42);
    printf("found: %d", tmp->data);

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
/// node z vrednostjo, drugače null (pridemo do konca lista)
node *find(node *head, int data) {
    node *tmp = head;

    if (tmp->data == data) {
        return tmp;
    }

    return find(tmp->next, data);
}

/// funkcija bo ustvarila in vrnila novi node z vrednostjo data
/// alociramo spomin za node, ga zapolnimo in nato vrnemo node
node *create_node(int data) {
    node *new = malloc(sizeof(node));
    new->data = data;
    new->next = NULL;

    return new;
}

/// shema: (head)-NEW-n1-NULL
node *dumb_insert(node *head, node *new) {
    new->next = head;

    return new;
}

/// shema: (head)-NEW-n1-NULL
/// novi node vstavimo na adress kamor pointa head (hence the
/// double pointer **), nato dereferencamo head in nastavimo
/// ga nastavimo na head. to pomeni, da mu zbijemo eno zvezdico :(
/// (right...?), da postane pointer, in nato new (pointer, ki kaže
/// na adress) shranimo v head.
///
/// (a je to zdaj kinda statična funckija? nespomnem se OOP)
void *better_insert(node **head, node *new) {
    new->next = *head;
    *head = new;
}
