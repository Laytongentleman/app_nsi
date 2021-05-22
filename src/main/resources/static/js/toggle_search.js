var menu = document.querySelector(".menuu");
var menuToggle = document.querySelector(".menu-toggle");
menuToggle.addEventListener("click", function(e) {
  menu.classList.toggle("invisible");
});


var add = document.querySelector(".formadd");
var addToggle = document.querySelector(".add");

addToggle.addEventListener("click", function(e) {
  add.classList.toggle("invisible");

});