package com.sofka.CRUD_Exercise.controller;

import com.sofka.CRUD_Exercise.domain.Contact;
import com.sofka.CRUD_Exercise.domain.Phone;
import com.sofka.CRUD_Exercise.service.ContactLibraryService;
import com.sofka.CRUD_Exercise.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.List;


/**
 * Library Controller
 *
 * @version 1.0.0 2022-06-29
 * @author Jonathan Daniel Pinilla Forero <jonathan_pinilla@outlook.com>
 * @since 1.0.0
 */
@Slf4j
@RestController
public class LibraryController {

    /**
     *  Rest template
     */
    RestTemplate restTemplate = new RestTemplate();

    /**
     *  Response Object
     */
    Response response = new Response();

    /**
     * New HttpStatus initialized as OK
     */
    HttpStatus httpStatus = HttpStatus.OK;

    /**
     * Service to manage the library
     */
    @Autowired
    private ContactLibraryService contactLibraryService;

    /**
     * Main page, just shows to the user which is the correct location
     * @return String
     */
    @GetMapping(path ="/")
    public String home(){
        return "To use the application go to http://localhost:8080/index.html";
    }

    /**
     * Get mapping to show all the contacts in the notebook
     * @return Contact List with all the contacts
     */
    @GetMapping(path = "/api/v1/contacts")
    public List<Contact> getContacts(){
        return contactLibraryService.getList();
    }

    /**
     * Creates a new contact
     * @param contact to be created
     * @return the contact created and the HttpStatus as Created
     */
    @PostMapping(path = "/api/v1/contact")
    public ResponseEntity<Contact> createContact(Contact contact){
        log.info("Contact to create {}", contact);
        contactLibraryService.createContact(contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    /**
     * Creates a new phone and connects with a contact
     * @param phone object phone to be created
     * @return response with the phone object and the httpstatus created
     */
    @PostMapping(path = "/api/v1/phone")
    public ResponseEntity<Phone> createPhone(Phone phone){
        log.info("Phone to create {}", phone);
        contactLibraryService.createPhone(phone);
        return new ResponseEntity<>(phone, HttpStatus.CREATED);
    }

    /**
     * Updates the name of an existing contact by it's id
     * @param contact object to be updated
     * @param id parameter to find the contact to update
     * @return response and http status
     */
    @PutMapping(path = "/api/v1/contact/{id}")
    public ResponseEntity<Response> updateContact(
            @RequestBody Contact contact,
            @PathVariable(value="id") Integer id
    ) {
        response.restart();
        try {
            response.data = contactLibraryService.updateContact(id, contact);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Deletes a contact using its id
     * @param id of the contact to delete
     * @return the response and its http status
     */
    @DeleteMapping(path = "/api/v1/contact/{id}")
    public ResponseEntity<Response> deleteContacto(@PathVariable(value="id") Integer id) {
        response.restart();
        try {
            response.data = contactLibraryService.deleteContact(id);
            if (response.data == null) {
                response.message = "Contact does not exist in the notebook";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Contact deleted successfully";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity(response, httpStatus);
    }

    /**
     * Manage internal message error
     * @param exception
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Manage response message error
     * @param exception
     */
    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if(exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "El dato ya está registrado";
                    break;
                case 1452:
                    response.message = "El usuario indicado no existe";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
