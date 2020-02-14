INSERT INTO ad_role (ad_role_id, isactive, description, name)
    VALUES (1, true, 'Role from Administrator', 'ADMIN');
INSERT INTO ad_role (ad_role_id, isactive, description, name)
    VALUES (2, false, 'Role from User', 'USER');

INSERT INTO ad_user (ad_user_id, isactive, email, name, password)
    VALUES (1, true, 'admin@bootpiere.com', 'admin', '$2a$10$0R6hevVx8mCtdf22EAewU.GRz0vuTPdtI/LDLJDi6zeeongjKIXjC');
INSERT INTO ad_user (ad_user_id, isactive, email, name, password)
    VALUES (2, true, 'user@bootpiere.com', 'user', '$2a$10$1xnOIVmThfqwznpL7VO9p.rpymQP6B5nHB1kyVj1ASs4IUUAXcpNq');


INSERT INTO user_role (ad_user_id, ad_role_id)
    VALUES (1, 1);
INSERT INTO user_role (ad_user_id, ad_role_id)
    VALUES (2, 2);