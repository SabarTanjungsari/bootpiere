-- CREATE Login Roles "bootpiere"
-- CREATE DATABASE bootpiere WITH Owner bootpiere

INSERT INTO ad_role (description, name, created, createdby, updated, updatedby)
    VALUES ('Role from Administrator', 'ADMIN', now(), 1, now(), 1);
INSERT INTO ad_role (description, name, created, createdby, updated, updatedby)
    VALUES ('Role from User', 'USER', now(), 1, now(), 1);
INSERT INTO ad_role (description, name, created, createdby, updated, updatedby)
    VALUES ('Role from Accounting', 'ACCOUNTING', now(), 1, now(), 1);

INSERT INTO ad_user(email, email_password, name, password, password_encrypt,  created, createdby, updated, updatedby)
    VALUES ('system.tondira@gmail.com', 'dG9uZGlyYTEyMw==', 'bootpiere', '$2a$10$35kpqKu8Ry0oTq2Z2deNg.Hc3dsclsi1luGclF6JGFqTxcjdyZ.Km',
            'Ym9vdHBpZXJl', now(), 1, now(), 1);

INSERT INTO user_role (ad_user_id, ad_role_id)
    VALUES (1, 1);
--INSERT INTO user_role (ad_user_id, ad_role_id)
    --VALUES (1, 2);
--INSERT INTO user_role (ad_user_id, ad_role_id)
    --VALUES (1, 3);