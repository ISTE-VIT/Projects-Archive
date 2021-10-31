var height = document.getElementById("heighttext");
var weight = document.getElementById("weighttext");
var heightans = 0;
var finalans = 0;
var finaltext = document.getElementById('final-text');

const calcbutton = document.getElementById("calcbutton");
const clearbtn = document.getElementById('clearbtn');

calcbutton.addEventListener('click',function(){
    // height = height/100;
    heightans = (height.value * height.value);
    finalans = weight.value/heightans;
    finalans = finalans.toFixed(2);
    if(finalans<18.5){
        finaltext.innerHTML = `Your BMI is ${finalans} and it is bad. <br><br> You are malnourished`;
    }
    else if(finalans>=18.5 && finalans<25){
        finaltext.innerHTML = `Your BMI is ${finalans} and it is good. <br><br> You are in safe limits`;
    }
    else{
        finaltext.innerHTML = `Your BMI is ${finalans} and it is very bad. <br><br> You are obese`;
    }
})

clearbtn.addEventListener('click',function(){
    height.value= " ";
    weight.value= " ";
    finaltext.remove();
})