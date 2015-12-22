"""Searches for large code segments."""

def main():
    src = open("thinkjava.tex")
    lineno = 0
    begin = 0
    size = 0
    for line in src:
        lineno += 1
        if line.startswith("\\begin{code}"):
            begin = lineno + 1
            size = 0
        elif line.startswith("\\end{code}"):
            if size >= 20:
                print "Line", begin, ":", size
        else:
            size += 1

if __name__ == "__main__":
    main()
