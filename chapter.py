"""Outputs the LaTeX source for a single chapter."""

import sys

def main(n):
    src = open("thinkjava.tex")
    # copy the preamble
    for line in src:
        print line,
        if line.startswith("\\begin{document}"):
            print "\\setcounter{chapter}{" + str(n-1) + "}\n"
            break
    # copy the chapter
    i = 0
    for line in src:
        if line.startswith("\\chapter{"):
            i += 1
            if i > n:
                break
        if i == n:
            print line,
    print "\\end{document}"

if __name__ == "__main__":
    if len(sys.argv) == 2:
        main(int(sys.argv[1]))
    else:
        print "Usage: python chapter.py NUMBER"
