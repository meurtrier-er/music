CREATE TABLE IF NOT EXISTS songs_filtered
(
    Id SERIAL PRIMARY KEY,
	songName VARCHAR(50),
	songDuration INTEGER,
	albumId INTEGER
);