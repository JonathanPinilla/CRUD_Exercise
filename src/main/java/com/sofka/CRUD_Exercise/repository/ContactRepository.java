package com.sofka.CRUD_Exercise.repository;

import com.sofka.CRUD_Exercise.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for the Contact entity
 *
 * @version 1.0.0 2022-06-29
 * @author Jonathan Daniel Pinilla Forero <jonathan_pinilla@outlook.com>
 * @since 1.0.0
 */
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    /**
     * Update name of a contact
     *
     * @param id          the id of the contact to update
     * @param contactName the new contact name
     */
    @Modifying
    @Query(value = "update Contact  contact set contact.contactName = :contactName where contact.id = :id")
    public void updateName(@Param(value = "id") Integer id, @Param(value = "contactName") String contactName);

    /**
     * Update last name of a contact
     *
     * @param id the id of the contact to update
     * @param contactLname the new contact last name
     */
    @Modifying
    @Query(value = "update Contact contact set contact.contactLname = :contactLname where contact.id = :id")
    public void updateLname(@Param(value = "id") Integer id, @Param(value = "contactLname") String contactLname);
}