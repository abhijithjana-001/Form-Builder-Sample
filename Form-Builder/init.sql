-- init.sql
-- init.sql

-- Create your database
CREATE DATABASE formbuilder1;

-- Switch to the newly created database
\c formbuilder1;

-- Create a user and grant necessary privileges
CREATE USER postgres WITH PASSWORD '1234';
GRANT ALL PRIVILEGES ON DATABASE formbuilder1 TO postgres;
