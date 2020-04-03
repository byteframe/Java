-- WindAndWaterProcedures.sql
-- This script is for generating the stored procedures

USE playmat;

DROP PROCEDURE IF EXISTS UserGet;

delimiter //

CREATE PROCEDURE UserGet(IN getName varchar(50), IN getPword varchar(50), IN getFirstName varchar(50),
                             IN getLastName varchar(50), IN getEMail varchar(50), IN getEnabled tinyint)
BEGIN

  SELECT name, firstName, lastName, eMail FROM Users
  WHERE IF(ISNULL(getName),name = name, name = getName)
  AND IF(ISNULL(getPword),pword = pword, pword = getPword)
  AND IF(ISNULL(getFirstName),firstName = firstName, firstName = getFirstName)
  AND IF(ISNULL(getLastName), lastName = lastName, lastName = getLastName)
  AND IF(ISNULL(getEMail),eMail = eMail, eMail = getEMail)
  AND IF(ISNULL(getEnabled),enabled = enabled, enabled = getEnabled);

END;
//

delimiter ;

DROP PROCEDURE IF EXISTS UserSet;

delimiter //

CREATE PROCEDURE UserSet(IN newName varchar(50), IN newPword varchar(50),  IN newFirstName varchar(50),
                             IN newLastName varchar(50), IN newEMail varchar(50), IN newEnabled tinyint)
BEGIN

  DECLARE rowCount INT;

  UPDATE Users
  SET pword = newPword,
      firstName = newFirstName,
      lastName = newLastName,
      eMail = newEMail,
      enabled = newEnabled
  WHERE name = newName;

  SELECT ROW_COUNT() INTO rowCount;

  IF rowCount <= 0 THEN
    INSERT INTO Users (name, pword, firstName, lastName, eMail, enabled)
    VALUES (newName, newPword, newFirstName, newLastName, newEmail, newEnabled);
  END IF;

END;
//

delimiter ;

DROP PROCEDURE IF EXISTS UserDelete;

delimiter //

CREATE PROCEDURE UserDelete(inUserName varchar(50))

BEGIN

  DELETE FROM UserRoles WHERE username = inUserName;
  DELETE FROM PlayerCards WHERE username = inUserName;
  DELETE FROM PlayerDecks WHERE username = inUserName;
  DELETE FROM Users WHERE name = inUserName;

END;
//

delimiter ;

DROP PROCEDURE IF EXISTS RolesGet;

delimiter //

CREATE PROCEDURE RolesGet()
BEGIN

  SELECT name, description FROM Roles;

END;
//

delimiter ;

DROP PROCEDURE IF EXISTS RoleInsert;

delimiter //

CREATE PROCEDURE RoleInsert(IN newName varchar(50), IN newDescription varchar(100))
BEGIN

  INSERT INTO Roles (name, description)
    VALUES (newName, newDescription);

END;
//

delimiter ;

DROP PROCEDURE IF EXISTS UserRoleGet;

delimiter //

CREATE PROCEDURE UserRoleGet(IN getUserName varchar(50), IN getRoleName varchar(50), IN getEnabled tinyint)
BEGIN

  SELECT userName, roleName, enabled FROM UserRoles
  WHERE IF(ISNULL(getUserName), userName = userName, userName = getUserName)
  AND IF(ISNULL(getRoleName), roleName = roleName, roleName = getRoleName)
  AND IF(ISNULL(getEnabled), enabled = enabled, enabled = getEnabled);

END;
//

delimiter ;

DROP PROCEDURE IF EXISTS UserRoleSet;

delimiter //

CREATE PROCEDURE UserRoleSet(IN newUserName varchar(50), IN newRoleName varchar(50), IN newEnabled tinyint)
BEGIN

  DECLARE rowCount INT;

  UPDATE UserRoles
  SET enabled = newEnabled
  WHERE userName = newUserName
  AND roleName = newRoleName;

  SELECT ROW_COUNT() INTO rowCount;

  IF rowCount <= 0 THEN
    INSERT INTO UserRoles (userName, roleName, enabled)
    VALUES (newUserName, newRoleName, newEnabled);
  END IF;

END;
//

delimiter ;

DROP PROCEDURE IF EXISTS TableSet;

delimiter //

CREATE PROCEDURE TableSet(IN newId bigint, IN newName varchar(50), IN newPlayers int, IN newTType varchar(50))
BEGIN

  DECLARE rowCount INT;

  UPDATE PTables
  SET tType = newTType, players = newPlayers
  WHERE id = newId
  AND name = newName;

  SELECT ROW_COUNT() INTO rowCount;

  IF rowCount <= 0 THEN
    INSERT INTO PTables (id, name, players, tType)
    VALUES (newId, newName, newPlayers, newTType);
  END IF;

END;
//

delimiter ;