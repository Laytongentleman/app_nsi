var menu = document.querySelector(".menuu");
var menuToggle = document.querySelector(".menu-toggle");
menuToggle.addEventListener("click", function(e) {
  menu.classList.toggle("invisible");
});