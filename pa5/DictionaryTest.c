#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Dictionary.h"

#define MAX_LEN 180;

int main(int argc, char *argv[])
{
	Dictionary D = newDictionary();

	// insert(D, "foo", "bar");
	// insert(D, "eat", "fish");
	// printDictionary(stdout, D);

	// delete (D, "foo");
	// fprintf(stdout, "%d", size(D));

	// makeEmpty(D);
	// fprintf(stdout, "%d", isEmpty(D));

	// freeDictionary(D);

	// fprintf(stdout, "%d\n", size(D));
	// insert(D, "tet", "val");
	// insert(D, "one", "val1");
	// char *word1[] = {"one", "two", "three", "four", "five", "six", "seven"};
	// char *word2[] = {"foo", "bar", "blah", "galumph", "happy", "sad", "blue"};
	// int i;

	// for (i = 0; i < 7; i++)
	// {
	// 	insert(D, word1[i], word2[i]);
	// }
	// printDictionary(stdout, D);
	// delete (D, "tet");
	// fprintf(stdout, "%d\n", size(D));
	// for (i = 0; i < 7; i++)
	// {
	// 	char *k = word1[i];
	// 	char *v = lookup(D, k);
	// 	printf("key=\"%s\" %s\"%s\"\n", k, (v == NULL ? "not found " : "value="), v);
	// }
	// makeEmpty(D);
	// printf("%s\n", (isEmpty(D) ? "true" : "false"));
}