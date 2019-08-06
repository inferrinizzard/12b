#------------------------------------------------------------------------------
#   Makefile for List ADT
#------------------------------------------------------------------------------

# Variables
JARFILE    = ListClient
MAINCLASS  = ListClient
SOURCES    = ListInterface.java ListIndexOutOfBoundsException.java List.java \
             ListClient.java ListTest.java
CLASSES    = ListInterface.class ListIndexOutOfBoundsException.class \
             List.class List\$$Node.class ListClient.class
SUBMIT	  = submit cmps012b-pt.s19 lab6 $(SOURCES) Makefile
# Build Targets
all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	# rm -f Manifest $(CLASSES)
	chmod +x $(JARFILE)

$(CLASSES): $(SOURCES)
	javac -Xlint $(SOURCES)

# Phony Targets
clean:
	rm -f $(JARFILE) $(CLASSES) my-out

run:
	java -jar $(JARFILE) > my-out

diff:
	diff my-out model-out

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES) Makefile