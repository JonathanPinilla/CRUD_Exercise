package com.sofka.CRUD_Exercise.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entity for contact
 *
 * @author Jonathan Daniel Pinilla Forero <jonathan_pinilla@outlook.com>
 * @version 1.0.0 2022-06-29
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    /**
     * Variable to manage de ID of the tuple
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identifier of the tuple
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", nullable = false)
    private Integer id;

    /**
     * Contact name
     */
    @Column(name = "contact_name", nullable = false, length = 100)
    private String contactName;

    /**
     * Contact last name
     */
    @Column(name = "contact_lname", nullable = false, length = 100)
    private String contactLname;

    /**
     * Contact email
     */
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    /**
     * Contact birth date
     */
    @Column(name = "contact_birth", nullable = false)
    private Date contactBirth;

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Phone.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "phoneContact"
    )
    @JsonManagedReference
    private Set<Phone> phones = new LinkedHashSet<>();

}