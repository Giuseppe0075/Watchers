DROP SCHEMA watchers;
CREATE DATABASE watchers;
USE watchers;
CREATE TABLE `Admin`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `psw` VARCHAR(255) NOT NULL
);

CREATE TABLE `User`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `psw` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `surname` VARCHAR(255) NOT NULL,
    `birthday` DATE NULL,
    `road` VARCHAR(255) NOT NULL,
    `civic_number` VARCHAR(255) NOT NULL,
    `city` VARCHAR(255) NOT NULL,
    `CAP` CHAR(5) NOT NULL
);

CREATE TABLE `Brand`(
    `business_name` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    PRIMARY KEY(`business_name`)
);

CREATE TABLE `Watch`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `brand` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `reviews_avg` DOUBLE(8, 2) NOT NULL DEFAULT '0',
    `price` DOUBLE(8, 2) NOT NULL,
    `material` VARCHAR(255) NOT NULL,
    `stock` INT NOT NULL DEFAULT '0',
    `dimension` DOUBLE(8, 2) NOT NULL COMMENT 'in mm',
    `IVA` SMALLINT NOT NULL,
    `sex` ENUM('MAN', 'WOMEN', 'UNISEX') NOT NULL DEFAULT 'UNISEX',
    `visible` TINYINT(1) NOT NULL DEFAULT '1',
    CONSTRAINT `watch_brand_foreign` FOREIGN KEY(`brand`) REFERENCES `Brand`(`business_name`)
);

CREATE TABLE `Purchase`(
    `id_order` BIGINT UNSIGNED NOT NULL,
    `user` BIGINT UNSIGNED NOT NULL,
    `watch` BIGINT UNSIGNED NOT NULL,
    `quantity` SMALLINT UNSIGNED NOT NULL DEFAULT '1',
    `IVA` SMALLINT NOT NULL,
    `price` DOUBLE(8, 2) NOT NULL,
    PRIMARY KEY(`id_order`, `user`, `watch`),
    CONSTRAINT `purchase_user_foreign` FOREIGN KEY(`user`) REFERENCES `User`(`id`),
    CONSTRAINT `purchase_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);

CREATE TABLE `Favourite`(
    `watch` BIGINT UNSIGNED NOT NULL,
    `user` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY(`watch`, `user`),
    CONSTRAINT `favourite_user_foreign` FOREIGN KEY(`user`) REFERENCES `User`(`id`),
    CONSTRAINT `favourite_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);

CREATE TABLE `Cart`(
    `user` BIGINT UNSIGNED NOT NULL,
    `watch` BIGINT UNSIGNED NOT NULL,
    `quantity` SMALLINT UNSIGNED NOT NULL DEFAULT '1',
    PRIMARY KEY(`user`, `watch`),
    CONSTRAINT `cart_user_foreign` FOREIGN KEY(`user`) REFERENCES `User`(`id`),
    CONSTRAINT `cart_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);

CREATE TABLE `Operation`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `admin` BIGINT UNSIGNED NOT NULL,
    `watch` BIGINT UNSIGNED NOT NULL,
    `operation` VARCHAR(255) NOT NULL,
    `date` DATE NOT NULL,
    CONSTRAINT `operation_admin_foreign` FOREIGN KEY(`admin`) REFERENCES `Admin`(`id`),
    CONSTRAINT `operation_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);

CREATE TABLE `Review`(
    `watch` BIGINT UNSIGNED NOT NULL,
    `user` BIGINT UNSIGNED NOT NULL,
    `stars` SMALLINT UNSIGNED NOT NULL,
    `description` VARCHAR(255) NULL,
    `date` DATE NOT NULL,
    PRIMARY KEY(`watch`, `user`),
    CONSTRAINT `review_user_foreign` FOREIGN KEY(`user`) REFERENCES `User`(`id`),
    CONSTRAINT `review_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);

CREATE TABLE `Image`(
    `id` BIGINT UNSIGNED NOT NULL,
    `watch` BIGINT UNSIGNED NOT NULL,
    `image` BLOB NOT NULL,
    PRIMARY KEY(`id`, `watch`),
    CONSTRAINT `image_watch_foreign` FOREIGN KEY(`watch`) REFERENCES `Watch`(`id`)
);