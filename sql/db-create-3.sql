DROP DATABASE IF EXISTS conference3;
CREATE DATABASE IF NOT EXISTS conference3 DEFAULT CHARACTER SET utf8;
USE conference3;

DROP TABLE IF EXISTS event_report;
DROP TABLE IF EXISTS event_visitor;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS report;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS language;
DROP TABLE IF EXISTS role;

CREATE TABLE role (
  id INT PRIMARY KEY,
  title ENUM('MODERATOR', 'SPEAKER', 'VISITOR') NOT NULL
);

CREATE TABLE language (
  id INT AUTO_INCREMENT PRIMARY KEY,
  value VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE local (
   id INT AUTO_INCREMENT PRIMARY KEY,
   entity_id INT NOT NULL,
   user_name VARCHAR(30),
   report_topic VARCHAR(30),
   location_country VARCHAR(30),
   location_city VARCHAR(30),
   language_id INT NOT NULL,
   CONSTRAINT fk_local_language_id FOREIGN KEY (language_id)
       REFERENCES language (id)
       ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name_id INT NOT NULL,
  email VARCHAR(30) NOT NULL UNIQUE,
  password VARCHAR(30) NOT NULL,
  role_id INT,
  CONSTRAINT fk_user_role_id FOREIGN KEY (role_id)
      REFERENCES role (id)
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_user_local_name_id FOREIGN KEY (name_id)
      REFERENCES local (id)
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE report (
    id INT AUTO_INCREMENT PRIMARY KEY,
    topic_id INT NOT NULL,
    speaker INT,
    CONSTRAINT fk_report_user_id FOREIGN KEY (speaker)
        REFERENCES user (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    CONSTRAINT fk_report_local_topic_id FOREIGN KEY (topic_id)
        REFERENCES local (id)
        ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE location (
  id INT AUTO_INCREMENT PRIMARY KEY,
  zipcode INT,
  country_id INT,
  city_id INT NOT NULL,
  CONSTRAINT fk_location_local_country FOREIGN KEY (country_id)
      REFERENCES local (id)
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_location_local_city FOREIGN KEY (city_id)
      REFERENCES local (id)
      ON UPDATE CASCADE ON DELETE CASCADE

);


CREATE TABLE event (
   id INT AUTO_INCREMENT PRIMARY KEY,
   title_id INT NOT NULL,
   date DATE NOT NULL,
   location INT,
   CONSTRAINT fk_event_Location_id FOREIGN KEY (location)
       REFERENCES location (id)
       ON UPDATE CASCADE ON DELETE SET NULL,
   CONSTRAINT fk_event_local_title FOREIGN KEY (title_id)
       REFERENCES local (id)
       ON UPDATE CASCADE ON DELETE CASCADE
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
   visited BOOL DEFAULT FALSE,
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
INSERT INTO language (id, value) VALUES (DEFAULT, 'en');
INSERT INTO language (id, value) VALUES (DEFAULT, 'ua');
# INSERT INTO local (id, entity_id, user_name, report_topic, location_country, location_city, language_id) VALUES (DEFAULT, 1, 'Admin', 'admin', 1);

# SET @id = (SELECT id FROM user WHERE email = 'taras@gmail.com');
INSERT INTO local (id, entity_id, user_name, language_id) VALUES (DEFAULT, 1, 'Admin', 1);
INSERT INTO local (id, entity_id, user_name, language_id) VALUES (DEFAULT, 2, 'Speaker', 1);
INSERT INTO local (id, entity_id, user_name, language_id) VALUES (DEFAULT, 3, 'Visitor1', 1);
INSERT INTO user (id, name_id, email, password, role_id) VALUES (DEFAULT, 1, 'taras@gmail.com', 'admin', 1);
INSERT INTO user (id, name_id, email, password, role_id) VALUES (DEFAULT, 2, 'speaker@gmail.com', 'speaker', 2);
INSERT INTO user (id, name_id, email, password, role_id) VALUES (DEFAULT, 3, 'v1@gmail.com', 'v1', 3);
