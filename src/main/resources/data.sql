-- Initialize h2's spatial functions
-- otherwise you will get an exception:
-- org.h2.jdbc.JdbcSQLException: Function "ST_WITHIN" not found; SQL statement:

CREATE ALIAS IF NOT EXISTS H2GIS_SPATIAL FOR "org.h2gis.functions.factory.H2GISFunctions.load";
CALL H2GIS_SPATIAL();
