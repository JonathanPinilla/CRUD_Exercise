document.addEventListener("DOMContentLoaded", function () {
    const formElement = document.getElementById('formToCreate');

    formElement.addEventListener('submit', function (e) {
        const data = new FormData(this);
        fetch('http://localhost:8080/contact', {
            method: 'POST',
            body: data
        }).then(function (response) {
            return response.text();
        }).then(function (text) {
            console.log(text);
        }).catch(function(error){
            console.log(error);
        });
    });
});