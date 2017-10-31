DROP TABLE USERS IF exists;
CREATE TABLE USERS(id INTEGER NOT NULL AUTO_INCREMENT,
              username VARCHAR(255) NOT NULL, -- everyone has a first name
              email VARCHAR(255) NOT NULL,  -- everyone has a last name
              password VARCHAR(255) NOT NULL,   -- everyone has a password
              PRIMARY KEY(id)
              );

DROP TABLE STORIES IF exists;
CREATE TABLE STORIES(id INTEGER NOT NULL AUTO_INCREMENT,
                    userId INTEGER NOT NULL,
                    fileName VARCHAR(255) NOT NULL,
                    title VARCHAR(255) NOT NULL,
                    text BLOB,
                    dateCreated TIMESTAMP NOT NULL,
                    PRIMARY KEY(id),
                    FOREIGN KEY(userId) REFERENCES USERS(id)
                    );