from __future__ import print_function
import sys
from pyquery import PyQuery
import re
import glob
"""
  Run from project directory - paths are relative from there
"""

index_file = "trinkethtml/index.html"

output_dir = "thinkjava/" # Where to write files locally
web_dir = "/thinkjava/"    # Prefix for links

# Pyquery to get TOC from index
with open(index_file) as index:


    # Process chapter TOC from index.
    indextext = index.read()
    d = PyQuery(indextext)

    files = sorted(glob.glob("trinkethtml/*.html"))

    link_replacements = {}

    # Build link swapping dict
    for i, file in enumerate(files[1:]): # skip book index
        special_chapters = {
            15: "appendix-a.html",
            16: "appendix-b.html",
            17: "appendix-c.html",
            18: "book-index.html"
        }
        # Create new output file for chapter
        if i == 0:
            newfile = "preface.html"
        elif i < 15:
            newfile = "chapter{0}.html".format(str(i))
        else:
            newfile = special_chapters[i]
        # TODO: handle appendices and index
        link_replacements[file.replace('trinkethtml/','')] = newfile

    for i, file in enumerate(files[1:]): # skip book index
        print("Processing: ", file)
        selector = 'div.columns > ul > li:nth-child(' + str(i+1) + ')'
        list_items = d(selector)
        list_items('li').eq(0).addClass('has-dropdown')
        list_items('ul').addClass('dropdown')
        toc = PyQuery('<ul class="right"></ul>').html(list_items)
        thisfile = file.replace('trinkethtml/','')
        newfile = link_replacements[thisfile]
        toc_text = re.sub(thisfile, web_dir + newfile, toc.html())
        #print(toc_text)

        # Extract chapter text
        with open(file) as f:
            chapter_raw = f.read()
        chapter_query = PyQuery(chapter_raw)
        chapter_text = chapter_query(".bookchapter").html()

        # Replace old links
        for old, new in link_replacements.items():
            chapter_text = re.sub(old, web_dir + new, chapter_text)
        #print(chapter_text)

        # TODO: transform chapter text somehow

        # Get title
        title = chapter_query("title").text()
        title += " | Think Java | Trinket"

        print("Making ", newfile)
        with open(output_dir + newfile, 'w') as nf:
            template = """

{% extends 'books/thinkjava/base.html' %}
{% block chaptercontent %}
<div class="row">
<div class="columns small-12">

$body$
</div>

</div>
{% endblock %}

{% block toc %}
$toc$
{% endblock %}

{% block title%}$title${% endblock %}
"""
            template = re.sub('\$title\$', title, template)
            template = re.sub('\$toc\$', toc_text, template)
            template = re.sub('\$body\$', chapter_text, template)
            # convert ids to names
            template = re.sub(r'id\=\"', 'name="', template)
            # form valid <a> elements
            template = re.sub(r'<a (.*?[^<])/>', '<a \g<1>></a>', template, flags=re.M)
            print(template)

            nf.write(template.encode('utf8'))
sys.exit(0)

# Begin prior code

filename = sys.argv[1]

print("Processing TOC for " + filename + "...")

with open(filename, 'r+') as f:
    text = f.read()

with open(filename, 'w') as f:
    f.write('{% extends "books/pfe/base.html" %}\n\n')
    pattern = re.compile(r"{% block toc %}(.*){% endblock %}", re.DOTALL)
    matches = re.findall(pattern, text)
    d = PyQuery(matches[0])
    # first ul gets 'right'
    d('ul').eq(0).addClass('right')
    # first li gets dropdown
    d('li').eq(0).addClass('has-dropdown')
    # second ul is dropdown
    d('ul').eq(1).addClass('dropdown')

    toc = "{% block toc %}\n" + str(d) + "\n{% endblock %}"
    newtext = re.sub(pattern, toc, text)
    f.write(newtext)