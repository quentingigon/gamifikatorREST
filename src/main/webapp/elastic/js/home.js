
document.getElementById('toggleAddApp').addEventListener('click', function () {
    [].map.call(document.getElementsByClassName("app"), function(el) {
        el.classList.toggle('app--open');
    });
});


document.getElementById('toggleProfile').addEventListener('click', function () {
    [].map.call(document.querySelectorAll('.profile'), function(el) {
        el.classList.toggle('profile--open');
    });
});


function switchTheme(e){
    document.getElementsByTagName("BODY")[0].classList.toggle("theme2");
}