-- WindAndWaterBase.sql
-- This script is for generating the tables

DROP DATABASE IF EXISTS playmat;

CREATE DATABASE playmat;

USE playmat;

GRANT ALL ON * TO 'playmat'@'localhost' IDENTIFIED BY 'playmat';
GRANT SELECT ON mysql.proc TO 'playmat'@'localhost' IDENTIFIED BY 'playmat';

CREATE TABLE CardData(
id             varchar(150),
name           varchar(50),
cType          varchar(50),
color          varchar(50),
levelPoints    varchar(50),
attack         varchar(50),
def            varchar(50),
cConv          varchar(50),
special        varchar(255),
flavor         varchar(255),
quantityOnHand int,
price          decimal(7,2),
themePack      bool,
starterPack    bool,
booster        bool,
packType       int,
rarity         int not null default 0,
packed         bool not null default false,
enabled        bool not null default false,
imageURL       varchar(150),
primary key (id));

CREATE TABLE PlayerCards(
userName      varchar(50) not null,
cardId        varchar(50) not null,
numberOfCards int,
primary key (userName, cardId));

CREATE TABLE PlayerDecks(
userName      varchar(50) not null,
name          varchar(50) not null,
cardId        varchar(50) not null,
numberOfCards int,
primary key (userName, name, cardId));

CREATE TABLE Users(
name         varchar(50) not null COLLATE latin1_bin, 
pword        varchar(50) not null COLLATE latin1_bin,
firstName    varchar(50),
lastName     varchar(50),
eMail        varchar(50),
phone        varchar(15),
shipAddress1 varchar(50),
shipAddress2 varchar(50),
shipCity     varchar(50),
shipState    varchar(50),
shipZip      varchar(15),
billAddress1 varchar(50),
billAddress2 varchar(50),
billCity     varchar(50),
billState    varchar(50),
billZip      varchar(15),
enabled      bool,
primary key (name));

CREATE TABLE Roles(
name        varchar(50) not null,
description varchar(100),
enabled     bool,
primary key (name));

CREATE TABLE Functions(
name    varchar(50) not null,
enabled bool,
primary key (name));

CREATE TABLE RoleFunctions(
roleName     varchar(50) not null,
functionName varchar(50) not null,
enabled      bool,
primary key (roleName, functionName));

CREATE TABLE UserRoles(
userName varchar(50) not null,
roleName varchar(50) not null,
enabled  bool,
primary key (userName, roleName));
