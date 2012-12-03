FICHIER = rapport
FICHIERS = $(FICHIER).aux $(FICHIER).log $(FICHIER).out $(FICHIER).pdf

all : compilation affichage

compilation :
	pdflatex $(FICHIER).tex

affichage :
	evince $(FICHIER).pdf

clean :
	@-rm $(FICHIERS)
