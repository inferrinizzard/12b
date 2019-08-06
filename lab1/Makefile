#------------------------------------------------------------------------------
# Sean Song 1649139 12B/M 4/8 Makefile, Makefile
# Another Makefile with macros
#------------------------------------------------------------------------------
JAVASRC = HelloUser.java HelloUser2.java
SOURCES = README Makefile $(JAVASRC)
MAINCLASS = HelloUser2
CLASSES = HelloUser.class HelloUser2.class
JARFILE = HelloUser HelloUser2
SUBMIT = submit cmps012b-pt.s19 lab1
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
