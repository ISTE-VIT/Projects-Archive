from rake_nltk import Rake
from nltk.tokenize import word_tokenize
from nltk.probability import FreqDist
rake_nltk_var = Rake()
text = """spaCy is an open-source software library for advanced natural language processing,
written in the programming languages Python and Cython. The library is published under the MIT license
and its main developers are Matthew Honnibal and Ines Montani, the founders of the software company Explosion."""
rake_nltk_var.extract_keywords_from_text(text)
keyword_extracted = rake_nltk_var.get_ranked_phrases()
print(keyword_extracted)

print(len(keyword_extracted))
