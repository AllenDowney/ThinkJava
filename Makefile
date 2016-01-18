F=thinkjava

all:
	pdflatex $(F)
	makeindex $(F)
	pdflatex $(F)
	pdflatex $(F)

clean:
	rm -f $(F).aux $(F).idx $(F).ilg $(F).ind $(F).log $(F).out $(F).toc

plastex:
	# Before running plastex, we need the current directory in PYTHONPATH
	# export PYTHONPATH=$PYTHONPATH:.
	python Filist.py $(F).tex > $(F).plastex
	plastex --renderer=DocBook --theme=book --image-resolution=300 --filename=$(F).xml $(F).plastex

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
	# replace the pdfs with eps
	sed s/\\.pdf/.eps/g thinkjava.tex > $(F)6.tex
	pdflatex $(F)6
	rm -rf html
	mkdir html
	hevea -fix -O -e latexonly htmlonly $(F)6
# the following greps are a kludge to prevent imagen from seeing
# the definitions in latexonly, and to avoid headers on the images
	grep -v latexonly $(F)6.image.tex > a; mv a $(F)6.image.tex
	grep -v fancyhdr $(F)6.image.tex > a; mv a $(F)6.image.tex
	imagen -png $(F)6
	hacha $(F)6.html
	cp up.png next.png back.png html
	mv index.html $(F)6.css $(F)6*.html $(F)6*.png *motif.gif html

DEST = /home/downey/public_html/greent/thinkjava6

distrib:
	rm -rf dist
	mkdir dist
	rsync -a $(F).pdf html dist
	rsync -a dist/* $(DEST)
	chmod -R o+r $(DEST)/*
	cd $(DEST)/..; sh back
