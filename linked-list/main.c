#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>


struct Node {
    int value;
    struct Node* next;

};


// create a new node to be added to the list
struct Node* createNode(int value) {
    // allocate memory for the list
    struct Node* newNode = malloc(sizeof(struct Node));
    newNode->value = value;
    newNode->next = NULL;
    return newNode;
}

// inserts node at the end
struct Node* insertNode(int value, struct Node* head ) {
        if ( head == NULL) {
          head =  createNode(value);
        }// end if
    else {
        struct Node* temp=head;
        // traverse list until and stop in the last node to make a new connection

        while (temp->next !=NULL) {
            temp = temp->next;
        }
        temp->next= createNode(value);
    }

    return head;
}


// print out the list
void printList(struct Node* head) {
    if (head==NULL)return;
    struct Node* temp = head;
    // traverse list until null
    while (temp !=NULL) {
        printf("%d ", temp->value);
        temp = temp->next;
    }

}

//For inserting a new node at the beginning
struct Node *insertHead(int value, struct Node* head) {

    if ( head == NULL) {
        head =  createNode(value);
    }else {
        // list is not empty
        struct Node* temp = head;
        struct Node* newNode= createNode(value);
        newNode->next=temp; // newNode added start of the list pointing to temp
        temp=newNode; // temp would be update with the new node in it.
        head=temp; // update the head to point to the new node.

    }

    return head;

}

//Returns true/false or the node pointer if found
bool findValue(struct Node* head , int value) {
    if (head==NULL)return 0;

    struct Node* temp= head;
    while (temp!=NULL) {
        if (temp->value == value) {
            return true;
        }
        temp = temp->next;
    }

    return false;

}
// Remove a node by value
//Reconnect surrounding nodes and free memory

struct Node* removeNode(struct Node* head, int value) {

    if (head==NULL)return head;

    struct Node* temp = head;
    struct Node* pt2= head->next;

    if (head->value==value) {

        head=head->next;
        return head;
    }
    while (pt2!=NULL) {

        if (pt2->value==value) {
            // 1-> 2 -> 3 -> 4 -> 5 -  >6
            // temp pt2
            pt2 = pt2->next;
            temp->next=pt2;
            head=temp;
            return head;
        }
        temp = temp->next;
        pt2 = pt2->next;

    }

    return head;

    }

int main(void) {
    struct Node *head = NULL;

    head = insertNode(10,head);
    head=insertNode(20,head);
    head=insertNode(30,head);
    head=insertNode(40,head);
    head=insertNode(50,head);
    printList(head);
    head=insertHead(0,head);
    printf("\n \n");
    printList(head);
    bool search = findValue(head, 40);
    if (search == 1) {
        printf("\n true" );
    }else {
        printf("\n false" );
    }
    head= removeNode(head,0);
    printf("\n \n");
    printList(head);
    printf("\n \n");







}