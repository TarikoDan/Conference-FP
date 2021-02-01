DROP DATABASE IF EXISTS conference;
CREATE DATABASE IF NOT EXISTS conference;
USE conference;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;

CREATE TABLE role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    role_id INT,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id)
        REFERENCES role (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO role (id, title) VALUES (DEFAULT, 'MODERATOR');
INSERT INTO role (id, title) VALUES (DEFAULT, 'SPEAKER');
INSERT INTO role (id, title) VALUES (DEFAULT, 'VISITOR');
INSERT INTO user (id, name, email, password, role_id) VALUES (DEFAULT, 'Admin', 'taras@gmail.com', 'admin', 1);
INSERT INTO user (id, name, email, password, role_id) VALUES (DEFAULT, 'Speaker', 'speaker@gmail.com', 'speaker', 2);
