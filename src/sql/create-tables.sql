CREATE DATABASE IF NOT EXISTS landing_page DEFAULT CHARACTER SET 'utf8';

-- CREATE USER 'lpadmin'@'localhost' IDENTIFIED BY 'MANAGER'
-- GRANT ALL PRIVILEGES ON landing_page.* TO 'lpadmin'@'localhost';


DROP TABLE IF EXISTS `landing_page`.`abtesting`;

CREATE  TABLE `landing_page`.`abtesting` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) );


DROP TABLE IF EXISTS `landing_page`.`subscribers`;

CREATE  TABLE `landing_page`.`subscribers` (
  `id` INT NOT NULL DEFAULT 346 AUTO_INCREMENT ,
  `email` VARCHAR(128) NOT NULL ,
  `created` timestamp NOT NULL DEFAULT now(),
  `ip_adress` VARCHAR(15) NULL ,
  `ab_page` INT NULL ,
  PRIMARY KEY (`id`, `email`) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) );
