# MNIST Fashion Recognizer

In this project, I built a Fashion Recognizer model with CNN on the MNIST Fashion dataset.

The link to the dataset: https://www.kaggle.com/zalando-research/fashionmnist

### Steps followed:

1. Importing Libraries: Importing the required dependencies for the task
2. Data Loading: Loading the data from local directory and storing it as a `Pandas.DataFrame` object
3. Preprocessing: Converting and reshaping data for it to be used in model fitting
4. Model building: Building a sequential model for CNN by using `Conv2D`, `MaxPool2D` and `Flatted` and finally making a fully connected neural network using `Dense` with `relu` as the activation function
5. Model compiling and fitting: Compiling the model with `adam` optimizer, `sparse_categorical_crossentropy` as the loss and `accuracy` as the metric and then fitting the model with the training data and testing data for 10 epochs
6. Model Evaluation: Evaluating the loss and accuracy graphs for training and testing data
7. Model Performance: Checking the performance of our model by plotting various Images and printing their predicted labels
8. Metrics: Plotting confusion matrix to check if the model is well-tuned for Fashion Image classification.
