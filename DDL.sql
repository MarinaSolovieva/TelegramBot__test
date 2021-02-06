CREATE TABLE my_db.cities
(
    id          bigint          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(30) UNIQUE,
    description VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);