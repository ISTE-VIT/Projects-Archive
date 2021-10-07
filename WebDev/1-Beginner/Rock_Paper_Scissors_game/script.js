const rock = document.getElementById('rock');
const scissor = document.getElementById('scissor');
const paper = document.getElementById('paper');
const start = document.getElementById('start')
const main = document.getElementById('main-container');
var choiceDisplay = document.getElementById('choicedisplay');
var firstDisplay = document.getElementById('firstdisplay');

var userPoints, computerPoints;

// choiceDisplay = document.createElement('div');

// choiceDisplay to display what the computer has chosen

var choiceList = ['rock', 'paper', 'scissor']; //choiceList from which the computer gets a random choice

var computerChoice;

// when the user clicks on the start button
start.addEventListener('click', function () {
   userPoints = 0;
   computerPoints = 0;
   firstDisplay.classList.add('displayinitial');
   firstDisplay.innerText = `Computer: ${computerPoints}  User: ${userPoints}`;
   // choiceDisplay.remove();

})

// function to take care of displaying the user's & computer's points, and to display the computer's choice
function finalDisplay(userPoints, computerPoints, computerChoice) {
   firstDisplay.innerText = `Computer: ${computerPoints}  User: ${userPoints}`;
   firstDisplay.classList.add('displayinitial');
   // firstDisplay.appendChild(text);
   // main.appendChild(firstDisplay);
   // const displayChoice = document.createTextNode(`Computer chose ${computerChoice} `);
   choiceDisplay.classList.add('finaldisplay');
   choiceDisplay.innerText = `Computer chose ${computerChoice}`;

   // firstDisplay.remove();
}

// if the user clicks on rock

function rockFunc() {

   computerChoice = choiceList[Math.floor(Math.random() * choiceList.length)];

      if (rock && computerChoice == 'scissor') {
         userPoints = userPoints + 1;
         // choiceDisplay.remove();
         // firstDisplay.remove();
         finalDisplay(userPoints, computerPoints, computerChoice);


      }

      else if (rock && computerChoice == 'paper') {
         computerPoints = computerPoints + 1;
         // choiceDisplay.remove();
         // firstDisplay.remove();
         finalDisplay(userPoints, computerPoints, computerChoice);

      }

      else {
         alert("Play Again");
         // firstDisplay.remove();
         // choiceDisplay.remove();
         // finalDisplay(userPoints, computerPoints, computerChoice);

      }

} //end of function


// when user clicks on paper
function paperFunc() {

   computerChoice = choiceList[Math.floor(Math.random() * choiceList.length)];

      if (paper && computerChoice == 'rock') {
         userPoints = userPoints + 1;
         // choiceDisplay.remove();
         // firstDisplay.remove();
         finalDisplay(userPoints, computerPoints, computerChoice);

      }



      else if (paper && computerChoice == 'scissor') {
         computerPoints = computerPoints + 1;
         // choiceDisplay.remove();
         // firstDisplay.remove();
         finalDisplay(userPoints, computerPoints, computerChoice);

      }

      else {

         alert("Play Again");
         // firstDisplay.remove();
         // choiceDisplay.remove();
         // finalDisplay(userPoints, computerPoints, computerChoice);

      }


} //end of function



// when user clicks on scissor

function scissorFunc() {

   computerChoice = choiceList[Math.floor(Math.random() * choiceList.length)];

      if (scissor && computerChoice == 'paper') {
         userPoints = userPoints + 1;
         finalDisplay(userPoints, computerPoints, computerChoice);
         // choiceDisplay.remove();
         // firstDisplay.remove();
      }

      else if (scissor && computerChoice == 'rock') {
         computerPoints = computerPoints + 1;
         finalDisplay(userPoints, computerPoints, computerChoice);
         // choiceDisplay.remove();
         // firstDisplay.remove();
      }

      else {
         alert("Play Again");
         // firstDisplay.remove();
         // choiceDisplay.remove();
         // finalDisplay(userPoints, computerPoints, computerChoice);

      }


}


rock.addEventListener('click', function () {
   rockFunc();
})

scissor.addEventListener('click', function () {
   scissorFunc();
})

paper.addEventListener('click', function () {
   paperFunc();
})

