#//Sean Song 1649139 12B/M 23/4 Makefile Makefile
FLAGS = -std=c99 -Wall
SOURCES = README Makefile FileReverse.c
OBJECTS = FileReverse.o
EXEBIN = FileReverse
SUBMIT = submit cmps012b-pt.s19 lab3
all: $(EXEBIN)
$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)
$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)
clean :
	rm -f $(EXEBIN) $(OBJECTS)
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
