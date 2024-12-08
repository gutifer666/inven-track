CREATE TABLE users (
                       id INT NOT NULL AUTO_INCREMENT,
                       enabled BOOLEAN NOT NULL,
                       name VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100),
                       rol VARCHAR(50) NOT NULL,
                      PRIMARY KEY (id)
);
