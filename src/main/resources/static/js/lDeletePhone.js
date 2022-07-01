/**
 * logical delete for a phone
 *
 * @version 1.0.0 2022-06-29
 * @author Jonathan Daniel Pinilla Forero <jonathan_pinilla@outlook.com>
 * @since 1.0.0
 */

document.addEventListener("DOMContentLoaded", function () {
    /**
     * @param formElement loads the form information bi its id
     */
    const formElement = document.getElementById('formToLDelete');

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
        var object = {};
        data.forEach((value, key) => object[key] = value);
        var jsonData = JSON.stringify(object);
        const id = document.getElementById('id').value;
        fetch(`/api/v1/phone/${id}`, {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'PUT',
            body: jsonData
        }).then(function (response) {
            if (response.status == 200) { alert("Contact updated successfully"); }
            else { alert("An error ocurred: ", response) };
            window.location.href = 'http://localhost:8080/index.html';
            console.log("response: ", response);
        }).then(function (text) {
            console.log(text);
        }).catch(function (error) {
            console.log(error);
        });
    });
});