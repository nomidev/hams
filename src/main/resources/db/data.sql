INSERT INTO `menu` (`created_by`, `creation_date`, `last_modified_by`, `last_update_date`, `menu_active_code`, `menu_desc`, `menu_icon`, `menu_level_no`, `menu_name`, `menu_role`, `menu_url`, `parent_id`, `sort_order_no`, `use_flag`) VALUES
	(1, now(), 1, now(), '', 'HAMS', NULL, 0, 'HAMS', '', '', '#', 1, 1),
	(1, now(), 1, now(), 'admin', 'Admin', NULL, 1, 'Admin', 'ROLE_ADMIN', '/admin', '1', 1, 1),
	(1, now(), 1, now(), 'admin', 'Menu', NULL, 2, 'Menu', 'ROLE_ADMIN', '/admin/menu', '2', 1, 1);

INSERT INTO hams.menu (id, created_by, creation_date, last_modified_by, last_update_date, menu_active_code, menu_desc, menu_icon, menu_level_no, menu_name, menu_role, menu_url, parent_id, sort_order_no, use_flag) VALUES (1, 1, '2026-03-12 10:45:55.000000', 1, '2026-03-12 10:45:55.000000', '', 'HAMS', null, 0, 'HAMS', '', '', '#', 1, 1);
INSERT INTO hams.menu (id, created_by, creation_date, last_modified_by, last_update_date, menu_active_code, menu_desc, menu_icon, menu_level_no, menu_name, menu_role, menu_url, parent_id, sort_order_no, use_flag) VALUES (2, 1, '2026-03-12 10:45:55.000000', 1, '2026-03-12 10:45:55.000000', 'admin', 'Admin', null, 1, 'Admin', 'ROLE_ADMIN', '/admin', '1', 1, 1);
INSERT INTO hams.menu (id, created_by, creation_date, last_modified_by, last_update_date, menu_active_code, menu_desc, menu_icon, menu_level_no, menu_name, menu_role, menu_url, parent_id, sort_order_no, use_flag) VALUES (3, 1, '2026-03-12 10:45:55.000000', 1, '2026-03-12 10:45:55.000000', 'admin', 'Menu', null, 2, 'Menu', 'ROLE_ADMIN', '/admin/menu', '2', 1, 1);
INSERT INTO hams.menu (id, created_by, creation_date, last_modified_by, last_update_date, menu_active_code, menu_desc, menu_icon, menu_level_no, menu_name, menu_role, menu_url, parent_id, sort_order_no, use_flag) VALUES (4, 1, '2026-03-12 11:14:04.664104', 1, '2026-03-12 11:14:44.739240', 'admin', null, null, 2, '게시판관리', 'ROLE_ADMIN', '/admin/bulletin', '2', 2, 1);
INSERT INTO hams.menu (id, created_by, creation_date, last_modified_by, last_update_date, menu_active_code, menu_desc, menu_icon, menu_level_no, menu_name, menu_role, menu_url, parent_id, sort_order_no, use_flag) VALUES (5, 1, '2026-03-12 11:35:07.396314', 1, '2026-03-12 11:35:07.396314', 'admin', '코드관리', null, 2, '코드관리', 'ROLE_ADMIN', '/admin/commonCode', '2', 3, 1);
INSERT INTO hams.menu (id, created_by, creation_date, last_modified_by, last_update_date, menu_active_code, menu_desc, menu_icon, menu_level_no, menu_name, menu_role, menu_url, parent_id, sort_order_no, use_flag) VALUES (6, 1, '2026-03-12 11:52:01.429320', 1, '2026-03-12 11:52:01.429320', 'NOTICE', '공지사항', null, 1, '공지사항', 'ROLE_USER', '/board/list?bulletin_id=1', '1', 2, 1);


INSERT INTO `hams`.`role` (`created_by`, `creation_date`, `last_update_date`, `role_code`, `role_name`) VALUES (1, now(), now(), 'ROLE_ADMIN', 'ADMIN');
INSERT INTO `hams`.`role` (`created_by`, `creation_date`, `last_update_date`, `role_code`, `role_name`) VALUES (1, now(), now(), 'ROLE_MANAGER', 'MANAGER');
INSERT INTO `hams`.`role` (`created_by`, `creation_date`, `last_update_date`, `role_code`, `role_name`) VALUES (1, now(), now(), 'ROLE_USER', 'USER');


INSERT INTO hams.common_code (id, created_by, creation_date, last_modified_by, last_update_date, attribute1, attribute2, attribute3, attribute4, attribute5, code, code_desc, code_name, code_type, parent_code, sort_no, use_flag) VALUES (1, 1, '2026-03-12 11:45:23.576313', 1, '2026-03-12 11:45:23.576313', '', null, null, null, null, 'NOTICE', '공지사항', '공지사항', 'BULLETIN', '', 1, 1);

INSERT INTO hams.bulletin (id, created_by, creation_date, last_modified_by, last_update_date, title, type, use_flag) VALUES (1, 1, '2026-03-12 11:46:32.530170', 1, '2026-03-12 11:46:32.530170', 'Notice', 'NOTICE', 1);
