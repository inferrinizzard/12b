#------------------------------------------------------------------------------
# Makefile for DictionaryClient.c with macros
#------------------------------------------------------------------------------

ADT     = Dictionary
EXEBIN  = Model$(ADT)Test
SOURCES = $(ADT).c $(EXEBIN).c $(ADT)Test.c
OBJECTS = $(ADT).o $(EXEBIN).o
HEADERS = $(ADT).h
SUBMIT	  = submit cmps012b-pt.s19 pa5 $(SOURCES) $(HEADERS) README Makefile

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check:
	valgrind --leak-check=full $(EXEBIN) 

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)