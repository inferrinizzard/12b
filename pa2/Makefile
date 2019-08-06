#//Sean Song 1649139 12B/M 17/4 Makefile Makefile
JAVASRC = Queens.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = Queens
CLASSES = Queens.class
JARFILE = Queens
SUBMIT = submit cmps012b-pt.s19 pa2
all: $(JARFILE)
$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)
$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)
clean:
	rm $(CLASSES) $(JARFILE)
submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
