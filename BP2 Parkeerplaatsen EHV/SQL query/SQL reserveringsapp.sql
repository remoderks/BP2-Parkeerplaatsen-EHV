-- Create the schema
CREATE SCHEMA if not exists ReserveringsappEHV;

-- Use the schema
USE ReserveringsappEHV;

-- Create the personen table
CREATE TABLE klanten (
    klant VARCHAR(50) PRIMARY KEY
);

-- Create the kentekens table
CREATE TABLE kentekens (
    kenteken VARCHAR(20) PRIMARY KEY
);

-- Create the objectIDs table
CREATE TABLE objectIDs (
    objectID INT PRIMARY KEY
);

-- Create the reserveringen table
CREATE TABLE reserveringen (
    tijdslot TIME,
    datum DATE,
    PRIMARY KEY (tijdslot, datum)
);

-- Create the personenhebbenkentekens table
CREATE TABLE klantenhebbenkentekens (
    klant VARCHAR(50),
    kenteken VARCHAR(20),
    CONSTRAINT persoon_kentekens_pk PRIMARY KEY (klant, kenteken),
    CONSTRAINT persoon_personen_fk FOREIGN KEY (klant) REFERENCES personen(klant),
    CONSTRAINT kenteken_kentekens_fk FOREIGN KEY (kenteken) REFERENCES kentekens(kenteken)
);

-- Create the reserveringsafspraken table
CREATE TABLE reserveringsafspraken (
    kenteken VARCHAR(20),
    objectID INT,
    tijdslot TIME,
    datum DATE,
    CONSTRAINT reserveringsafspraken_pk PRIMARY KEY (kenteken, tijdslot, datum),
    CONSTRAINT ak_objectid_tijdslot_datum UNIQUE (objectID, tijdslot, datum),
    CONSTRAINT objectID_fk FOREIGN KEY (objectID) REFERENCES objectIDs(objectID),
    CONSTRAINT kenteken_fk FOREIGN KEY (kenteken) REFERENCES kentekens(kenteken),
    CONSTRAINT tijdslot_datum_fk FOREIGN KEY (tijdslot, datum) REFERENCES reserveringen(tijdslot, datum)
);
