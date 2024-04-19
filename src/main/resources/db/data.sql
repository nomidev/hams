INSERT INTO `menu` (`created_by`, `creation_date`, `last_modified_by`, `last_update_date`, `menu_active_code`, `menu_desc`, `menu_icon`, `menu_level_no`, `menu_name`, `menu_role`, `menu_url`, `parent_id`, `sort_order_no`, `use_flag`) VALUES
	(1, now(), 1, now(), '', 'HAMS', NULL, 0, 'HAMS', '', '', '#', 1, 1),
	(1, now(), 1, now(), 'admin', 'Admin', NULL, 1, 'Admin', 'ROLE_ADMIN', '/admin', '1', 1, 1),
	(1, now(), 1, now(), 'admin', 'Menu', NULL, 2, 'Menu', 'ROLE_ADMIN', '/admin/menu', '2', 1, 1);

INSERT INTO `hams`.`role` (`created_by`, `creation_date`, `last_update_date`, `role_code`, `role_name`) VALUES (1, now(), now(), 'ROLE_ADMIN', 'ADMIN');
INSERT INTO `hams`.`role` (`created_by`, `creation_date`, `last_update_date`, `role_code`, `role_name`) VALUES (1, now(), now(), 'ROLE_MANAGER', 'MANAGER');
INSERT INTO `hams`.`role` (`created_by`, `creation_date`, `last_update_date`, `role_code`, `role_name`) VALUES (1, now(), now(), 'ROLE_USER', 'USER');


