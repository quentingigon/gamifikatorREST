


function toggleListApp(e){
    document.getElementById(e.target.id).classList.toggle("app--open");
}


function toggleL(email){
//    location.href='admin?email='+email;
    document.getElementById("showDev").classList.toggle("showDevVisible");
}

function toggleExit(e){
    document.getElementById(e.target.id).parentElement.classList.toggle("app--open");
}