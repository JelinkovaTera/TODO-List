-- Create database if it doesn't exist
DROP DATABASE IF EXISTS todo_list;
CREATE DATABASE IF NOT EXISTS todo_list CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Select database
USE todo_list;

-- Create user if it doesn't exist (MySQL 8+ requires checking manually)
SELECT COUNT(*) INTO @userExists FROM mysql.user WHERE user = 'todo.user';
SET @query = IF(@userExists = 0, 'CREATE USER ''todo.user''@''%'' IDENTIFIED BY ''kobliha'';', 'SELECT 1;');
PREPARE stmt FROM @query;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Grant privileges to the user
GRANT ALL PRIVILEGES ON todo_list.* TO 'todo.user'@'%';

-- Apply changes
FLUSH PRIVILEGES;

-- Create status table first to avoid foreign key reference issues
CREATE TABLE IF NOT EXISTS todo_list.`status` (
  `status_id` INT NOT NULL AUTO_INCREMENT,
  `status_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`status_id`),
  UNIQUE KEY `status_name_UNIQUE` (`status_name`)
);

-- Create entries table
CREATE TABLE IF NOT EXISTS todo_list.`entries` (
  `entry_id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) DEFAULT NULL,
  `status_id` INT DEFAULT NULL,
  PRIMARY KEY (`entry_id`),
  KEY `status_id_idx` (`status_id`),
  CONSTRAINT `status_id_fk` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`) ON DELETE SET NULL
);

-- Insert default statuses if they don't exist
INSERT IGNORE INTO todo_list.`status` (`status_id`, `status_name`) VALUES 
(1, 'new'), 
(2, 'in progress'), 
(3, 'done'), 
(4, 'on hold');

-- Insert test data into entries table (ignore duplicates)
INSERT IGNORE INTO todo_list.entries (`entry_id`, `description`, `status_id`) VALUES 
(2, 'Pohladit kočku', 3),
(3, 'Nanosit dřevo', 1),
(4, 'Jít na procházku', 4),
(5, 'Dát si kakao', 3);

-- Verify inserted data
SELECT * FROM todo_list.status;
SELECT * FROM todo_list.entries;

DROP VIEW IF EXISTS todo_list.`entry_list`;


-- Create a view for easier access to entries with status names
CREATE VIEW todo_list.`entry_list` AS 
SELECT 
    e.`entry_id`, 
    e.`description`, 
    s.`status_name` 
FROM todo_list.`entries` e
LEFT JOIN todo_list.`status` s ON e.`status_id` = s.`status_id`;
