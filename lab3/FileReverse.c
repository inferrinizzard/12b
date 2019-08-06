#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// char *reverse(char *word);

// char *reverse(char *word)
// {
// 	int length = strlen(word);
// 	char result[length];
// 	for (int i = 0; i < length; i++)
// 		result[length - 1 - i] = word[i];
// 	return result;
// }

int main(int argc, char *argv[])
{
	FILE *in;				/* file handle for input */
	FILE *out;			/* file handle for output */
	char word[256]; /* char array to store words from input file */
	/* check command line for correct number of arguments */
	if (argc != 3)
	{
		printf("Usage: %s <input file> <output file>\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	/* open input file for reading */
	in = fopen(argv[1], "r");
	if (in == NULL)
	{
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}
	/* open output file for writing */
	out = fopen(argv[2], "w");
	if (out == NULL)
	{
		printf("Unable to write to file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}
	/* read words from input file, print on separate lines to output file*/
	while (fscanf(in, " %s", word) != EOF)
	{
		// fprintf(out, "%s\n", reverse(word));
		// fprintf(out, "%s\n", word);
		for (int i = strlen(word) - 1; i >= 0; i--)
			putc(word[i], out);
		fprintf(out, "\n");
	}
	/* close input and output files */
	fclose(in);
	fclose(out);
	return (EXIT_SUCCESS);
	// return 0;
}