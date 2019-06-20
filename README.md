# ThinkJava
LaTeX source for Think Java, 1st edition.
Copyright (c) 2016 Allen Downey and Chris Mayfield.

NOTE: This repository is no longer actively maintained.
See https://github.com/ChrisMayfield/ThinkJava2 for the 2nd edition of Think Java.

Permission is granted to copy, distribute, and/or modify this work under the
terms of the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported
License, which is available at http://creativecommons.org/licenses/by-nc-sa/3.0/.

The original form of this book is the LaTeX source code available from
http://greenteapress.com/wp/think-java/ and https://github.com/AllenDowney/ThinkJava.

The illustrations were drawn using xfig (http://www.xfig.org/) and dia
(https://wiki.gnome.org/Apps/Dia/). These tools are free and open-source.

Compiling the LaTeX source has the effect of generating a device-independent
representation of the book, which can be converted to other formats and printed.

To compile the PDF version from source:

    pdflatex thinkjava
    makeindex thinkjava
    pdflatex thinkjava
    pdflatex thinkjava

The source code includes a Makefile that automates this process.
On Linux, you will need to install texlive-latex-extra and hevea.
