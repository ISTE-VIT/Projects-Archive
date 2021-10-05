from flask import Flask,render_template,request,jsonify
import yake
from os import system

app = Flask(__name__)

@app.route("/",methods=['GET'])
def home():
    return render_template("index.html")

@app.route("/search",methods=['POST'])
def search():
    kw_extractor = yake.KeywordExtractor()
    text = request.form['experience']
    val= (int)(request.form['ngram'])
    language = "en"
    max_ngram_size = val
    deduplication_threshold = 0.9
    numOfKeywords = 30
    custom_kw_extractor = yake.KeywordExtractor(lan=language, n=max_ngram_size, dedupLim=deduplication_threshold, top=numOfKeywords, features=None)
    keywords = custom_kw_extractor.extract_keywords(text)

    return jsonify(keywords)

if __name__== "__main__":
    app.run(debug=True,use_reloader=True)