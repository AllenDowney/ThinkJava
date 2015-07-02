F=thinkjava

all:
	pdflatex $(F)
	makeindex $(F)
	pdflatex $(F)
	pdflatex $(F)

clean:
	rm -f $(F).aux $(F).idx $(F).ilg $(F).ind $(F).log $(F).out $(F).toc
