CREATE TABLE IF NOT EXISTS singers
(
    Id SERIAL PRIMARY KEY,
    singerName VARCHAR(50)
);

INSERT INTO singers (singerName) VALUES ('Eminem');
INSERT INTO singers (singerName) VALUES ('50_Cent');
INSERT INTO singers (singerName) VALUES ('Ariana_Grande');
INSERT INTO singers (singerName) VALUES ('Selena_Gomez');
INSERT INTO singers (singerName) VALUES ('DaBaby');
INSERT INTO singers (singerName) VALUES ('Kendrick_Lamar');