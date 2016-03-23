import sys

from Filist import Filist

def main(name, filename, *argv):
    # print the contents of the given file
    ft = Filist(filename)
    ft.sub_lines(r'<programlisting>java', r'<programlisting language="java">')
    i, match = ft.search_lines('<chapter id="development">')
    ft.sub_lines(r'<chapter', r'<appendix', start=i)
    ft.sub_lines(r'</chapter', r'</appendix', start=i+1)
    print ft

if __name__ == '__main__':
    main(*sys.argv)


