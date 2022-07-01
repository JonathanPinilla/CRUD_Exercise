document.addEventListener("DOMContentLoaded", function () {
    /**
     * @param getListButton get the button that gets the contact list
     * @param createContactRedirButton get the button that redirects to the createContact.html
     * @param deleteContactRedirButton get the button that redirects to the deleteContact.html
     */
    const getListButton = document.querySelector('#getListButton');
    const createContactRedirButton = document.querySelector('#createContactRedirButton');
    const deleteContactRedirButton = document.querySelector('#deleteContactRedirButton');
    const createPhoneRedirButton = document.querySelector('#createPhoneRedirButton');
    const updateContactNRedirButton = document.querySelector('#updateContactNRedirButton');

    /**
     * Fetch request method Get creates table fields and fills them with Contact info
     */
    const getContactList = function () {
        fetch('http://localhost:8080/api/v1/contacts')
            .then(res => res.json())
            .then(data => {
                console.log(data);

                const table = document.getElementById('tableData');
                const tbody = document.createElement('tbody');
                
                data.forEach(element => {
                    const tr = document.createElement('tr');
                    const tdId = document.createElement('td');
                    const tdBirthDate = document.createElement('td');
                    const tdName = document.createElement('td');
                    const tdLname = document.createElement('td');
                    const tdEmail = document.createElement('td');
                    const tdPhone = document.createElement('td');
                    let phoneList = [];

                    tdId.textContent = element.id;
                    tdBirthDate.textContent = element.contactBirth;
                    tdName.textContent = element.contactName;
                    tdLname.textContent = element.contactLname;
                    tdEmail.textContent = element.email;
                    element.phones.forEach(element => {
                        phoneList.push(element.phoneNumber);
                    });
                    tdPhone.textContent = phoneList;
                    
                    tr.appendChild(tdId);
                    tr.appendChild(tdName);
                    tr.appendChild(tdLname);
                    tr.appendChild(tdEmail);
                    tr.appendChild(tdBirthDate);
                    tr.appendChild(tdPhone);

                    tbody.appendChild(tr);

                    
                });
                table.appendChild(tbody);
            })
            .catch(error => console.log(error));
    };

    /**
     * Function to load createContact.html
     */
    const createContactRedir = function () {
        window.location.href = 'http://localhost:8080/createContact.html'
    };

    /**
     * Function to load deleteContact.html
     */
    const deleteContactRedir = function () {
        window.location.href = 'http://localhost:8080/deleteContact.html'
    };

    /**
     * Function to load createPhone.html
     */
    const createPhoneRedir = function () {
        window.location.href = 'http://localhost:8080/createPhone.html'
    };

    /**
     * Function to load updateContact.html
     */
     const updateContactNRedir = function () {
        window.location.href = 'http://localhost:8080/updateContact.html'
    };

    /**
     * Event listener to launch the required function depending on the button clicked
     */
    createContactRedirButton.addEventListener("click", createContactRedir);
    deleteContactRedirButton.addEventListener("click", deleteContactRedir);
    createPhoneRedirButton.addEventListener("click", createPhoneRedir);
    updateContactNRedirButton.addEventListener("click", updateContactNRedir);
    getListButton.addEventListener("click", getContactList);
});