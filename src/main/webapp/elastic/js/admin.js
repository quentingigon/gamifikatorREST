


function toggleListApp(e){
    document.getElementById(e.target.id).classList.toggle("app--open");
}


function toggleL(){
//    location.href='admin?email='+email;
    document.getElementById("showDev").classList.toggle("showDevVisible");
}

function toggleExit(e){
    document.getElementById(e.target.id).parentElement.classList.toggle("_open");
}

function switchTheme(e){
    document.getElementsByTagName("BODY")[0].classList.toggle("theme2");

}