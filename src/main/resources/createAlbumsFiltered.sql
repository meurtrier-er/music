CREATE TABLE IF NOT EXISTS albums_filtered
(
    Id SERIAL PRIMARY KEY,
	albumName VARCHAR(50),
	singerId INTEGER,
	genreId INTEGER
);