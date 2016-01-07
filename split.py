"""Outputs the LaTeX source for a single chapter."""

import sys

def main(tex, num):
    src = open(tex)
    # copy the preamble
    for line in src:
        print line,
        if line.startswith("\\begin{document}"):
            print "\\setcounter{chapter}{" + str(num - 1) + "}\n"
            break
    # copy the chapter
    i = 0
    for line in src:
        if line.startswith("\\backmatter"):
            break
        if line.startswith("\\chapter{"):
            i += 1
            if i > num:
                break
        if i == num:
            print line,
    print "\\end{document}"

if __name__ == "__main__":
    if len(sys.argv) == 3:
        main(sys.argv[1], int(sys.argv[2]))
    else:
        print "Usage: python split.py MAIN.tex NUMBER"
