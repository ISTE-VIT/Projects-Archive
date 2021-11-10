
var displayText = document.getElementById("displaytext"); // storing the ID of the textbox in a variable
const seven = document.getElementById("seven");
const eight = document.getElementById("eight");
const nine = document.getElementById("nine");
const one = document.getElementById("one");
const two = document.getElementById("two");
const three = document.getElementById("three");
const four = document.getElementById("four");
const five = document.getElementById("five");
const six = document.getElementById("six");
const plus = document.getElementById("+");
const minus = document.getElementById("-");
const multiply = document.getElementById("*");
const divide = document.getElementById("dividedby");
const clearVal = document.getElementById("clear");
const equalto = document.getElementById("equalto");
const ans = 0

seven.addEventListener('click',function () {
    displayText.value+=seven.value;
})
eight.addEventListener('click',function () {
    displayText.value+=eight.value;
})
nine.addEventListener('click',function () {
    displayText.value+=nine.value;
})
one.addEventListener('click',function () {
    displayText.value+=one.value;
})
two.addEventListener('click',function () {
    displayText.value+=two.value;
})
three.addEventListener('click',function () {
    displayText.value+=three.value;
})
four.addEventListener('click',function () {
    displayText.value+=four.value;
})
five.addEventListener('click',function () {
    displayText.value+=five.value;
})
six.addEventListener('click',function () {
    displayText.value+=six.value;
})
plus.addEventListener('click',function () {
    displayText.value+=plus.value;
})
minus.addEventListener('click',function () {
    displayText.value+=minus.value;
})
multiply.addEventListener('click',function () {
    displayText.value+=multiply.value;
})
divide.addEventListener('click',function () {
    displayText.value+=divide.value;
})

clearVal.addEventListener('click',function(){
    displayText.value=" ";
})

equalto.addEventListener('click',function(){
    displayText.value = eval(displayText.value);
})




