--  SQL file representing changes to the functionalities model
--  Generated at Mon May 21 16:29:39 WEST 2007
--  DO NOT EDIT THIS FILE, run the generating script instead

--  Preamble
SET AUTOCOMMIT = 0;

START TRANSACTION;

-- 
--  Updating existing functionalities
-- 

--  ID: 26328 UUID: '173e00ae-66ff-408b-a8ce-d4a4011fe3f5'
UPDATE `ACCESSIBLE_ITEM` AS own SET own.`PATH` = '/teachers.do' WHERE own.`UUID` = '173e00ae-66ff-408b-a8ce-d4a4011fe3f5';

COMMIT;