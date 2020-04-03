-- WindAndWaterFunctions.sql
-- This script is for generating the stored card and deck procedures

USE playmat;

DROP PROCEDURE IF EXISTS PlayerDecksGet;

delimiter //

CREATE PROCEDURE PlayerDecksGet(IN getUserName varchar(50), IN getDeckName varchar(50))
BEGIN

  SELECT userName, name, cardId, numberOfCards FROM PlayerDecks
  WHERE IF(ISNULL(getUserName), userName = userName, userName = getUserName)
  AND IF(ISNULL(getDeckName), name = name, name = getDeckName);
  
END;
//

delimiter ;

DROP PROCEDURE IF EXISTS PlayerDecksSet;

delimiter //

CREATE PROCEDURE PlayerDecksSet(IN newUserName varchar(50), IN newName varchar(50), IN newCardId varchar(150), newNumberOfCards int)
BEGIN

  DECLARE rowCount INT;

  UPDATE PlayerDecks
  SET numberOfCards = newNumberOfCards
  WHERE userName = newUserName
  AND name = name
  AND cardId = newCardId;

  SELECT ROW_COUNT() INTO rowCount;

  IF rowCount <= 0 THEN
    INSERT INTO PlayerDecks (userName, name, cardId, numberOfCards)
    VALUES (newUserName, newName, newCardId, newNumberOfCards);
  END IF;

END;
//

delimiter ;

DROP PROCEDURE IF EXISTS PlayerDecksUpdate;

delimiter //

CREATE PROCEDURE PlayerDecksUpdate(IN newUserName varchar(50), IN newName varchar(50), IN newCardId varchar(150), newNumberOfCards int)
BEGIN

  DECLARE rowCount INT;

  UPDATE PlayerDecks
  SET numberOfCards = numberOfCards + newNumberOfCards
  WHERE userName = newUserName
  AND name = newName
  AND cardId = newCardId;

  SELECT ROW_COUNT() INTO rowCount;

  IF rowCount <= 0 THEN
    INSERT INTO PlayerDecks (userName, name, cardId, numberOfCards)
    VALUES (newUserName, newName, newCardId, newNumberOfCards);
  END IF;

END;
//

delimiter ;

DROP PROCEDURE IF EXISTS PlayerDecksDelete;

delimiter //

CREATE PROCEDURE PlayerDecksDelete(IN oldUserName varchar(50), IN oldName varchar(50))
BEGIN

  DECLARE rowCount INT;

  DELETE FROM PlayerDecks
  WHERE userName = oldUserName
  AND name = oldName;
-- AND cardId = newCardId;

END;
//

delimiter ;

DROP PROCEDURE IF EXISTS CardDataGet;

delimiter //

CREATE PROCEDURE CardDataGet(IN getId varchar(150), IN getName varchar(50), IN getCType varchar(50), IN getColor varchar(50),
                            IN getLevelPoints varchar(50), IN getAttack varchar(50), IN getDef varchar(50), IN getCConv varchar(50),
                            IN getThemePack varchar(50), IN getStarterPack varchar(50), IN getBooster varchar(50), IN getSpecial varchar(255),
                            IN getFlavor varchar(255), IN getEnabled tinyint, IN getImageURL varchar(150))
BEGIN

  SELECT id, name, cType, color, levelPoints, attack, def, cConv, themePack, starterPack, booster, special, flavor, enabled, imageURL FROM CardData
  WHERE IF(ISNULL(getId), id = id, id = getId)
  AND IF(ISNULL(getName), name = name, name = getName)
  AND IF(ISNULL(getCType), cType = cType, cType = getCType)
  AND IF(ISNULL(getColor), color = color, color = getColor)
  AND IF(ISNULL(getLevelPoints), levelPoints = levelPoints, levelPoints = getLevelPoints)
  AND IF(ISNULL(getAttack), attack = attack, attack = getAttack)
  AND IF(ISNULL(getDef), def = def, def = getDef)
  AND IF(ISNULL(getCConv), cConv = cConv, cConv = getCConv)
  AND IF(ISNULL(getThemePack), themePack = themePack, themePack = getThemePack)
  AND IF(ISNULL(getStarterPack), starterPack = starterPack, starterPack = getStarterPack)
  AND IF(ISNULL(getBooster), booster = booster, booster = getBooster)
  AND IF(ISNULL(getSpecial), special = special, special = getSpecial)
  AND IF(ISNULL(getFlavor), flavor = flavor, flavor = getFlavor)
  AND IF(ISNULL(getEnabled), enabled = enabled, enabled = getEnabled)
  AND IF(ISNULL(getImageURL), imageURL = imageURL, imageURL = getImageURL);
  
END;
//

delimiter ;

DROP PROCEDURE IF EXISTS CardDataSet;

delimiter //

CREATE PROCEDURE CardDataSet(IN setId varchar(150), IN setName varchar(50), IN setCType varchar(50), IN setColor varchar(50),
                            IN setLevelPoints varchar(50), IN setAttack varchar(50), IN setDef varchar(50), IN setCConv varchar(50),
                            IN setThemePack varchar(50), IN setStarterPack varchar(50), IN setBooster varchar(50), IN setSpecial varchar(255),
                            IN setFlavor varchar(255), IN setEnabled tinyint, IN setImageURL varchar(150))
BEGIN

  DECLARE rowCount INT;

  UPDATE CardData
  SET id = setId, name = setName, cType = setCType, color = setColor, levelPoints = setLevelPoints, attack = setAttack, def = setDef,
      cConv = setCConv, themePack = setThemePack, starterPack = setStarterPack, booster = setBooster, special = setSpecial, flavor = setFlavor,
      enabled = setEnabled, imageURL = setImageURL
  WHERE id = setId;


  SELECT ROW_COUNT() INTO rowCount;

  IF rowCount <= 0 THEN
    INSERT INTO CardData (id, name, cType, color, levelPoints, attack, def, cConv, themePack, starterPack, booster, special, flavor, enabled, imageURL)
    VALUES (setId, setName, setCType, setColor, setLevelPoints, setAttack,setDef, setCConv, setThemePack, setStarterPack, setBooster,
            setSpecial, setFlavor, setEnabled, setImageURL);
  END IF;
  
END;
//

delimiter ;

DROP PROCEDURE IF EXISTS PlayerCardsGet;

delimiter //

CREATE PROCEDURE PlayerCardsGet(IN getUserName varchar(50), IN getCardId varchar(150))
BEGIN

  SELECT userName, cardId, numberOfCards FROM PlayerCards
  WHERE IF(ISNULL(getUserName), userName = userName, userName = getUserName)
  AND IF(ISNULL(getCardId), cardId = cardId, cardId = getCardId);
  
END;
//

delimiter ;

DROP PROCEDURE IF EXISTS PlayerCardsUpdate;

delimiter //

CREATE PROCEDURE PlayerCardsUpdate(IN newUserName varchar(50), IN newCardId varchar(150), newNumberOfCards int)
BEGIN

  DECLARE rowCount INT;

  UPDATE PlayerCards
  SET numberOfCards = numberOfCards + newNumberOfCards
  WHERE userName = newUserName
  AND cardId = newCardId;

  SELECT ROW_COUNT() INTO rowCount;

  IF rowCount <= 0 THEN
    INSERT INTO PlayerCards (userName, cardId, numberOfCards)
    VALUES (newUserName, newCardId, newNumberOfCards);
  END IF;

END;
//

delimiter ;

DROP PROCEDURE IF EXISTS PlayerCardsSet;

delimiter //

CREATE PROCEDURE PlayerCardsSet(IN newUserName varchar(50), IN newCardId varchar(150), newNumberOfCards int)
BEGIN

  DECLARE rowCount INT;

  UPDATE PlayerCards
  SET numberOfCards = newNumberOfCards
  WHERE userName = newUserName
  AND cardId = newCardId;

  SELECT ROW_COUNT() INTO rowCount;

  IF rowCount <= 0 THEN
    INSERT INTO PlayerCards (userName, cardId, numberOfCards)
    VALUES (newUserName, newCardId, newNumberOfCards);
  END IF;

END;
//

delimiter ;

DROP FUNCTION IF EXISTS TransferPack;

delimiter //

CREATE FUNCTION TransferPack(userName VARCHAR(50), packSize INT, cardRarity INT)
RETURNS INT
BEGIN

  DECLARE cardsPicked, success INT;
  DECLARE newCardId VARCHAR(150);

  SET cardsPicked = 0;
  SET success = 0;

  WHILE cardsPicked < packSize DO

      SELECT id INTO newCardId
      FROM CardData
      WHERE packed = false
      AND rarity = cardRarity
      ORDER BY RAND()
      LIMIT 1;

      IF NewCardId IS NULL THEN
        UPDATE CardData
        SET packed = false;
      ELSE
      BEGIN
        CALL PlayerCardsUpdate(userName, newCardId, 1);
        SET cardsPicked = cardsPicked + 1;
      END;
      END IF;

  END WHILE;

  RETURN cardsPicked;

END;
//

delimiter ;