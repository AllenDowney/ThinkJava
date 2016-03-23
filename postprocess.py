import sys

from Filist import Filist

def main(name, filename, *argv):
    # print the contents of the given file
    ft = Filist(filename)
    ft.sub_lines(r'<programlisting>java', r'<programlisting language="java">')
    ft.sub_lines(r'<chapter id="development">', r'<appendix id="development">')
    ft.sub_lines(r'<chapter id="graphics">', r'<appendix id="graphics">')
    ft.sub_lines(r'<chapter id="debugappendix">', r'<appendix id="debugappendix">')
    print ft

if __name__ == '__main__':
    main(*sys.argv)


