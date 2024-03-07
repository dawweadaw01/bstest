-- 用户表
CREATE TABLE `sys_user` (
                            `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                            `username` VARCHAR(50) NOT NULL,
                            `password` VARCHAR(100) NOT NULL,
    -- 可根据实际需求添加其他字段，如邮箱、手机号等
                            `email` VARCHAR(100),
                            `phone` VARCHAR(20),
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `username_unique` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 角色表
CREATE TABLE `sys_role` (
                            `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                            `name` VARCHAR(50) NOT NULL,
                            `description` VARCHAR(255),
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `name_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 权限表
CREATE TABLE `sys_permission` (
                                  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                  `name` VARCHAR(50) NOT NULL,
                                  `description` VARCHAR(255),
                                  `url` VARCHAR(255) NOT NULL,
                                  `method` VARCHAR(10) NOT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 用户角色关系表
CREATE TABLE `sys_user_role` (
                                 `user_id` BIGINT(20) NOT NULL,
                                 `role_id` BIGINT(20) NOT NULL,
                                 PRIMARY KEY (`user_id`, `role_id`),
                                 CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
                                 CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 角色权限关系表
CREATE TABLE `sys_role_permission` (
                                       `role_id` BIGINT(20) NOT NULL,
                                       `permission_id` BIGINT(20) NOT NULL,
                                       PRIMARY KEY (`role_id`, `permission_id`),
                                       CONSTRAINT `fk_role_permission_role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
                                       CONSTRAINT `fk_role_permission_permission` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- 角色权限关系表
CREATE TABLE `sys_role_permission` (
                                       `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                       `role_id` BIGINT(20) NOT NULL,
                                       `permission_id` BIGINT(20) NOT NULL,
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `role_permission_unique` (`role_id`, `permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--猫咪种类信息表（cat_categories）
CREATE TABLE cat_categories (
                                category_id INT AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(255) NOT NULL COMMENT '猫咪种类名称',
                                origin VARCHAR(255) COMMENT '猫咪种类的原产地',
                                description TEXT COMMENT '种类描述'
) COMMENT='猫咪种类信息表，存储各种猫咪的种类信息';


--具体猫咪信息表（cats）
CREATE TABLE cat (
                     cat_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '猫咪ID',
                     category_id INT COMMENT '猫咪种类ID，外键，引用cat_categories表的category_id',
                     name VARCHAR(255) NOT NULL COMMENT '猫咪名称',
                     age INT COMMENT '猫咪年龄',
                     gender VARCHAR(50) COMMENT '猫咪性别',
                     preferences VARCHAR(255) COMMENT '猫咪喜好',
                     health_status VARCHAR(255) COMMENT '猫咪健康状态',
                     available_for_adoption BOOLEAN COMMENT '是否可领养'
) COMMENT='具体猫咪信息表，存储每只猫咪的详细信息';
