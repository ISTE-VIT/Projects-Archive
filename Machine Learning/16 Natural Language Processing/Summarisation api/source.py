import gensim
from gensim.summarization import summarize
import re


def removeSlash(s):
    s = re.sub("[\"]", '"', s)
    print("\n\n ",s, "\n\n")
    return s



def countWords(s):
    res = len(s.split())
    return res

def countPunctuations(body):
    count = 0
    for i in range(0, len(body)):
        # Checks whether given character is a punctuation mark
        if body[i] in (".!?"):
            count = count + 1
    print(count)
    return (count)

def shorten(text):
    if(countWords(text)<=20 or (countPunctuations(text)<=1)):
        return text
    else:
        #todo: clean the incoming body of text from any []
        #todo: recieved json will have / with quotes remove them
        #todo remove /n if present
        #removeSpecial(text)
        #text = (text)
        summary=summarize(text)
        if(summary==''):
            # print(text)
            # print(summary)
            summary= text

        print(summary)
        return summary

# def ratio(text,rat):
#     summary3=summarize(text,ratio=rat)
#     return summary3

#TODO:not working for integers

# def no_words(text,no):
#     # print(text)
#     n=no
#     summary2=summarize(text,word_count=n)
#     print(summarize(text,word_count=no))
#     print(summarize(text,word_count=n))
#     print(summarize(text,word_count=int(no)))
#
#     # print(summarize(text, ratio=0.5))
#     print("\n")
#     # print(summarize(text, word_count=50))
#     return summary2
