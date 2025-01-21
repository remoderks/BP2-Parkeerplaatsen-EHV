-- Maak de database
CREATE DATABASE ReserveringsappEHV;
USE ReserveringsappEHV;

-- Tabel: Klantnamen
CREATE TABLE Klantnamen (
    naam VARCHAR(255) PRIMARY KEY
);

-- Tabel: Kentekens
CREATE TABLE Kentekens (
    kenteken VARCHAR(20) PRIMARY KEY
);

-- Tabel: ObjectIDs
CREATE TABLE ObjectIDs (
    objectID INT PRIMARY KEY
);

-- Tabel: BeschikbaarheidParkeerplaatsen
CREATE TABLE BeschikbaarheidParkeerplaatsen (
    tijdslot TIME,
    datum DATE,
    PRIMARY KEY (tijdslot, datum)
);

-- Tabel: ParticuliereKlanten
CREATE TABLE ParticuliereKlanten (
    naam VARCHAR(255),
    kenteken VARCHAR(20),
    email VARCHAR(255),
    PRIMARY KEY (kenteken),
    FOREIGN KEY (naam) REFERENCES Klantnamen(naam) ON UPDATE CASCADE,
    FOREIGN KEY (kenteken) REFERENCES Kentekens(kenteken) ON UPDATE CASCADE
);

-- Tabel: ZakelijkeKlanten
CREATE TABLE ZakelijkeKlanten (
    naam VARCHAR(255),
    kenteken VARCHAR(20),
    email VARCHAR(255),
    PRIMARY KEY (kenteken),
    FOREIGN KEY (naam) REFERENCES Klantnamen(naam) ON UPDATE CASCADE,
    FOREIGN KEY (kenteken) REFERENCES Kentekens(kenteken) ON UPDATE CASCADE
);

-- Tabel: Reserveringsafspraken
CREATE TABLE Reserveringsafspraken (
    kenteken VARCHAR(20),
    objectID INT,
    tijdslot TIME,
    datum DATE,
    PRIMARY KEY (kenteken, tijdslot, datum),
    CONSTRAINT AK_Object_Tijdslot_Datum UNIQUE (objectID, tijdslot, datum),
    FOREIGN KEY (kenteken) REFERENCES Kentekens(kenteken) ON UPDATE CASCADE,
    FOREIGN KEY (objectID) REFERENCES ObjectIDs(objectID) ON UPDATE CASCADE,
    FOREIGN KEY (tijdslot, datum) REFERENCES BeschikbaarheidParkeerplaatsen(tijdslot, datum)
);
