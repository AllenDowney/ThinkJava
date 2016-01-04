"""Merges a chapter back into the main file."""

import sys

def insert(tex):
    chap = False
    for line in open(tex):
        # don't copy preamble or end document
        if line.startswith("\\chapter{"):
            chap = True
        if line.startswith("\\end{document}"):
            chap = False
        if chap:
            print line,

def main(n, tex):
    src = open("thinkjava.tex")
    # copy the preamble
    for line in src:
        print line,
        if line.startswith("\\begin{document}"):
            break
    # copy each chapter
    i = 0
    for line in src:
        if line.startswith("\\backmatter") or line.startswith("\\chapter{"):
            i += 1
            # insert new chapter
            if i == n + 1:
                insert(tex)
        # skip old chapter
        if i != n:
            print line,

if __name__ == "__main__":
    if len(sys.argv) == 3:
        main(int(sys.argv[1]), sys.argv[2])
    else:
        print "Usage: python merge.py NUMBER TEXFILE"
