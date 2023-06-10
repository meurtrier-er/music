CREATE TABLE IF NOT EXISTS albums
(
    Id SERIAL PRIMARY KEY,
    albumName VARCHAR(50),
	albumGenreId INTEGER,
	singerId INTEGER,
	FOREIGN KEY (albumGenreId) REFERENCES genres (Id),
	FOREIGN KEY (singerId) REFERENCES singers (Id)	
);

INSERT INTO albums (albumName, albumGenreId, singerId) VALUES ('The_Eminem_Show', 1, 1);
INSERT INTO albums (albumName, albumGenreId, singerId) VALUES ('Kamikaze', 1, 1);
INSERT INTO albums (albumName, albumGenreId, singerId) VALUES ('Get_rich_Or_Die_Tryin`', 2, 2);
INSERT INTO albums (albumName, albumGenreId, singerId) VALUES ('The_Massacre', 2, 2);
INSERT INTO albums (albumName, albumGenreId, singerId) VALUES ('thank_u_next', 3, 3);
INSERT INTO albums (albumName, albumGenreId, singerId) VALUES ('Positions', 3, 3);
INSERT INTO albums (albumName, albumGenreId, singerId) VALUES ('Revival', 3, 4);
INSERT INTO albums (albumName, albumGenreId, singerId) VALUES ('Rare', 3, 4);
INSERT INTO albums (albumName, albumGenreId, singerId) VALUES ('Baby_On_Baby', 1, 5);
INSERT INTO albums (albumName, albumGenreId, singerId) VALUES ('DAMN.', 1, 6);
