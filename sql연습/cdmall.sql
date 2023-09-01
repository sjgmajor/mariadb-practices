-- 앨범
CREATE TABLE `album` (
	`no`         INT         NOT NULL, -- 번호
	`title`      VARCHAR(45) NOT NULL, -- 제목
	`company_no` INT         NOT NULL  -- 배급사 번호
);
show tables;
-- 앨범
ALTER TABLE `album`
	ADD CONSTRAINT `PK_album` -- 앨범 기본키
		PRIMARY KEY (
			`no` -- 번호
		);

ALTER TABLE `album`
	MODIFY COLUMN `no` INT NOT NULL AUTO_INCREMENT;

-- 노래
CREATE TABLE `song` (
	`no`        INT          NOT NULL, -- 번호
	`title`     VARCHAR(100) NOT NULL, -- 제목
	`album_no`  INT          NOT NULL, -- 앨범번호
	`artist_no` INT          NOT NULL  -- 가수번호
);

-- 노래
ALTER TABLE `song`
	ADD CONSTRAINT `PK_song` -- 노래 기본키
		PRIMARY KEY (
			`no` -- 번호
		);

ALTER TABLE `song`
	MODIFY COLUMN `no` INT NOT NULL AUTO_INCREMENT;

-- 배급사
CREATE TABLE `company` (
	`no`   INT         NOT NULL, -- 번호
	`name` VARCHAR(45) NOT NULL, -- 이름
	`CEO`  VARCHAR(45) NULL      -- 대표자
);

-- 배급사
ALTER TABLE `company`
	ADD CONSTRAINT `PK_company` -- 배급사 기본키
		PRIMARY KEY (
			`no` -- 번호
		);

ALTER TABLE `company`
	MODIFY COLUMN `no` INT NOT NULL AUTO_INCREMENT;

-- 가수
CREATE TABLE `artist` (
	`no`      INT         NOT NULL, -- 번호
	`name`    VARCHAR(45) NULL,     -- 이름
	`profile` text        NULL      -- 프로필
);

-- 가수
ALTER TABLE `artist`
	ADD CONSTRAINT `PK_artist` -- 가수 기본키
		PRIMARY KEY (
			`no` -- 번호
		);

ALTER TABLE `artist`
	MODIFY COLUMN `no` INT NOT NULL AUTO_INCREMENT;

-- 장르
CREATE TABLE `genre` (
	`no`        INT         NOT NULL, -- 번호
	`name`      VARCHAR(45) NOT NULL, -- 이름
	`abbr_name` VARCHAR(10) NOT NULL  -- 약어
);

-- 장르
ALTER TABLE `genre`
	ADD CONSTRAINT `PK_genre` -- 장르 기본키
		PRIMARY KEY (
			`no` -- 번호
		);

ALTER TABLE `genre`
	MODIFY COLUMN `no` INT NOT NULL AUTO_INCREMENT;

-- 장르_노래
CREATE TABLE `genre_song` (
	`song_no`  INT NOT NULL, -- 노래번호
	`genre_no` INT NOT NULL  -- 번호
);

-- 앨범
ALTER TABLE `album`
	ADD CONSTRAINT `FK_company_TO_album` -- 배급사 -> 앨범
		FOREIGN KEY (
			`company_no` -- 배급사 번호
		)
		REFERENCES `company` ( -- 배급사
			`no` -- 번호
		);

-- 노래
ALTER TABLE `song`
	ADD CONSTRAINT `FK_artist_TO_song` -- 가수 -> 노래
		FOREIGN KEY (
			`album_no` -- 앨범번호
		)
		REFERENCES `artist` ( -- 가수
			`no` -- 번호
		);

-- 노래
ALTER TABLE `song`
	ADD CONSTRAINT `FK_album_TO_song` -- 앨범 -> 노래
		FOREIGN KEY (
			`artist_no` -- 가수번호
		)
		REFERENCES `album` ( -- 앨범
			`no` -- 번호
		);

-- 장르_노래
ALTER TABLE `genre_song`
	ADD CONSTRAINT `FK_song_TO_genre_song` -- 노래 -> 장르_노래
		FOREIGN KEY (
			`song_no` -- 노래번호
		)
		REFERENCES `song` ( -- 노래
			`no` -- 번호
		);

-- 장르_노래
ALTER TABLE `genre_song`
	ADD CONSTRAINT `FK_genre_TO_genre_song` -- 장르 -> 장르_노래
		FOREIGN KEY (
			`genre_no` -- 번호
		)
		REFERENCES `genre` ( -- 장르
			`no` -- 번호
		);