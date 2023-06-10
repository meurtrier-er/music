CREATE TABLE IF NOT EXISTS genres
(
    Id SERIAL PRIMARY KEY,
    genreName VARCHAR(50)
);

INSERT INTO genres (genreName) VALUES ('Hip-hop');
INSERT INTO genres (genreName) VALUES ('Rap');
INSERT INTO genres (genreName) VALUES ('Pop');
