-- CREATE Login Roles "bootpiere"
-- CREATE DATABASE bootpiere WITH Owner bootpiere

INSERT INTO ad_role (description, name, created, createdby, updated, updatedby)
    VALUES ('Role from Administrator', 'ADMIN', now(), 1, now(), 'ADMIN');
INSERT INTO ad_role (description, name, created, createdby, updated, updatedby)
    VALUES ('Role from User', 'USER', now(), 'ADMIN', now(), 'ADMIN');
INSERT INTO ad_role (description, name, created, createdby, updated, updatedby)
    VALUES ('Role from Accounting', 'ACCOUNTING', now(), 'ADMIN', now(), 'ADMIN');

INSERT INTO ad_user(email, email_password, name, password, password_encrypt,  created, createdby, updated, updatedby)
    VALUES ('system.tondira@gmail.com', 'dG9uZGlyYTEyMw==', 'bootpiere', '$2a$10$35kpqKu8Ry0oTq2Z2deNg.Hc3dsclsi1luGclF6JGFqTxcjdyZ.Km',
            'Ym9vdHBpZXJl', now(), 'ADMIN', now(), 'ADMIN');
INSERT INTO ad_system(ad_system_id, createdby, updatedby, chatid, name, token)
VALUES (1, 1000000, 1000000, 236038826, 'ID', '933380920:AAHB01EdjkSZGZc-KaCUCIsgCT4P_CfIFSs');

INSERT INTO user_role (ad_user_id, ad_role_id)
    VALUES (1, 1);
--INSERT INTO user_role (ad_user_id, ad_role_id)
    --VALUES (1, 2);
--INSERT INTO user_role (ad_user_id, ad_role_id)
    --VALUES (1, 3);