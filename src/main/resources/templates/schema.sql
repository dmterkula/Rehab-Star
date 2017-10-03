DROP TABLE users IF exists;
CREATE TABLE users(id INTEGER NOT NULL AUTOINCREMENT,
              userName VARCHAR(255) NOT NULL, -- everyone has a first name
              email VARCHAR(255) NOT NULL,  -- everyone has a last name
              password VARCHAR(255) NOT NULL,   -- everyone has a password
              PRIMARY KEY(id)
              );
