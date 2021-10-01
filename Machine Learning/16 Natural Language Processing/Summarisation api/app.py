from os import error
from flask import Flask, request, jsonify
from source import *

app = Flask(__name__)



@app.route('/', methods=["POST"])
def home():
    data = request.json
    paragraph = data['text']
    response = dict()
    summary1 = shorten(paragraph)
    print("summary is",summary1)
    response["summary1"] = summary1

    return jsonify(response)

# @app.route('/wordcount', methods=["POST"])
# def count():
#     # Input data will be in form of text
#     data = request.json
#     paragraph = data['text']
#     wc = data['wordcount']
#     print("type of wc",type(wc))
#     response = dict()
#
#     summary2=no_words(paragraph,wc)
#
#     print(summary2)
#
#     response["summary2"] = summary2
#
#     return jsonify(response)

# @app.route('/ratio', methods=["POST"])
# def ratio2():
#     data = request.json
#     paragraph = data['text']
#     r = data['ratio']
#     print("type of wc",type(r))
#     response = dict()
#
#     summary3=no_words(paragraph,r)
#
#     # print(summary3)
#
#     response["summary3"] = summary3
#
#     return jsonify(response)



if __name__=="__main__":
    app.run(debug=True)