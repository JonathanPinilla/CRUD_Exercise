CREATE DATABASE contactLibrary;
USE contactLibrary;
/* Build Table Structure */
CREATE TABLE contact
(
contact_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
contact_name VARCHAR(100) NOT NULL,
contact_lname VARCHAR(100) NOT NULL,
email varchar(100) NOT NULL,
contact_birth DATETIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/* Add Indexes */
CREATE INDEX contact_contact_lname_Idx ON contact (contact_lname) USING BTREE;
CREATE INDEX contact_contact_name_Idx ON contact (contact_name) USING BTREE;
CREATE UNIQUE INDEX contact_contact_name_contact_lname_Idx ON contact (contact_name, contact_lname) USING BTREE;
/******************** Add Table: telefono ************************/
/* Build Table Structure */
CREATE TABLE phone
(
phone_id INTEGER UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
phone_contact_id INTEGER UNSIGNED NOT NULL,
phone_number INTEGER NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/* Add Indexes */
CREATE UNIQUE INDEX phone_tel_phone_contact_id_phone_number_Idx ON phone (phone_contact_id, phone_number) USING BTREE;
CREATE INDEX phone_phone_number_Idx ON phone (phone_number) USING BTREE;
CREATE INDEX phone_phone_contact_id_Idx ON phone (phone_contact_id) USING BTREE;
/************ Add Foreign Keys ***************/
/* Add Foreign Key: fk_telefono_contacto */
ALTER TABLE phone ADD CONSTRAINT fk_phone_contact
FOREIGN KEY (phone_contact_id) REFERENCES contact (contact_id)
ON UPDATE CASCADE ON DELETE RESTRICT;