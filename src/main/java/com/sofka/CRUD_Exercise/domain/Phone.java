package com.sofka.CRUD_Exercise.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity of Phone
 *
 * @version 1.0.0 2022-06-29
 * @author Jonathan Daniel Pinilla Forero <jonathan_pinilla@outlook.com>
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "phone")
public class Phone implements Serializable {
    /**
     * Variable to manage de ID of the tuple
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identifier of the phone
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id", nullable = false)
    private Integer id;

    /**
     * Link point to contact (a contact can have many phones)
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "phone_contact_id", nullable = false)
    private Contact phoneContact;

    /**
     * Phone number
     */
    @Column(name = "phone_number", nullable = false)
    private Integer phoneNumber;

}