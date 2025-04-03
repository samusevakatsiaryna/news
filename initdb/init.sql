CREATE TABLE news_types (
    id SERIAL PRIMARY KEY,
    type_name TEXT NOT NULL
);

INSERT INTO news_types (type_name) VALUES ('Crypto'), ('ETFs'), ('Stocks');

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role_name TEXT NOT NULL
);

INSERT INTO roles (role_name) VALUES ('admin'), ('user');

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role_id INTEGER REFERENCES roles(id)
);

INSERT INTO users (email, password, role_id)
VALUES
    ('admin@gmail.com', '123', 1),
    ('user@gmail.com', '123', 2);

CREATE TABLE news (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    published_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    news_type_id INTEGER REFERENCES news_types(id)
);

INSERT INTO news (title, content, news_type_id)
VALUES
    ('News 1', 'Content 1', 1),
    ('News 2', 'Content 2', 2);