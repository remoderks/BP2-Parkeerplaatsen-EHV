-- Generate and insert all tijdslots for the next week into BeschikbaarheidParkeerplaatsen
USE ReserveringsappEHV;
SET @startDate = '2025-01-27'; -- Start date (Monday of next week)
SET @endDate = '2025-02-02';   -- End date (Sunday of next week)

-- Create a helper table for time slots (if it doesn’t exist)
CREATE TEMPORARY TABLE Tijdslots (tijdslot VARCHAR(11));

-- Populate Tijdslots table with all hourly time slots between 6:00 and 21:00
INSERT INTO Tijdslots (tijdslot)
SELECT 
    CONCAT(LPAD(h, 2, '0'), ':00-', LPAD(h + 1, 2, '0'), ':00') AS tijdslot
FROM 
    (SELECT 6 AS h UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 
     UNION ALL SELECT 10 UNION ALL SELECT 11 UNION ALL SELECT 12 
     UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15 
     UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 
     UNION ALL SELECT 19 UNION ALL SELECT 20) AS hours;

-- Insert all combinations of tijdslot and datum into BeschikbaarheidParkeerplaatsen
INSERT INTO BeschikbaarheidParkeerplaatsen (tijdslot, datum)
SELECT 
    t.tijdslot,
    DATE_ADD(@startDate, INTERVAL d.day_num DAY) AS datum
FROM 
    Tijdslots t
CROSS JOIN (
    SELECT 0 AS day_num UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 
    UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6
) AS d
WHERE 
    DATE_ADD(@startDate, INTERVAL d.day_num DAY) <= @endDate;

-- Clean up the temporary table
DROP TEMPORARY TABLE Tijdslots;
