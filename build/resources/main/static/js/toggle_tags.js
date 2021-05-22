

var tags = document.querySelector(".tags");
var tagsToggle = document.querySelector(".tags-toggle");

var hide = true;

tagsToggle.addEventListener("click", function(e) {
    if (hide){
        tt.src = '/img/togglebutton2.png';
        hide = false;
    }else{
        tt.src = '/img/togglebutton1.png';
        hide = true;
    }
  tags.classList.toggle("hidden");
});

