F=thinkjava

all:
	pdflatex $(F)
	makeindex $(F).idx        # shouldn't need .idx here, but we do
	pdflatex $(F)
	pdflatex $(F)

clean:
	rm -f $(F).aux $(F).idx $(F).ilg $(F).ind $(F).log $(F).out $(F).toc

plastex:
	# Before running plastex, we need the current directory in PYTHONPATH
	# export PYTHONPATH=$PYTHONPATH:.
	python preprocess.py $(F).tex > $(F).plastex
	plastex --renderer=DocBook --theme=book --image-resolution=300 --filename=$(F).xml $(F).plastex
	cd $(F); python ../postprocess.py $(F).xml > temp; mv temp $(F).xml

xxe:
	xmlcopyeditor ~/ThinkJava/$(F)/$(F).xml &

lint:
	xmllint -noout $(F)/$(F).xml

oreilly:
	rsync -a thinkjava/thinkjava.xml atlas	
	rsync -a figs/*.pdf atlas/figs/
	rsync -a figs/*.png atlas/figs/
	cd atlas; git add thinkjava.xml figs/*	
	cd atlas; git commit -m "Automated check in."
	cd atlas; git push

hevea:	thinkjava.tex header.html footer.html
	cp $(F).tex $(F)6.tex
	rm -rf html
	mkdir html
	hevea -O -exec xxdate.exe -e latexonly htmlonly $(F)6
	hevea -O -exec xxdate.exe -e latexonly htmlonly $(F)6
	imagen -png -pdf $(F)6
	imagen -png -pdf $(F)6
	hacha $(F)6.html
	cp up.png next.png back.png html
	mv index.html $(F)6.css $(F)6?*.html $(F)6*.png html
	rm *motif.gif $(F)6.*

DEST = /home/downey/public_html/greent/thinkjava6

distrib:
	rm -rf dist
	mkdir dist
	rsync -a $(F).pdf html dist
	rsync -a dist/* $(DEST)
	chmod -R o+r $(DEST)/*
	cd $(DEST)/..; sh back
