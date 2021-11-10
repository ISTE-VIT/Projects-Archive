let start = document.getElementById("start-game");
let squares = document.getElementsByClassName("square");
let track = document.getElementById("turn");
let currentPlayer = "X";
let score1 = document.getElementById("scorex");
let score2 = document.getElementById("scoreo");
score1.innerHTML = 0;
score2.innerHTML = 0;
let counter = 0;
let showResult=false;
let resultMessage;
const resultDiv=document.querySelector(".result-message-div");
const resultDivP=document.querySelector(".result-message-div > P ");
function resetScore(){
  score1.innerHTML = 0;
  score2.innerHTML = 0;
}

function modifyPlayer(){
    track.innerText = currentPlayer;
}

function startGame() {
  hidePopUp();
  start.innerHTML = "Restart Game!";
  counter = 0;
  for (let i = 0; i < squares.length; i++) {
    squares[i].textContent = "";
    squares[i].addEventListener("click", draw);
    currentPlayer = "X";
    modifyPlayer();
  }
}

start.addEventListener("click", startGame);

function addListenerToSquares() {
  for (let i = 0; i < squares.length; i++) {
    squares[i].addEventListener("click", draw);
  }
}

function swapTurn() {
  if (currentPlayer == "X") {
    currentPlayer = "O";
    } else {
    currentPlayer = "X";
    }
modifyPlayer();
}

function draw() {
  this.innerText = currentPlayer;
  this.removeEventListener("click", draw);
  counter++;
  swapTurn();
  checkWinner();
  }

function checkWinner(){
  if(winnerX() == true ){
    score1.innerHTML++;
    resultMessage="Congratulations! X Won";
    showResult=true;
    // alert("Congratulations! X Won");
    showPopUp();
    // startGame()
  }
  else if (winnerO() == true){
    score2.innerHTML++;
    resultMessage="Congratulations! O Won";
    showResult=true;
    showPopUp();
    // alert("Congatulations! O Won");
    // startGame()
  }
  else if(counter == 9){
    resultMessage="Game Tied. GG!";
    showResult=true;
    // alert("Game Tied. GG!");
    showPopUp();
    // startGame()
  }
}

function winnerX(){
    for(let i = 0; i <= squares.length;i++){
    if(squares[0].textContent =="X" 
      && squares[1].textContent =="X"
      && squares[2].textContent =="X"){
        return true;
    }
    else if(squares[3].textContent =="X"
      && squares[4].textContent =="X"
      && squares[5].textContent =="X"){
        return true;
    }
    else if(squares[6].textContent =="X"
      && squares[7].textContent =="X"
      && squares[8].textContent =="X"){
        return true;
    }
   else if(squares[0].textContent =="X"
      && squares[3].textContent =="X"
      && squares[6].textContent =="X"){
        return true;
    }
    else if(squares[1].textContent =="X"
      && squares[4].textContent =="X"
      && squares[7].textContent =="X"){
        return true;
    }
   else if(squares[2].textContent =="X"
      && squares[5].textContent =="X"
      && squares[8].textContent =="X"){
        return true;
    }
    else if(squares[0].textContent =="X"
      && squares[4].textContent =="X"
      && squares[8].textContent =="X"){
        return true;
    }
   else if(squares[2].textContent =="X"
      && squares[4].textContent =="X"
      && squares[6].textContent =="X"){
        return true;
    }
    else {
      showResult=false;
      return false;
    }
}

}

function winnerO(){
  for(let i = 0; i <= squares.length;i++){
      if(squares[0].textContent == "O"
    && squares[1].textContent == "O"
    && squares[2].textContent == "O"){
      return true;
  }
 else if(squares[3].textContent == "O"
    && squares[4].textContent == "O"
    && squares[5].textContent == "O"){
      return true;
  }
  else if(squares[6].textContent == "O"
    && squares[7].textContent == "O"
    && squares[8].textContent == "O"){
      return true;
  }
 else if(squares[0].textContent == "O"
    && squares[3].textContent == "O"
    && squares[6].textContent == "O"){
      return true;
  }
  else if(squares[1].textContent == "O"
    && squares[4].textContent == "O"
    && squares[7].textContent == "O"){
      return true;
  }
 else if(squares[2].textContent == "O"
    && squares[5].textContent == "O"
    && squares[8].textContent == "O"){
      return true;
  }
  else if(squares[0].textContent == "O"
    && squares[4].textContent == "O"
    && squares[8].textContent == "O"){
      return true;
  }
 else if(squares[2].textContent == "O"
    && squares[4].textContent == "O"
    && squares[6].textContent == "O"){
      return true;
  }
  else {
    showResult=false;
    return false;
  }
  }

}

function showPopUp()
{
  resultDiv.style.display="flex";
  resultDivP.textContent=resultMessage;
}

function hidePopUp(){
  resultDiv.style.display="none"
}