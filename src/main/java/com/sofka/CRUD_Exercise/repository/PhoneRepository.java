package com.sofka.CRUD_Exercise.repository;

import com.sofka.CRUD_Exercise.domain.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository for the Phone entity
 *
 * @version 1.0.0 2022-06-29
 * @author Jonathan Daniel Pinilla Forero <jonathan_pinilla@outlook.com>
 * @since 1.0.0
 */
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    /**
     * Update phone number of a phone
     *
     * @param id the id of the contact to update
     * @param phoneNumber the new contact last name
     */
    @Modifying
    @Query(value = "update Phone phone set phone.phoneNumber = :phoneNumber where phone.id = :id")
    public void updatePhoneNumber(@Param(value = "id") Integer id, @Param(value = "phoneNumber") Integer phoneNumber);
}