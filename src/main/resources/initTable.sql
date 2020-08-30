CREATE TABLE IF NOT EXISTS `test`.`test_shorteners` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime DEFAULT NULL,
  `origin_url` varchar(255) DEFAULT NULL,
  `shorten_url` varchar(255) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `test`.`test_chars` ;

CREATE TABLE IF NOT EXISTS `test`.`test_chars` (
  `id` int NOT NULL AUTO_INCREMENT ,
  `chars` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DELETE FROM `test`.`test_chars`;

INSERT INTO `test`.`test_chars` (`chars`) VALUES ('0');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('1');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('2');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('3');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('4');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('5');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('6');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('7');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('8');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('9');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('a');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('b');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('c');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('d');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('e');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('f');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('g');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('h');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('i');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('j');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('k');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('l');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('m');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('n');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('o');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('p');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('q');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('r');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('s');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('t');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('u');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('v');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('w');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('x');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('y');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('z');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('A');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('B');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('C');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('D');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('E');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('F');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('G');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('H');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('I');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('J');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('K');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('L');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('M');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('N');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('O');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('P');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('Q');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('R');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('S');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('T');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('U');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('V');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('W');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('X');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('Y');
INSERT INTO `test`.`test_chars` (`chars`) VALUES ('Z');