
import yake
from os import system

kw_extractor = yake.KeywordExtractor()
text = input("Enter the text: ")
language = "en"
max_ngram_size = 1
deduplication_threshold = 0.9
numOfKeywords = 30
custom_kw_extractor = yake.KeywordExtractor(lan=language, n=max_ngram_size, dedupLim=deduplication_threshold, top=numOfKeywords, features=None)
keywords = custom_kw_extractor.extract_keywords(text)

for kw in keywords:
    print(kw[0])

print("\n")
print("Length of the entire text(in words) is")
print(len(text))
print("\n")
wordfreq = []
for w in keywords:
    wordfreq.append(keywords.count(w)/len(text)*100)

print("Keyword Density\n" + str(wordfreq) + "\n")

for sw in keywords:
    img_url="https://www.google.com/search?q="+sw[0]+"&tbm=isch"
    print(img_url)

cls = lambda: system('cls')