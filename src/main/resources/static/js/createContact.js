/**
 * Creates a new contact with no phones
 *
 * @version 1.0.0 2022-06-29
 * @author Jonathan Daniel Pinilla Forero <jonathan_pinilla@outlook.com>
 * @since 1.0.0
 */

document.addEventListener("DOMContentLoaded", function () {
    /**
     * @param formElement loads the form information bi its id
     */
    const formElement = document.getElementById('formToCreate');

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
        const data = new FormData(this);
        fetch('http://localhost:8080/api/v1/contact', {
            method: 'POST',
            body: data
        }).then(function (response) {
            if(response.status == 201){alert("Contact created successfully");}
            else{alert("An error ocurred: ",response)};
            window.location.href = 'http://localhost:8080/index.html';
            console.log("response: ",response);
        }).then(function (text) {
            console.log(text);
        }).catch(function(error){
            console.log(error);
        });
    });
});