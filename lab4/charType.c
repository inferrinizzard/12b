#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <assert.h>
#include <string.h>

#define MAX_STRING_LENGTH 100

void print_str(FILE *output, char *s, char *name);
void extract_chars(char *s, char *a, char *d, char *p, char *w);

int main(int argc, char *argv[])
{
	FILE *in, *out;
	char *line, *alpha, *num, *punct, *space;
	int lineNum = 0;

	if (argc != 3)
	{
		printf("Usage: %s input-file output-file\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	if ((in = fopen(argv[1], "r")) == NULL)
	{
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	if ((out = fopen(argv[2], "w")) == NULL)
	{
		printf("Unable to write to file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	line = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
	alpha = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
	num = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
	punct = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
	space = calloc(MAX_STRING_LENGTH + 1, sizeof(char));
	assert(line != NULL && alpha != NULL && num != NULL && punct != NULL && space != NULL);

	while (fgets(line, MAX_STRING_LENGTH, in) != NULL)
	{
		lineNum++;
		extract_chars(line, alpha, num, punct, space);

		fprintf(out, "%s", "line ");
		fprintf(out, "%d", lineNum);
		fprintf(out, "%s\n", " contains:");

		print_str(out, alpha, " alphabetic");
		print_str(out, num, " numeric");
		print_str(out, punct, " punctuation");
		print_str(out, space, " whitespace");

		fprintf(out, "\n");
	}

	free(line);
	free(alpha);
	free(num);
	free(punct);
	free(space);

	fclose(in);
	fclose(out);

	return EXIT_SUCCESS;
}

void print_str(FILE *output, char *s, char *name)
{
	fprintf(output, "%d", strlen(s));
	fprintf(output, "%s", name);
	if (strlen(s) == 1)
		fprintf(output, "%s", " character: ");
	else
		fprintf(output, "%s", " characters: ");
	fprintf(output, "%s\n", s);
}

void extract_chars(char *s, char *a, char *d, char *p, char *w)
{
	int i = 0, ptrA = 0, ptrD = 0, ptrP = 0, ptrW = 0;
	while (s[i] != '\0' && i < MAX_STRING_LENGTH)
	{
		if (isalnum((int)s[i]))
		{
			if (isalpha((int)s[i]))
				a[ptrA++] = s[i];
			else if (isdigit((int)s[i]))
				d[ptrD++] = s[i];
		}
		else
		{
			if (ispunct((int)s[i]))
				p[ptrP++] = s[i];
			else if (isblank((int)s[i]))
				w[ptrW++] = s[i];
		}
		i++;
	}
	a[ptrA] = '\0';
	d[ptrD] = '\0';
	p[ptrP] = '\0';
	w[ptrW] = '\0';
}