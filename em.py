"""Search for \em in exercise (theorem) environments; these will be typeset as
normal font in HTML versions."""

def main():
    num = 0
    inex = False
    for line in open("thinkjava.tex"):
        num += 1
        if "\\begin{exercise}" in line:
            inex = True
            continue
        if "\\end{exercise}" in line:
            inex = False
            continue
        if inex and "\\em" in line:
            print num
            print line

if __name__ == "__main__":
    main()
