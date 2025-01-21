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
    tijdslot VARCHAR(15),
    datum DATE,
    PRIMARY KEY (tijdslot, datum)
);

-- Tabel: ParticuliereKlanten
CREATE TABLE ParticuliereKlanten (
    naam VARCHAR(255),
    kenteken VARCHAR(20),
    email VARCHAR(255),
    PRIMARY KEY (kenteken),
    FOREIGN KEY (naam) REFERENCES Klantnamen(naam) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (kenteken) REFERENCES Kentekens(kenteken) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Trigger: Voeg automatisch ontbrekende naam toe aan hoofdverzamling Klantnamen en kenteken voor hoofdverzameling Kentekens
DELIMITER //
CREATE TRIGGER BeforeInsertParticuliereKlanten
BEFORE INSERT ON ParticuliereKlanten
FOR EACH ROW
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Klantnamen WHERE naam = NEW.naam) THEN
        INSERT INTO Klantnamen (naam) VALUES (NEW.naam);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM Kentekens WHERE kenteken = NEW.kenteken) THEN
        INSERT INTO Kentekens (kenteken) VALUES (NEW.kenteken);
    END IF;
END;//
DELIMITER ;


-- Tabel: ZakelijkeKlanten
CREATE TABLE ZakelijkeKlanten (
    naam VARCHAR(255),
    kenteken VARCHAR(20),
    kvknummer VARCHAR(255),
    PRIMARY KEY (kenteken),
    FOREIGN KEY (naam) REFERENCES Klantnamen(naam) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (kenteken) REFERENCES Kentekens(kenteken) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Trigger: Voeg automatisch ontbrekende naam toe aan Klantnamen en kenteken aan Kentekens.
DELIMITER //
CREATE TRIGGER BeforeInsertZakelijkeKlanten
BEFORE INSERT ON ZakelijkeKlanten
FOR EACH ROW
BEGIN
    IF NOT EXISTS (SELECT 1 FROM Klantnamen WHERE naam = NEW.naam) THEN
        INSERT INTO Klantnamen (naam) VALUES (NEW.naam);
    END IF;
    IF NOT EXISTS (SELECT 1 FROM Kentekens WHERE kenteken = NEW.kenteken) THEN
        INSERT INTO Kentekens (kenteken) VALUES (NEW.kenteken);
    END IF;
END;//
DELIMITER ;


-- Tabel: Reserveringsafspraken
CREATE TABLE Reserveringsafspraken (
    kenteken VARCHAR(20),
    objectID INT,
    tijdslot VARCHAR(15),
    datum DATE,
    PRIMARY KEY (kenteken, tijdslot, datum),
    CONSTRAINT AK_Object_Tijdslot_Datum UNIQUE (objectID, tijdslot, datum),
    FOREIGN KEY (kenteken) REFERENCES Kentekens(kenteken) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (objectID) REFERENCES ObjectIDs(objectID) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (tijdslot, datum) REFERENCES BeschikbaarheidParkeerplaatsen(tijdslot, datum)
);
