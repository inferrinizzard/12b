#//Sean Song 1649139 12B/M 13/5 Makefile Makefile
#------------------------------------------------------------------------------
# Makefile for any ADT and its test client
#------------------------------------------------------------------------------

ADT_NAME  = Dictionary
MODE			= Client
SOURCES   = $(ADT_NAME).c $(ADT_NAME)$(MODE).c
OBJECTS   = $(ADT_NAME).o $(ADT_NAME)$(MODE).o
HEADERS   = $(ADT_NAME).h
EXEBIN    = $(ADT_NAME)$(MODE)
MISC 			= Makefile README $(ADT_NAME)Test.c
FLAGS     = -std=c99 -Wall
SUBMIT	  = submit cmps012b-pt.s19 lab5 $(SOURCES) $(HEADERS) $(MISC)

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)

check:
	valgrind --leak-check=full $(EXEBIN) 