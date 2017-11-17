BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `user` (
	`id`	INTEGER,
	`name`	TEXT,
	`surname`	TEXT,
	`login`	TEXT,
	`password`	TEXT,
	PRIMARY KEY(`id`)
);
COMMIT;
