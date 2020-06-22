// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
 const greetings = [
  "Hello world!",
  "¡Hola Mundo!",
  "你好，世界！",
  "Bonjour le monde!",
 ];
 // Pick a random greeting.
 const greeting = greetings[Math.floor(Math.random() * greetings.length)];
 // Add it to the page.
 const greetingContainer = document.getElementById("greeting-container");
 greetingContainer.innerText = greeting;
}

function addDate() {
 const date = new Date();
 const dateContainer = document.getElementById("date-container");
 dateContainer.innerText = date;
}

function openSidebar() {
 document.getElementById("mySidebar").style.display = "block";
}

function closeSidebar() {
 document.getElementById("mySidebar").style.display = "none";
}

function displayImage() {
 var x = document.getElementById("fav_animal");
 if (x.style.display === "none") {
  x.style.display = "block";
 } else {
  x.style.display = "none";
 }
}

function hideImage() {
 var x = document.getElementById("fav_animal");
 x.style.display = "block";
}

/** Adds text form DataServlet to the webpage using arrow */
function getText() {
 fetch("/data")
  .then((response) => response.text())
  .then((text) => {
   document.getElementById("places-lived-container").innerText = text;
  });
}

/**fetching the JSON arraylist string from server */
function fetchList() {
 fetch('/data').then(response => response.json()).then((placesLived) => {
  console.log(placesLived);
  const placesLivedElement = document.getElementById('places-lived-container');
  placesLivedElement.innerHTML = '';
  for (var i = 0; i < placesLived.length; i++) {
   placesLivedElement.appendChild(
    createListElement(placesLived[i]));
  }
 });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
 const liElement = document.createElement('li');
 liElement.innerText = text;
 return liElement;
}

var map;

function initMap() {
 var center = {
  lat: 35,
  lng: 0
 }
 var princeGeorge = {
  lat: 53.92,
  lng: -122.75
 };
 var canmore = {
  lat: 51.09,
  lng: -115.3442
 };
 var kamchatka = {
  lat: 53.058,
  lng: 158.632
 };
 var kingston = {
  lat: 44.23,
  lng: -76.48
 };

 var map = new google.maps.Map(document.getElementById('map'), {
  center: center,
  zoom: 2.5
 });
 var PGMarker = new google.maps.Marker({
  position: princeGeorge,
  map: map
 });

 var canmoreMarker = new google.maps.Marker({
  position: canmore,
  map: map
 });

 var kamchatkaMarker = new google.maps.Marker({
  position: kamchatka,
  map: map
 });

var KingstonMarker = new google.maps.Marker({
  position: kingston,
  map: map
 });

var PGContent = '<div id="content">' +
  '<div id="siteNotice">' +
  '</div>' +
  '<h1 id="firstHeading" class="firstHeading">Prince George, B.C.</h1>' +
  '<div id="bodyContent">' +
  '<p><b>Prince George</b>, is a small city located in notherb British Columbia.'+
  ' I has born here in 2000 and spent the first 2 years of my lie in the city' + '</p>'
 '</div>' +
 '</div>';
 var canmoreContent = '<div id="content">' +
  '<div id="siteNotice">' +
  '</div>' +
  '<h1 id="firstHeading" class="firstHeading">Canmore, Alberta</h1>' +
  '<div id="bodyContent">' +
  '<p><b>Canmore</b>, is a small town located in the Alberta Rocky Mountians Since I have been two this has been my main home' + '</p>'
 '</div>' +
 '</div>';
 var kamchatkaContent = '<div id="content">' +
  '<div id="siteNotice">' +
  '</div>' +
  '<h1 id="firstHeading" class="firstHeading">Kamchatka, Russia</h1>' +
  '<div id="bodyContent">' +
  '<p><b>Kamchatka</b>, is a penninsula off the eastern cost of Russia.' +
  'I lived here from when I was 2 until 5.' + '</p>'
 '</div>' +
 '</div>';
 var kingstonContent = '<div id="content">' +
  '<div id="siteNotice">' +
  '</div>' +
  '<h1 id="firstHeading" class="firstHeading">Kingston, Ontario</h1>' +
  '<div id="bodyContent">' +
  '<p><b>Kingston</b>, is a city on lake Ontario. I have spent the last 2 years attending Univeristy here.' +
 '</div>' +
 '</div>';

var PGInfowindow = new google.maps.InfoWindow({
  content: PGContent,
  maxWidth: 320
 });
 var canmoreInfowindow = new google.maps.InfoWindow({
  content: canmoreContent,
  maxWidth: 320
 });
 var kamchatkaInfowindow = new google.maps.InfoWindow({
  content: kamchatkaContent,
  maxWidth: 320
 });
 var kingstonInfowindow = new google.maps.InfoWindow({
  content: kingstonContent,
  maxWidth: 320
 });

var PGMarker = new google.maps.Marker({
  position: princeGeorge,
  map: map,
  title: 'Prince George, B.C.'
 });
 var canmoreMarker = new google.maps.Marker({
  position: canmore,
  map: map,
  title: 'Canmore, Alberta'
 });
 var kamchatkaMarker = new google.maps.Marker({
  position: kamchatka,
  map: map,
  title: 'Kamchatka, Russia'
 });
var kingstonMarker = new google.maps.Marker({
  position: kingston,
  map: map,
  title: 'Kingston, Ontario'
 });

 PGMarker.addListener('click', function() {
  PGInfowindow.open(map, PGMarker);
 });
 canmoreMarker.addListener('click', function() {
  canmoreInfowindow.open(map, canmoreMarker);
 });
 kamchatkaMarker.addListener('click', function() {
  kamchatkaInfowindow.open(map, kamchatkaMarker);
 });
 kingstonMarker.addListener('click', function() {
  kingstonInfowindow.open(map, kingstonMarker);
 });
}

/**
 * Builds the Geo Chart UI.
 */
 function initGeoChart() { 
    google.charts.load('current', {
            'packages':['geochart'],
            'mapsApiKey': 'AIzaSyBCPz2-Fuq6F7AAPq5LslBkTYvvqZIGwQM'
        });
        google.charts.setOnLoadCallback(drawRegionsMap);
        function drawRegionsMap() {
            var data = google.visualization.arrayToDataTable([
            ['Country', 'Popularity'],
            ['Germany', 200],
            ['United States', 300],
            ['Brazil', 400],
            ['Canada', 500],
            ['France', 600],
            ['RU', 900]
            ]);

            var options = {};
            var chart = new google.visualization.GeoChart(document.getElementById('geo-chart'));
            chart.draw(data, options);
      }
 }

/**
 * Builds the comments UI.
 */
//function called by blogposts.html's body onload 
function displayComments() {
 let commentsQuantity = document.getElementById('commentsQuantity').value;
 fetch("/add-comment?commentsQuantity=" + commentsQuantity).then(response => response.json()).then((comments) => {
  const commentContainer = document.getElementById('comments-container');
  commentContainer.innerHTML = "";
  comments.forEach((comment) => {
   commentContainer.appendChild(createComment(comment))
  });
 });
}

function createComment(comment) {
 const nameElement = createHTML('h6', comment.name);
 const timeElement = createHTML('h6', comment.time);
 let headerHTML = document.createElement('div');
 headerHTML.className = "comment-heading";
 let headerElements = [nameElement, timeElement]
 headerElements.forEach((htmlElement) => {
  headerHTML.appendChild(htmlElement)
 });

 const contentElement = createHTML('h6', comment.message);
 let commentHTML = document.createElement('div');
 commentHTML.className = "comment";
 let commentElements = [headerHTML, contentElement];
 commentElements.forEach((htmlElement) => {
  commentHTML.appendChild(htmlElement)
 });
 return commentHTML;
}

function createHTML(type, content) {
 const htmlElement = document.createElement(type);
 htmlElement.innerHTML = content;
 return htmlElement;
}

