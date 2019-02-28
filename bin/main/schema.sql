CREATE TABLE rss_entries (
    id INTEGER AUTO_INCREMENT,
    author VARCHAR(255),
    title VARCHAR(255),
    description VARCHAR(255),
    PRIMARY KEY(id),
    UNIQUE(author, title, description)
);