CREATE SEQUENCE IF NOT EXISTS person_sq MINVALUE 1 START WITH 1;
CREATE TABLE IF NOT EXISTS person (
   id INTEGER NOT NULL DEFAULT nextval('person_sq'),
   login varchar(2000) NOT NULL,
   password varchar(2000) NOT NULL,
   CONSTRAINT pk_person PRIMARY KEY(id)
);