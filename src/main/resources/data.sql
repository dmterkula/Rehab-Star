INSERT INTO USERS(username, email, password) VALUES('dmterk', 'myEmail@domain.com', 'myPass');
INSERT INTO USERS(username, email, password) VALUES('Eoin', 'EoinWithAnE@wheresThePrinter.com', '12345');

INSERT INTO STORIES(userId, fileName, title, dateCreated) VALUES('1', 'story1.txt', '18 Days Clean', CURRENT_TIMESTAMP);
INSERT INTO STORIES(userId, fileName, title, dateCreated) VALUES('2', 'story2.txt', 'My First Relapse', CURRENT_TIMESTAMP);