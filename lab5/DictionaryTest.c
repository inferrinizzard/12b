#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Dictionary.h"

#define MAX_LEN 100

int main(int argc, char *argv[])
{
	// FILE *out = fopen(argv[1], "w");
	Dictionary D = newDictionary();
	char *test[] = {"one", "test", "two", "testing", "three", "ow"};
	insert(D, test[0], test[1]);
	insert(D, test[2], test[3]);
	insert(D, test[4], test[5]);
	fprintf(stdout, "%s\n", lookup(D, "one")); //test lookup
	printDictionary(stdout, D);								 // test print
	fprintf(stdout, "%d\n\n", size(D));				 //test size before delete
	delete (D, "two");
	delete (D, "three");
	// delete (D, "one");
	printDictionary(stdout, D);
	fprintf(stdout, "%d\n\n", size(D)); //test size after delete
	makeEmpty(D);											//test makeEmpty
	printDictionary(stdout, D);
	fprintf(stdout, "%s%s\n", "isEmpty = ", isEmpty(D) == 1 ? "true" : "false"); //test empty

	// fprintf(stdout, "%d\n", D == NULL);
	// freeDictionary(&D); //test free
	// fprintf(stdout, "%d", D == NULL);

	return (EXIT_SUCCESS);
}
