"""Outputs the LaTeX source for a single chapter."""

import sys

def main(n):
    print "\\documentclass[12pt]{book}"
    print "\\usepackage{thinkjava}"
    print "\\setcounter{chapter}{" + str(n-1) + "}\n"
    print "\\begin{document}\n\n"
    i = 0
    for line in open("thinkjava.tex"):
        if line.startswith("\\chapter{"):
            i += 1
        if i == n:
            print line,
    print "\\end{document}"

if __name__ == "__main__":
    if len(sys.argv) == 2:
        main(int(sys.argv[1]))
    else:
        print "Usage: python chapter.py NUMBER"
