"""Changes the indentation level of code listings."""

import sys

def main(tex, level):
    state = 0
    src = open(tex)
    for line in src:

        if len(line) == 1:  # skip blank lines
            print line,

        elif state == 0:  # look for code block
            if line.startswith("\\begin{code}"):
                state = 1
            print line,

        elif state == 1:  # first line of code
            cur = len(line) - len(line.lstrip())
            add = level - cur
            if add < 0:
                sub = -add
                add = 0
            else:
                sub = 0
            print ' ' * add + line[sub:],
            state = 2

        elif state == 2:  # inside a code block
            if line.startswith("\\end{code}"):
                state = 0
                print line,
            else:
                print ' ' * add + line[sub:],

if __name__ == "__main__":
    if len(sys.argv) == 3:
        main(sys.argv[1], int(sys.argv[2]))
    else:
        print "Usage: python indent.py MAIN.tex LEVEL"
