CREATE TABLE IF NOT EXISTS `request_response_log`
(
    `id`                int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `request`           VARCHAR(256) null,
    `request_timestamp` TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `thread_timestamp`
(
    `id`                int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    `counter_value`     VARCHAR(255) null,
    `request_timestamp` TIMESTAMP NOT NULL DEFAULT NOW(),
    `thread_name`       VARCHAR(255) null,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

