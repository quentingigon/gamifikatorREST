
document.getElementById('toggleAddApp').addEventListener('click', function () {
    [].map.call(document.getElementsByClassName("app"), function(el) {
        el.classList.toggle('app--open');
    });
});

