-- WindAndWaterInit.sql
-- This file populates all of the initial information into tables for initial functionality

USE playmat;

CALL RoleInsert('Admin', 'Immutable Administrative Role.');

CALL RoleInsert('Player', 'Base Role For Users.');

CALL UserSet('admin', 'admin',  'admin', 'admin', '', 1);

CALL UserRoleSet('admin', 'Admin', 1);