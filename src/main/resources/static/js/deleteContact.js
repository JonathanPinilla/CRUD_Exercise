document.addEventListener("DOMContentLoaded", function () {
    /**
     * @param formElement loads the form information bi its id
     */
    const formElement = document.getElementById('formToDelete');

    /**
     * Event listener that will perform the fetch POST request inyecting the form data
     * @param 'submit' the action type in the form bottun that launch the Event listener
     * @param function(e) is the function that makes the Fetch request
     */
    formElement.addEventListener('submit', function (e) {

        /**
         * prevent the default action of submit
         */
        e.preventDefault();

        /**
         * @param data is the data collected in the form in the html
         */
        const id = document.getElementById("contactId").value;
        fetch("http://localhost:8080/api/v1/contact/"+id, {
            method: 'DELETE'
        }).then(function (response) {
            if(response){alert("Contact deleted successfully");}
            window.location.href = 'http://localhost:8080/index.html';
            console.log("response: ",response);
        }).then(function (text) {
            console.log("text: ",text);
        }).catch(function (error) {
            console.log(error);
        });
    });
});
