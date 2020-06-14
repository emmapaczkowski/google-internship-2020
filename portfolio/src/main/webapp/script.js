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
    for( var i = 0 ; i < placesLived.length; i++) {
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
  var canmore = {lat: 51.09, lng: -115.3442};
  var map = new google.maps.Map(document.getElementById('map'), {center: canmore, zoom: 10});
  var marker = new google.maps.Marker({position: canmore, map: map});
}

/**
 * Builds the comments UI.
 */
//function called by blogposts.html's body onload 
function displayComments() {
  let commentsQuantity = document.getElementById('commentsQuantity').value;
  fetch("/add-comment?commentsQuantity="+commentsQuantity).then(response => response.json()).then((comments) => {
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