
var current_email="";

function toggleListApp(e){
    document.getElementById(e.target.id).classList.toggle("app--open");
}


function toggleL(email){
//    location.href='admin?email='+email;
    document.getElementById("showDev").classList.toggle("showDevVisible");
    if(email !== ""){
        current_email =email;
    }
    document.getElementById("currEmailL").setAttribute('value', current_email);
    document.getElementById("currEmailR").setAttribute("value", current_email);


}

function toggleExit(e){
    document.getElementById(e.target.id).parentElement.classList.toggle("_open");
}

function switchTheme(e){
    document.getElementsByTagName("BODY")[0].classList.toggle("theme2");

}