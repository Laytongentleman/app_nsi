"use strict";


var x = document.querySelector(".audio");

var button = document.querySelector(".playbutton");
var button_reload = document.querySelector(".reloadbutton");
var play;
function playAudio() {  
  x.play();
}

function pauseAudio() {
  x.pause();
}

function reloadAudio() {
  x.pause();
  x.currentTime = 0;
  x.play();      
  play = true;
  console.log("reloaded");
  p.src = '/img/pause.png';
}

button.addEventListener('click', function(e) {
  console.log(play)
    if (play == true) {
        pauseAudio();
        p.src = '/img/play.png';
        play = false;
        
    } else {
        playAudio();
        p.src = '/img/pause.png';
        play = true;
    }
})

button_reload.addEventListener('click', function(e) {
    reloadAudio();
})



const init = function(e) {
  let btn = document.querySelector(".pont");
  play = true;
  console.log(btn);
  let audiotime = localStorage.getItem('audio-time')
  x.currentTime = audiotime
  if (localStorage.getItem('play') == "false"){
    play = false;
  }

  if (play == true) {
    playAudio();  
    p.src = '/img/pause.png';
    } else {
    pauseAudio();
    p.src = '/img/play.png';}
  btn.addEventListener('click', function() {
  });
};

document.addEventListener('DOMContentLoaded', function(){
  init();});
 
  function myfun(){
    localStorage.setItem('audio-time', x.currentTime)
    localStorage.setItem('play', play)
}

window.onbeforeunload = function(){
  myfun();
};