const express = require('express');
const https = require('https');
const bodyParser = require('body-parser');
const { response } = require('express');

const app = express();
app.use(express.static(__dirname + '/public'));

app.set('view engine', 'ejs');

app.get('/', function (req, res) {
  res.sendFile(__dirname + '/index.html');
});

app.use(bodyParser.urlencoded({ extended: true }));

app.post('/', function (req, res) {
  console.log('Post Recieved.');

  const url = 'https://sv443.net/jokeapi/v2/joke/Programming?type=single';
  https.get(url, function (response) {
    response.on('data', function (data) {
      const jokeData = JSON.parse(data);
      const progJoke = jokeData.joke;

      console.log(progJoke);

      res.render('list', { renderedJoke: progJoke });
    });
  });
});

app.listen(3000, function () {
  console.log('Server running on port 3000.');
});
