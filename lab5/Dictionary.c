//Sean Song 1649139 12B/M 13/5 Dictionary.c main c file
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Dictionary.h"

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

typedef struct DictionaryObj
{
	NodeObj *head, *tail;
	int size;
} DictionaryObj;

typedef DictionaryObj *Dictionary;

Dictionary newDictionary()
{
	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D != NULL);
	D->head = NULL;
	D->tail = NULL;
	D->size = 0;
	return D;
}

void freeDictionary(Dictionary *pD)
{
	if (pD != NULL && *pD != NULL)
	{
		if (!isEmpty(*pD))
			makeEmpty(*pD);
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
	if (D == NULL)
	{
		fprintf(stderr, "Dictionary Error: Calling lookup() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	Node ptr = D->head;
	while (ptr != NULL)
		if (strcmp(ptr->key, k) == 0)
			return ptr->value;
		else
			ptr = ptr->next;
	return NULL;
}

void insert(Dictionary D, char *k, char *v)
{
	if (D == NULL)
	{
		fprintf(stderr, "Dictionary Error: Calling insert() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	Node push = newNode(k, v);
	if (D->head == NULL)
		D->head = push;
	else
	{
		Node tail = D->head;
		while (tail->next != NULL)
			tail = tail->next;
		tail->next = push;
	}
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
	Node ptr = D->head;
	if (strcmp(ptr->key, k) == 0)
	{
		D->head = ptr->next;
		freeNode(&ptr);
		D->size--;
	}
	else
		while (ptr->next != NULL)
		{
			Node temp = ptr->next;
			if (strcmp(temp->key, k) == 0)
			{
				ptr->next = temp->next;
				freeNode(&temp);
				D->size--;
			}
			else
				ptr = temp;
		}
}

void makeEmpty(Dictionary D)
{
	if (D == NULL)
	{
		fprintf(stderr, "Dictionary Error: Calling makeEmpty() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	while (D->size > 0)
	{
		Node ptr = D->head;
		D->head = ptr->next == NULL ? NULL : ptr->next;
		freeNode(&ptr);
		D->size--;
	}
}

void printDictionary(FILE *out, Dictionary D)
{
	if (D == NULL)
	{
		fprintf(stderr, "Dictionary Error: Calling print() on NULL Dictionary reference\n");
		exit(EXIT_FAILURE);
	}
	Node ptr = D->head;
	if (ptr == NULL)
		fprintf(out, "\n", NULL);
	while (ptr != NULL)
	{
		fprintf(out, "%s%s%s\n", ptr->key, " ", ptr->value);
		ptr = ptr->next;
	}
}
