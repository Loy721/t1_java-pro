CREATE TABLE users(
                      id bigserial PRIMARY KEY,
                      user_name VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO users (user_name) VALUES ('Ivanov Ivan1');
INSERT INTO users (user_name) VALUES ('Ivanov Ivan2');
INSERT INTO users (user_name) VALUES ('Ivanov Ivan3');
INSERT INTO users (user_name) VALUES ('Ivanov Ivan4');
INSERT INTO users (user_name) VALUES ('Ivanov Ivan5');