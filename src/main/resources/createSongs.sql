CREATE TABLE IF NOT EXISTS songs
(
    Id SERIAL PRIMARY KEY,
    songName VARCHAR(50),
    songDuration INTEGER,
	albumId INTEGER,
	FOREIGN KEY (albumId) REFERENCES albums (Id)
);

INSERT INTO songs (songName, songDuration, albumId) VALUES ('Business', 251, 1);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Superman', 350, 1);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Till_I_Collapse', 297, 1);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Lucky_You', 244, 2);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Stepping_Stone', 309, 2);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Kamikaze', 216, 2);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Venom', 269, 2);

INSERT INTO songs (songName, songDuration, albumId) VALUES ('In_Da_Club', 227, 3);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('High_All_The_Time', 269, 3);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('P.I.M.P.', 254, 3);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Poor_Lil_Rich', 199, 3);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('This_Is_50', 184, 4);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Candy_Shop', 209, 4);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Just_A_Lil_Bit', 239, 4);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Hate_It_Or_Love_It', 263, 4);

INSERT INTO songs (songName, songDuration, albumId) VALUES ('7_rings', 178, 5);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('thank_u_next', 207, 5);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('break_up_with_your_girlfriend_i`m_bored', 190, 5);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('34+35', 173, 6);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('off_the_table', 239, 6);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('safety_net', 208, 6);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('pov', 201, 6);

INSERT INTO songs (songName, songDuration, albumId) VALUES ('Revival', 246, 7);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Same_Old_Love', 229, 7);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Good_For_you', 221, 7);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Boyfriend', 161, 8);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Lose_You_To_Love_Me', 206, 8);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('People_You_Know', 194, 8);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Fun', 189, 8);

INSERT INTO songs (songName, songDuration, albumId) VALUES ('Suge', 163, 9);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Baby_Sitter', 157, 9);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('Best_Friend', 186, 9);

INSERT INTO songs (songName, songDuration, albumId) VALUES ('DNA.', 185, 10);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('HUMBLE.', 176, 10);
INSERT INTO songs (songName, songDuration, albumId) VALUES ('LOVE.', 214, 10);
