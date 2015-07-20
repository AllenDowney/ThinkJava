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
	#rm -rf /home/downey/ThinkJava/.svn
	plastex --renderer=DocBook --theme=book --image-resolution=300 --filename=$(F).xml $(F).tex
	#rm -rf /home/downey/ThinkJava/.svn

xxe:
	xmlcopyeditor ~/ThinkJava/$(F)/$(F).xml &

lint:
	xmllint -noout $(F)/$(F).xml