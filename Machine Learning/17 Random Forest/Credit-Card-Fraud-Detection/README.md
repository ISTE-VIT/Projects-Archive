# AI-Fraud-Detection

The main purpose of this project is to understand and implement the distinct approach of Random forest algorithm to identify fraudulent transactions in a database instead of SVM and Logical Regression Model. The model will be able to identify the transactions with greater accuracy and by comparing the approaches, a more optimal solution can be found. <br>

The Credit Card Fraud Detection Problem includes modelling past credit card transactions with the knowledge of the ones that turned out to be fraud. This model is then used to identify whether a new transaction is fraudulent or not. The aim of the project here is to detect 100% of the fraudulent transactions while minimizing the incorrect fraud classifications. <br>

# Challenges
A robust system needs to be made to detect fraudulent transactions. The following challenges have to be overcome to make sure that the final product is resilient.<br>
The challenge is to recognize fraudulent credit card transactions so that the customers of credit card companies are not charged for items that they did not purchase. Main challenges involved in credit card fraud detection are:<br> <br>
    - Enormous Data is processed every day and the model build must be fast enough to respond to the scam in time. <br>
    - Imbalanced Data i.e., most of the transactions (99.8%) are not fraudulent which makes it really hard for detecting the fraudulent transactions.<br>
    - Data availability, as the data is mostly private.<br>
    - Misclassified Data can be another major issue, as not every fraudulent transaction is caught and reported.<br>
    - Adaptive techniques used against the model by the scammers.<br>

<b> The Matthew’s correlation coefficient (MCC) is used to compare the performance of different models. </b>

# Dataset
The dataset contains transactions made by credit cards in September 2013 by European cardholders, over a span of two days. 492 frauds out of 284,807 transactions are present. The dataset is highly unbalanced, the positive class (frauds) account for 0.172% of all transactions.<br>
	
It contains only numerical input variables which are the result of a PCA transformation. Unfortunately, due to confidentiality issues, the original features and more background information about the data cannot be provided.<br>

# Random Forest Algorithm
The method used here is Random Forest Classifier, which is basically a collection of various decision trees. Random forest adds randomness by searching for best feature among a random subset of features. A subset of the training set is sampled randomly to train each tree and then a decision tree is built. Each node then splits on a feature selected from a random subset of the full feature set. It uses bagging and feature randomness when building each individual tree to try to create an uncorrelated forest of trees and leads to a higher rate of accuracy even for large datasets. <br>

The dataset, as mentioned above, consists of a large volume of data and the method of analysis used by Random forest allows fast analysis and prediction, thus making the model suitable for real-world scenarios. Two basic assumptions of Random Forest:<br>
- There should be some actual values in the feature variable of the dataset so that the classifier can predict accurate results rather than a guessed result.<br>
- The predictions from each tree must have very low correlations.<br>

<b>Note: For more information regarding the project, refer to the Project Report</b>

# Web-App <br>
https://ai-project-ccf.herokuapp.com/
    

