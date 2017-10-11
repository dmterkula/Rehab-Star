DROP TABLE USERS IF exists;
CREATE TABLE USERS(id INTEGER NOT NULL AUTO_INCREMENT,
              username VARCHAR(255) NOT NULL, -- everyone has a first name
              email VARCHAR(255) NOT NULL,  -- everyone has a last name
              password VARCHAR(255) NOT NULL,   -- everyone has a password
              PRIMARY KEY(id)
              );