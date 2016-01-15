"""Checks that we have one line per sentence."""

import sys

def main(tex):
    src = open(tex)
    lineno = 0
    # skip to the chapters
    for line in src:
        lineno += 1
        if line.startswith("\\chapter"):
            break
    # for each line of text
    count = 0
    stack = []
    for line in src:
        lineno += 1
        if line == '\n':  # blank
            continue
        if line[0] == '%':  # comment
            continue
        # ignore special environments
        if line.startswith("\\begin{"):
            env = line[7:line.find('}', 7)]
            if env in ["code", "stdout", "tabular", "eqnarray*"]:
                #~ print "PUSH\t" + line,
                stack.append(line)
        if line.startswith("\\end{"):
            env = line[5:line.find('}', 5)]
            if env in ["code", "stdout", "tabular", "eqnarray*"]:
                #~ print "POP\t" + line,
                stack.pop()
        if len(stack) > 0:
            continue
        # get the last character
        end = line[-2]
        if end == ")":
            end = line[-3]
        if end == "'":
            end = line[-4]
        # ignore LaTeX commands
        if line[0] == '\\' and ' ' not in line:
            continue
        if end == '}' or end == ']' or end == '\\':
            continue
        # check for exactly one sentence
        if end not in ['.', ';', ':', '?', '!']:
            print str(lineno) + '\t' + line,
            count += 1
        elif line.find('. ') > -1:
            print str(lineno) + '\t' + line,
            count += 1
        if count == 10:
            break

if __name__ == "__main__":
    if len(sys.argv) == 2:
        main(sys.argv[1])
    else:
        print "Usage: python oneline.py FILE.tex"
