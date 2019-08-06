#//Sean Song 1649139 12B/M 5/5 Makefile Makefile
FLAGS = -std=c99 -Wall
SOURCES = README Makefile charType.c
OBJECTS = charType.o
EXEBIN = charType
SUBMIT = submit cmps012b-pt.s19 lab4
all: $(EXEBIN)
$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)
$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)
clean :
	rm -f $(EXEBIN) $(OBJECTS)
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
