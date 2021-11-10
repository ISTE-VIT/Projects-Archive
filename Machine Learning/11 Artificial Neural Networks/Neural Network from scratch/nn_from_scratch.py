"""
Here, we build a neural network without any library funtions.
The network will be trained to do addition operation as a use case
"""

# Imports
import numpy as np
import math
from random import random

# Create a neural network class
class NN:
    def __init__(self,num_inputs=2,num_hidden=[3,5],num_outputs=2):

        # initialize number of input, hidden and output neurons
        self.num_inputs = num_inputs
        self.num_hidden = num_hidden
        self.num_outputs = num_outputs

        # initalize weights and bias arrays
        self.weights = []
        self.bias = []

        # concatinate all the layers on the network
        # layers will look something like this by default --> [2,3,5,2]
        layers = [num_inputs] + num_hidden + [num_outputs]

        # calculate random intial weights
        for i in range(0,len(layers)-1):
            w = np.random.randn(layers[i],layers[i+1])
            self.weights.append(w)

        # save derivatives per layer
        derivatives = []
        for i in range(len(layers) - 1):
            d = np.zeros((layers[i], layers[i + 1]))
            derivatives.append(d)
        self.derivatives = derivatives

        # save activations per layer
        activations = []
        for i in range(len(layers)):
            a = np.zeros(layers[i])
            activations.append(a)
        self.activations = activations

    #------------------------------------------
    # feed forward
    def forward(self,input_value):
        intermediate = input_value
        self.activations[0] = intermediate

        # do a forward pass from input to output
        for i, w in enumerate(self.weights):
            layer1 = np.dot(intermediate,w)
            intermediate = self.sigmoid(layer1)
            self.activations[i+1] = intermediate

        return intermediate

    #------------------------------------------
    # apply backward
    def backward(self,error):
        # iterate backwards through the network layers
        for i in reversed(range(len(self.derivatives))):


            # backpropogate the next error
            activations = self.activations[i+1]
            delta = error * self.sigmoid_der(activations)
            delta_re = delta.reshape(delta.shape[0], -1).T
            current_activations = self.activations[i]
            current_activations = current_activations.reshape(current_activations.shape[0],-1)
            self.derivatives[i] = np.dot(current_activations, delta_re)
            error = np.dot(delta, self.weights[i].T)

    #------------------------------------------
    def train(self, inputs,targets,epochs,learning_rate):

        #forward pass and backward pass for every input
        for i in range(epochs):
            sum_errors = 0
            j=0
            for input in inputs:
                target = targets[j]
                output = self.forward(input)
                error = target - output
                self.backward(error)
                self.gradient_desc(learning_rate)
                sum_errors+=self._mse(target,output)
            print("Error: {} at epoch {}".format(sum_errors / len(inputs), i+1))

        print("Training complete!")
        print("=====")

    #------------------------------------------
    def gradient_desc(self,learning_rate=1):
        for i in range(len(self.weights)):
            weights = self.weights[i]
            derivatives = self.derivatives[i]
            weights += derivatives * learning_rate

    #------------------------------------------
    def sigmoid(self,x):
        return 1/(1+np.exp(-x))

    #------------------------------------------
    def sigmoid_der(self,x):
        return x * (1.0 - x)

    #------------------------------------------
    def _mse(self, target, output):
        return np.average((target - output) ** 2)

if __name__ == "__main__":

    #dataset for training model to perform addition
    inputs = np.array([[random()/2 for _ in range(2)] for _ in range(1000)])
    targets = np.array([[i[0] + i[1]] for i in inputs])

    n = NN(2,[5],1)
    n.train(inputs, targets, 100, 1)

    # input for prediction
    input = np.array([0.3, 0.1])
    target = np.array([0.4])

    # prediction
    output = n.forward(input)

    print()
    print("Our network believes that {} + {} is equal to {}".format(input[0], input[1], output[0]))
