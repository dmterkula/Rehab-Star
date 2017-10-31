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
                    text BLOB,
                    PRIMARY KEY(id),
                    FOREIGN KEY(userId) REFERENCES USERS(id)
                    );

INSERT INTO USERS(username, email, password) VALUES('dmterk', 'myEmail@domain.com', 'myPass');
INSERT INTO USERS(username, email, password) VALUES('Eoin', 'EoinWithAnE@wheresThePrinter.com', '12345');

INSERT INTO STORIES(userId, fileName) VALUES('1', 'dmterkStory1.txt');
INSERT INTO STORIES(userId, fileName) VALUES('1', 'EoinStory1.txt');