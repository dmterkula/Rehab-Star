INSERT INTO USERS(username, email, password) VALUES('dmterk', 'myEmail@domain.com', 'myPass');
INSERT INTO USERS(username, email, password) VALUES('Eoin', 'EoinWithAnE@wheresThePrinter.com', '12345');
INSERT INTO USERS(username, email, password) VALUES('Taylor', 'pm_me_security_jobs', 'asdfghjkl');
INSERT INTO USERS(username, email, password) VALUES('Emma', 'testEmail.com', '54321');



INSERT INTO STORIES(userId, fileName, title, dateCreated) VALUES('1', 'story1.txt', '18 Days Clean', CURRENT_TIMESTAMP);
INSERT INTO STORIES(userId, fileName, title, dateCreated) VALUES('2', 'story2.txt', 'My First Relapse', CURRENT_TIMESTAMP);
INSERT INTO STORIES(userId, fileName, title, dateCreated) VALUES('1', 'story3.txt', '3 weeks clean', CURRENT_TIMESTAMP-500);

INSERT INTO FOLLOWERS(userId, followingId) VALUES(1, 2);
INSERT INTO FOLLOWERS(userId, followingId) VALUES(1, 3);