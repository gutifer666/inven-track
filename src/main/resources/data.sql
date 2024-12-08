INSERT INTO users (enabled, name, password, email,rol)
VALUES (
        true,
        'admin',
        '$2a$10$tnC4pYqrAwCnDCFkFbjxV.PDE/b.fKI0aygmMQO0vKx5dki7WFT46',
        'admin@mail.com',
        'ROLE_ADMIN');
INSERT INTO users (enabled, name, password,email ,rol)
VALUES
    (
     true,
     'user',
     '$2a$10$PR4ElawJcWhuLoBPnP4CDeG1c0NSyGPteTq9AYcbDl8vB8sMZ/C4K',
     'user@mail.com',
     'ROLE_USER');