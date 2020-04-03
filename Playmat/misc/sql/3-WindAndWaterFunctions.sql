-- WindAndWaterFunctions.sql
-- This script is for generating the stored functions

USE playmat;

DROP FUNCTION IF EXISTS UserValidate;

delimiter //

CREATE FUNCTION UserValidate(userName varchar(50), psword varchar(50))
RETURNS INT
BEGIN

  DECLARE Num_Rows INT;

  SELECT COUNT(name) INTO Num_Rows FROM Users
  WHERE name = userName
  AND IF(ISNULL(psword),pword = pword, pword = psword);

  RETURN Num_Rows;

END;
//

delimiter ;

DROP FUNCTION IF EXISTS UserAddNew;

delimiter //

CREATE FUNCTION UserAddNew(newUserName varchar(50), newPword varchar(50), newFirstName varchar(50),
                               newLastName varchar(50), newEMail varchar(50))
RETURNS tinyint
BEGIN

  CALL UserSet(newUserName, newPword, newFirstName, newLastName, newEMail, 1);
  CALL UserRoleSet(newUserName, 'Player', 1);
  RETURN 1;

END;
//

delimiter ;