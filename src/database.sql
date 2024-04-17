CREATE TABLE `Admin`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255) NOT NULL,
    `psw` VARCHAR(255) NOT NULL
);
ALTER TABLE
    `Admin` ADD UNIQUE `admin_email_unique`(`email`);
CREATE TABLE `Purchase`(
    `id_order` BIGINT UNSIGNED NOT NULL,
    `user` BIGINT UNSIGNED NOT NULL,
    `watch` BIGINT UNSIGNED NOT NULL,
    `quantity` SMALLINT UNSIGNED NOT NULL DEFAULT '1',
    `IVA` SMALLINT NOT NULL,
    `price` DOUBLE(8, 2) NOT NULL,
    PRIMARY KEY(`id_order`)
);
ALTER TABLE
    `Purchase` ADD PRIMARY KEY(`user`);
ALTER TABLE
    `Purchase` ADD PRIMARY KEY(`watch`);
CREATE TABLE `Brand`(
    `business_name` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    PRIMARY KEY(`business_name`)
);
CREATE TABLE `Favourite`(
    `watch` BIGINT UNSIGNED NOT NULL,
    `user` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY(`watch`)
);
ALTER TABLE
    `Favourite` ADD PRIMARY KEY(`user`);
CREATE TABLE `Cart`(
    `user` BIGINT UNSIGNED NOT NULL,
    `watch` BIGINT UNSIGNED NOT NULL,
    `quantity` SMALLINT UNSIGNED NOT NULL DEFAULT '1',
    PRIMARY KEY(`user`)
);
ALTER TABLE
    `Cart` ADD PRIMARY KEY(`watch`);
CREATE TABLE `Operation`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `admin` BIGINT NOT NULL,
    `watch` BIGINT NOT NULL,
    `operation` VARCHAR(255) NOT NULL,
    `date` DATE NOT NULL
);
CREATE TABLE `Image`(
    `id` BIGINT UNSIGNED NOT NULL,
    `watch` BIGINT UNSIGNED NOT NULL,
    `image` BLOB NOT NULL,
    PRIMARY KEY(`id`)
);
ALTER TABLE
    `Image` ADD PRIMARY KEY(`watch`);
CREATE TABLE `User`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255) NOT NULL,
    `psw` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `surname` VARCHAR(255) NOT NULL,
    `birthday` DATE NULL,
    `road` VARCHAR(255) NOT NULL,
    `civic_number` VARCHAR(255) NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `CAP` CHAR(5) NOT NULL
);
ALTER TABLE
    `User` ADD UNIQUE `user_email_unique`(`email`);
CREATE TABLE `Review`(
    `watch` BIGINT UNSIGNED NOT NULL,
    `user` BIGINT UNSIGNED NOT NULL,
    `stars` SMALLINT UNSIGNED NOT NULL,
    `description` VARCHAR(255) NULL,
    `date` DATE NOT NULL DEFAULT 'GETDATE()',
    PRIMARY KEY(`watch`)
);
ALTER TABLE
    `Review` ADD PRIMARY KEY(`user`);
CREATE TABLE `Watch`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `brand` BIGINT NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `reviews_avg` DOUBLE(8, 2) NOT NULL DEFAULT '0',
    `price` DOUBLE(8, 2) NOT NULL,
    `material` VARCHAR(255) NOT NULL,
    `stock` INT NOT NULL DEFAULT '0',
    `dimension` DOUBLE(8, 2) NOT NULL COMMENT 'in mm',
    `IVA` SMALLINT NOT NULL,
    `sex` ENUM('') NOT NULL COMMENT 'METTERE man, women, unisex',
    `visible` TINYINT(1) NOT NULL DEFAULT '1'
);
ALTER TABLE
    `Operation` ADD CONSTRAINT `operation_admin_foreign` FOREIGN KEY(`admin`) REFERENCES `Admin`(`id`);
ALTER TABLE
    `Watch` ADD CONSTRAINT `watch_id_foreign` FOREIGN KEY(`id`) REFERENCES `Purchase`(`watch`);
ALTER TABLE
    `Watch` ADD CONSTRAINT `watch_brand_foreign` FOREIGN KEY(`brand`) REFERENCES `Brand`(`business_name`);
ALTER TABLE
    `Watch` ADD CONSTRAINT `watch_id_foreign` FOREIGN KEY(`id`) REFERENCES `Favourite`(`watch`);
ALTER TABLE
    `Watch` ADD CONSTRAINT `watch_id_foreign` FOREIGN KEY(`id`) REFERENCES `Review`(`watch`);
ALTER TABLE
    `Operation` ADD CONSTRAINT `operation_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`);
ALTER TABLE
    `User` ADD CONSTRAINT `user_id_foreign` FOREIGN KEY(`id`) REFERENCES `Cart`(`user`);
ALTER TABLE
    `User` ADD CONSTRAINT `user_id_foreign` FOREIGN KEY(`id`) REFERENCES `Review`(`user`);
ALTER TABLE
    `User` ADD CONSTRAINT `user_id_foreign` FOREIGN KEY(`id`) REFERENCES `Purchase`(`user`);
ALTER TABLE
    `Watch` ADD CONSTRAINT `watch_id_foreign` FOREIGN KEY(`id`) REFERENCES `Cart`(`watch`);
ALTER TABLE
    `Watch` ADD CONSTRAINT `watch_id_foreign` FOREIGN KEY(`id`) REFERENCES `Image`(`watch`);
ALTER TABLE
    `User` ADD CONSTRAINT `user_id_foreign` FOREIGN KEY(`id`) REFERENCES `Favourite`(`user`);