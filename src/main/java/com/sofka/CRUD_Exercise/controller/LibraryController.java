package com.sofka.CRUD_Exercise.controller;

import com.sofka.CRUD_Exercise.domain.Contact;
import com.sofka.CRUD_Exercise.service.ContactLibraryService;
import com.sofka.CRUD_Exercise.utility.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    RestTemplate restTemplate = new RestTemplate();
    /**
     * Service to manage the library
     */

    Response response = new Response();

    HttpStatus httpStatus = HttpStatus.OK;

    @Autowired
    private ContactLibraryService contactLibraryService;


    @GetMapping(path ="/")
    public String home(){
        return "Hello World";
    }

    @GetMapping(path = "/contacts")
    public List<Contact> getContacts(){
        return contactLibraryService.getList();
    }

    @GetMapping(path = "/testJson")
    public String index() {
        String jsonStr = String.valueOf(contactLibraryService.getList());
        return jsonStr;
    }

    @PostMapping(path = "/contact")
    public ResponseEntity<Contact> createContact(Contact contact){
        log.info("Contact to create {}", contact);
        contactLibraryService.createContact(contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
