//Sean Song 1649139 12B/M 13/5 Dictionary.c main c file
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Dictionary.h"

const static int tableSize = 101;

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift)
{
	int sizeInBits = 8 * sizeof(unsigned int);
	shift = shift & (sizeInBits - 1);
	if (shift == 0)
		return value;
	return (value << shift) | (value >> (sizeInBits - shift));
}
// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char *input)
{
	unsigned int result = 0xBAE86554;
	while (*input)
	{
		result ^= *input++;
		result = rotate_left(result, 5);
	}
	return result;
}
// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char *key)
{
	return pre_hash(key) % tableSize;
}

typedef struct NodeObj
{
	char *key, *value;
	struct NodeObj *next;
} NodeObj;

typedef NodeObj *Node;

Node newNode(char *k, char *v)
{
	Node N = malloc(sizeof(NodeObj));
	assert(N != NULL);
	N->key = k;
	N->value = v;
	N->next = NULL;
	return N;
}

void freeNode(Node *pN)
{
	if (pN != NULL && *pN != NULL)
	{
		free(*pN);
		*pN = NULL;
	}
}

typedef struct ListObj
{
	NodeObj *head, *tail;
	int size;
} ListObj;

typedef ListObj *List;

List newList()
{
	List L = malloc(sizeof(ListObj));
	assert(L != NULL);
	L->head = L->tail = NULL;
	L->size = 0;
	return L;
}

char *lookupList(List L, char *k)
{
	if (L == NULL)
	{
		fprintf(stderr, "List Error: Calling lookupList() on NULL List reference\n");
		exit(EXIT_FAILURE);
	}
	Node ptr = L->head;
	while (ptr != NULL)
		if (strcmp(ptr->key, k) == 0)
			return ptr->value;
		else
			ptr = ptr->next;
	// for(Node ptr = L->head;ptr!=NULL;ptr=ptr->next)
	// 	if(strcmp(ptr->key, k) == 0)
	// 		return ptr->value;
	return NULL;
}

int isEmptyList(List L)
{
	if (L == NULL)
	{
		fprintf(stderr, "List Error: Calling isEmpty() on NULL List reference\n");
		exit(EXIT_FAILURE);
	}
	return L->size == 0 ? 1 : 0;
}

void insertList(List L, char *k, char *v)
{
	if (L == NULL)
	{
		fprintf(stderr, "List Error: Calling insert() on NULL List reference\n");
		exit(EXIT_FAILURE);
	}
	Node push = newNode(k, v);
	if (L->head == NULL)
		L->head = push;
	else
	{
		// Node tail = L->head;
		// while (tail->next != NULL)
		// 	tail = tail->next;
		// tail->next = push;

		// L->tail->next = push;
		// L->tail = push;

		push->next = L->head;		//edit
		L->head = push;		//edit
	}
	L->size++;
}

void deleteList(List L, char *k)
{
	if (L == NULL)
	{
		fprintf(stderr, "List Error: Calling delete() on NULL List reference\n");
		exit(EXIT_FAILURE);
	}
	if (lookupList(L, k) == NULL)
	{
		fprintf(stderr, "List Error: Key not found in delete()\n");
		exit(EXIT_FAILURE);
	}
	Node ptr = L->head;
	if (strcmp(ptr->key, k) == 0)
	{
		L->head = ptr->next;
		freeNode(&ptr);
		L->size--;
	}
	else
		while (ptr->next != NULL)
		{
			Node temp = ptr->next;
			if (strcmp(temp->key, k) == 0)
			{
				ptr->next = temp->next;
				freeNode(&temp);
				L->size--;
			}
			else
				ptr = temp;
		}
}

void makeEmptyList(List L)
{
	if (L == NULL)
	{
		fprintf(stderr, "List Error: Calling makeEmpty() on NULL List reference\n");
		exit(EXIT_FAILURE);
	}
	for (; L->size > 0; L->size--)
	{
		Node ptr = L->head;
		deleteList(L, ptr->key);
		// freeNode(&ptr);		//edit
	}
}

void freeList(List *pL)
{
	if (pL != NULL && *pL != NULL)
	{
		if (!isEmptyList(*pL))
			makeEmptyList(*pL);
		free(*pL);
		// free(pL);
		*pL = NULL;
	}
}

typedef struct DictionaryObj
{
	List table[101];
	int size;
} DictionaryObj;

typedef DictionaryObj *Dictionary;

Dictionary newDictionary()
{
	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D != NULL);
	for (int i = 0; i < tableSize; i++)
		D->table[i] = NULL;
	D->size = 0;
	return D;
}

void freeDictionary(Dictionary *pD)
{
	if (pD != NULL && *pD != NULL)
	{
		for (int i = 0; i < tableSize - 1; i++)
			freeList(&(*pD)->table[i]); //edit

		free(*pD);
		*pD = NULL;
	}
}

int isEmpty(Dictionary D)
{
	if (D == NULL)
	{
		fprintf(stderr, "Dictionary Error: Calling isEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	return size(D) == 0 ? 1 : 0;
}

int size(Dictionary D)
{
	if (D == NULL)
	{
		fprintf(stderr, "Dictionary Error: Calling size() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	return D->size;
}

char *lookup(Dictionary D, char *k)
{
	char *out = NULL;
	if (D == NULL)
	{
		fprintf(stderr, "Dictionary Error: Calling lookup() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	// for (int i = 0; i < tableSize; i++)
		if (D->table[hash(k)] != NULL && lookupList(D->table[hash(k)], k) != NULL)		//edit
			out = lookupList(D->table[hash(k)], k);		//edit
	return out;
}

void insert(Dictionary D, char *k, char *v)
{
	if (D == NULL)
	{
		fprintf(stderr, "Dictionary Error: Calling insert() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	int h = hash(k);
	if (D->table[h] == NULL)
		D->table[h] = newList();
	insertList(D->table[h], k, v);
	D->size++;
}

void delete (Dictionary D, char *k)
{
	if (D == NULL)
	{
		fprintf(stderr, "Dictionary Error: Calling delete() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	if (lookup(D, k) == NULL)
	{
		fprintf(stderr, "Dictionary Error: Key not found in delete()\n");
		exit(EXIT_FAILURE);
	}
	deleteList(D->table[hash(k)], k);
	D->size--;
}

void makeEmpty(Dictionary D)
{
	if (D == NULL)
	{
		fprintf(stderr, "Dictionary Error: Calling makeEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	for (int i = 0; i < tableSize; i++)
		if (D->table[i] != NULL)
			makeEmptyList(D->table[i]);
	D->size = 0;
}

void printDictionary(FILE *out, Dictionary D)
{
	if (D == NULL)
	{
		fprintf(stderr, "Dictionary Error: Calling print() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	for (int i = 0; i < tableSize; i++)
		if (D->table[i] != NULL)
		{
			Node temp;
			// for (Node tptr = D->table[i]->tail; tptr != D->table[i]->head; tptr = temp)
			for (Node ptr = D->table[i]->head; ptr != NULL; ptr = ptr->next)		//edit
			{
			// 	Node ptr = D->table[i]->head;
			// 	for (; ptr->next != tptr; ptr = ptr->next);
				if (ptr != NULL)
					fprintf(out, "%s%s%s\n", ptr->key, " ", ptr->value);
				temp = ptr;
			}
		}
}
