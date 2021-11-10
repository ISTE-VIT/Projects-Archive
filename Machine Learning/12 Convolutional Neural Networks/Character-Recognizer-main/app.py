import tensorflow as tf
model = tf.keras.models.load_model('model_hand.h5')
import streamlit as st
st.write("""
         # Character Recognizer
         """
         )
st.write("This is a simple image classification web app to handwritten letter images")
file = st.file_uploader("Please upload an image file", type=["jpg", "png"])

# Dictionary for getting characters from index values...
word_dict = {0:'A',1:'B',2:'C',3:'D',4:'E',5:'F',6:'G',7:'H',8:'I',9:'J',10:'K',11:'L',12:'M',13:'N',14:'O',15:'P',16:'Q',17:'R',18:'S',19:'T',20:'U',21:'V',22:'W',23:'X', 24:'Y',25:'Z'}

import cv2
from PIL import Image, ImageOps
import numpy as np
def import_and_predict(img, model):
    img = ImageOps.fit(img, (400,400), Image.ANTIALIAS)
    img = np.asarray(img)
    #img_copy = cv2.imread(img)

    img_copy = cv2.GaussianBlur(img, (7,7), 0)
    img_gray = cv2.cvtColor(img_copy, cv2.COLOR_BGR2GRAY)
    _, img_thresh = cv2.threshold(img_gray, 100, 255, cv2.THRESH_BINARY_INV)

    img_final = cv2.resize(img_thresh, (28,28))
    img_final =np.reshape(img_final, (1,28,28,1))
    prediction = model.predict(img_final)
    return prediction

if file is None:
    st.text("Please upload an image file")
else:
    image = Image.open(file)
    st.image(image, width=360)

    prediction = import_and_predict(image, model)
    st.title('Letter Recognized is : ')
    st.title(word_dict[np.argmax(prediction)])
    