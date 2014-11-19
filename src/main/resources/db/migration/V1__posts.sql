CREATE TABLE posts (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(128),
    content VARCHAR(65000), -- 65535 is the whole row size for MySQL
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);