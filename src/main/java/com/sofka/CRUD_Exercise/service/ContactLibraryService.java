package com.sofka.CRUD_Exercise.service;

import com.sofka.CRUD_Exercise.domain.Contact;
import com.sofka.CRUD_Exercise.domain.Phone;
import com.sofka.CRUD_Exercise.repository.ContactRepository;
import com.sofka.CRUD_Exercise.repository.PhoneRepository;
import com.sofka.CRUD_Exercise.service.interfaces.ILibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interface for the Library service
 *
 * @author Jonathan Daniel Pinilla Forero <jonathan_pinilla@outlook.com>
 * @version 1.0.0 2022-06-29
 * @since 1.0.0
 */
@Service
public class ContactLibraryService implements ILibrary {

    /**
     * Contact repository
     */
    @Autowired
    private ContactRepository contactRepository;

    /**
     * Phone repository
     */
    @Autowired
    private PhoneRepository phoneRepository;

    /**
     * To get the Contact list.
     *
     * @return list with all the contacts on the system
     */
    @Override
    @Transactional
    public List<Contact> getList() {
        return contactRepository.findAll();
    }

    /**
     * Create a new contact.
     *
     * @param contact the contact object that will be created
     * @return the contact created
     */
    @Override
    @Transactional
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    /**
     * Create a new phone.
     *
     * @param phone the phone object that will be created
     * @return the phone created
     */
    @Override
    @Transactional
    public Phone createPhone(Phone phone) {
        return phoneRepository.save(phone);
    }

    /**
     * Update a complete contact.
     *
     * @param id      the id of the contact to update
     * @param contact the contact object to update
     * @return the contact updated
     */
    @Override
    @Transactional
    public Contact updateContact(Integer id, Contact contact) {
        contact.setId(id);
        return contactRepository.save(contact);
    }

    /**
     * Update the name of a contact with the given id (Not implemented)
     *
     * @param id      the id of the contact to update
     * @param contact the contact object that will be updated
     * @return the contact updated
     */
    @Override
    @Transactional
    public Contact updateName(Integer id, Contact contact) {
        contactRepository.updateName(id, contact.getContactName());
        return contact;
    }

    /**
     * Update the last name of a contact given the id (Not implemented)
     *
     * @param id      the id of the contact to update
     * @param contact the contact object that will be updated
     * @return the contact updated
     */
    @Override
    public Contact updateLname(Integer id, Contact contact) {
        contact.setId(id);
        contactRepository.updateLname(id, contact.getContactLname());
        return contact;
    }

    /**
     * Update a complete phone.
     *
     * @param id    the id of the phone to update
     * @param phone the phone object to update
     * @return the phone updated
     */
    @Override
    public Phone updatePhone(Integer id, Phone phone) {
        phone.setId(id);
        return phoneRepository.save(phone);
    }

    /**
     * Updates only the phone number (Not implemented)
     * @param id    the id of the phone to update
     * @param phone the phone object to update
     * @return
     */
    @Override
    public Phone updateOnlyNumber(Integer id, Phone phone) {
        phone.setId(id);
        phoneRepository.updatePhoneNumber(id, phone.getPhoneNumber());
        return phone;
    }

    /**
     * Delete a contact.
     *
     * @param id the id of the contact to delete
     * @return the contact deleted
     */
    @Override
    public Contact deleteContact(Integer id) {
        var contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            contactRepository.delete(contact.get());
            return contact.get();
        } else {
            return null;
        }
    }

    /**
     * Delete a phone (Not implemented)
     *
     * @param id the id of the phone to delete
     * @return the phone deleted
     */
    @Override
    public Phone deletePhone(Integer id) {
        var phone = phoneRepository.findById(id);
        if (phone.isPresent()) {
            phoneRepository.delete(phone.get());
            return phone.get();
        } else {
            return null;
        }
    }
}
