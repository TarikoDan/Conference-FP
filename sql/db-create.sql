DROP DATABASE IF EXISTS conference;
CREATE DATABASE IF NOT EXISTS conference DEFAULT CHARACTER SET utf8;
USE conference;

DROP TABLE IF EXISTS event_report;
DROP TABLE IF EXISTS event_visitor;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS report;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS role;

CREATE TABLE role (
    id INT PRIMARY KEY,
    title ENUM('MODERATOR', 'SPEAKER', 'VISITOR') NOT NULL
);

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    role_id INT,
    CONSTRAINT fk_user_role_id FOREIGN KEY (role_id)
        REFERENCES role (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE report (
    id INT AUTO_INCREMENT PRIMARY KEY,
    topic VARCHAR(100) NOT NULL,
    speaker INT,
    CONSTRAINT fk_report_user_id FOREIGN KEY (speaker)
        REFERENCES user (id)
        ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE location (
    id INT AUTO_INCREMENT PRIMARY KEY,
    zipcode INT,
    country VARCHAR(30),
    region VARCHAR(30),
    city VARCHAR(30) NOT NULL,
    street VARCHAR(30) NOT NULL,
    building VARCHAR(10) NOT NULL,
    suite VARCHAR(10)
);


CREATE TABLE event (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    date DATE NOT NULL,
    location INT,
    CONSTRAINT fk_event_Location_id FOREIGN KEY (location)
        REFERENCES location (id)
        ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE event_report (
    event_id INT NOT NULL,
    report_id INT NOT NULL,
    PRIMARY KEY (event_id , report_id),
    CONSTRAINT fk_event_report_event_id FOREIGN KEY (event_id)
        REFERENCES event (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_event_report_report_id FOREIGN KEY (report_id)
        REFERENCES report (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE event_visitor (
    event_id INT NOT NULL,
    visitor_id INT NOT NULL,
    is_visited BOOL DEFAULT FALSE,
    last_update DATETIME DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
    PRIMARY KEY (event_id , visitor_id),
    CONSTRAINT fk_event_visitor_event_id FOREIGN KEY (event_id)
        REFERENCES event (id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_event_visitor_user_id FOREIGN KEY (visitor_id)
        REFERENCES user (id)
        ON UPDATE CASCADE ON DELETE CASCADE
);



INSERT INTO role (id, title) VALUES (1, 'MODERATOR');
INSERT INTO role (id, title) VALUES (2, 'SPEAKER');
INSERT INTO role (id, title) VALUES (3, 'VISITOR');
INSERT INTO user (id, name, email, password, role_id) VALUES (DEFAULT, 'Admin', 'taras@gmail.com', 'admin', 1);
INSERT INTO user (id, name, email, password, role_id) VALUES (DEFAULT, 'Speaker', 'speaker@gmail.com', 'speaker', 2);
INSERT INTO user (id, name, email, password, role_id) VALUES (DEFAULT, 'Visitor1', 'v1@gmail.com', 'v1', 3);
INSERT INTO report (id, topic) VALUES (DEFAULT, 'newReport');
INSERT INTO event (id, date, title) VALUES (DEFAULT, '2021-01-31', 'event');
