document.addEventListener("DOMContentLoaded", function () {
    const getListButton = document.querySelector('#getListButton');
    const createContactRedirButton = document.querySelector('#createContactRedirButton');
    const result = document.querySelector('#result');

    const getContactList = function () {
        result.innerText = 'Loading...';
        fetch('http://localhost:8080/testJson')
            .then(res => res.json)
            .then(data => {
                result.innerText = JSON.stringify(data, null, 2); 
                console.log(data.headers);
            })
            .catch(error => console.log(error));
    };

    const createContactRedir = function () {
        window.location.href = 'http://localhost:8080/createContact.html'
    };

    createContactRedirButton.addEventListener("click", createContactRedir);
    getListButton.addEventListener("click", getContactList);
});