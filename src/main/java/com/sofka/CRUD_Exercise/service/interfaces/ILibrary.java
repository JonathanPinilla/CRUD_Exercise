package com.sofka.CRUD_Exercise.service.interfaces;

import com.sofka.CRUD_Exercise.domain.Contact;
import com.sofka.CRUD_Exercise.domain.Phone;

import java.util.List;

/**
 * Interface for the Library service
 *
 * @author Jonathan Daniel Pinilla Forero <jonathan_pinilla@outlook.com>
 * @version 1.0.0 2022-06-29
 * @since 1.0.0
 */
public interface ILibrary {

    /**
     * To get the Contact list.
     *
     * @return list with all the contacts on the system
     */
    public List<Contact> getList();


    /**
     * Create a new contact.
     *
     * @param contact the contact object that will be created
     * @return the contact created
     */
    public Contact createContact(Contact contact);

    /**
     * Create a new phone.
     *
     * @param phone the phone object that will be created
     * @return the phone created
     */
    public Phone createPhone(Phone phone);


    /**
     * Update a complete contact.
     *
     * @param id      the id of the contact to update
     * @param contact the contact object to update
     * @return the contact updated
     */
    public Contact updateContact(Integer id, Contact contact);

    /**
     * Update the name of a contact with the given id.
     *
     * @param id      the id of the contact to update
     * @param contact the contact object that will be updated
     * @return the contact updated
     */
    public Contact updateName(Integer id, Contact contact);

    /**
     * Update the last name of a contact given the id.
     *
     * @param id      the id of the contact to update
     * @param contact the contact object that will be updated
     * @return the contact updated
     */
    public Contact updateLname(Integer id, Contact contact);

    /**
     * Update a complete phone.
     *
     * @param id    the id of the phone to update
     * @param phone the phone object to update
     * @return the phone updated
     */
    public Phone updatePhone(Integer id, Phone phone);

    /**
     * Update only phone number.
     *
     * @param id    the id of the phone to update
     * @param phone the phone object to update
     * @return the phone updated
     */
    public Phone updateOnlyNumber(Integer id, Phone phone);

    /**
     * Delete a contact.
     *
     * @param id the id of the contact to delete
     * @return the contact deleted
     */
    public Contact deleteContact(Integer id);

    /**
     * Delete a phone.
     *
     * @param id the id of the phone to delete
     * @return the phone deleted
     */
    public Phone deletePhone(Integer id);
}
