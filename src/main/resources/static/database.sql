create table passwords(
	id serial4 primary key,
	environment varchar(255),
	type varchar(255),
	username varchar(255),
	password varchar(255),
	updatedBy varchar(10),
	updatedAt timestamp,
    active int default 1
);

insert into passwords (environment, type, username, password, updatedAt, updatedBy)
values 
('dev', '182.195.81.24', 'superuser', '2023moffice!@', now(), 'nv.huy1'),
('dev', 'SempAdmin', 'C60_23339', 'admin123!', now(), 'nv.huy1'),
('dev', 'SempAdmin', 'C60_33557', 'admin123!', now(), 'nv.huy1'),
('dev', 'SempAdmin', 'C60_29930', 'qwer1212!', now(), 'nv.huy1'),
('dev', 'BasAdmin', 'C60_23339', 'admin123!', now(), 'nv.huy1'),
('dev', 'Portal', 'moffice.sds', 'qwer1212!', now(), 'nv.huy1'),
('dev', 'M.Office', 'heesuk0701.jeong^nsta0', 'qwert1212!', now(), 'nv.huy1'),
('dev', 'M.Office', 'jung_in.kim^nsta0', 'knoxportal1!', now(), 'nv.huy1'),
('dev', 'M.Office', 'ge.lee^nsta0', 'dkssud79@', now(), 'nv.huy1'),
('dev', 'M.Office', 'j06.oh^nsta0', 'qwer3434!', now(), 'nv.huy1'),
('dev', 'M.Office', 'ge.lee^nsta0', 'dkssud79@', now(), 'nv.huy1'),

('stg', 'BasAdmin', 'C60_23339', 'admin123!', now(), 'nv.huy1'),
('stg', 'BasAdmin', 'C60_23339', 'admin123!', now(), 'nv.huy1'),
('stg', 'BasAdmin', 'C60_33557', 'admin123!', now(), 'nv.huy1'),
('stg', 'BasAdmin', 'C60_29930', 'qwer1212!', now(), 'nv.huy1'),
('stg', '182.195.81.24', 'superuser', '2023moffice!@', now(), 'nv.huy1'),
('stg', 'SempAdmin', 'C60_23339', 'admin123!', now(), 'nv.huy1'),
('dev', 'SempAdmin', 'C60_33557', 'admin123!', now(), 'nv.huy1'),
('stg', 'SempAdmin', 'C60_29930', 'qwer1212!', now(), 'nv.huy1'),

('prod', '182.195.85.58', 'superuser', '2024moffice!@', now(), 'nv.huy1'),
('prod', '182.195.85.59', 'superuser', '2024moffice!@', now(), 'nv.huy1'),
('prod', 'BasAdmin', 'C60_23339', 'admin123!', now(), 'nv.huy1'),
('prod', 'BasAdmin', 'C60_33557', 'admin123!', now(), 'nv.huy1'),
('prod', 'SempAdmin', 'C60_23339', 'admin123!', now(), 'nv.huy1'),
('prod', 'SempAdmin', 'C60_33557', 'admin123!', now(), 'nv.huy1'),
('prod', 'SempAdmin', 'C60_29930', 'qwer1212!', now(), 'nv.huy1'),

('common', 'Wellstory', 'h0723', 'test12#$', now(), 'nv.huy1'),
('common', 'Wellstory', 'suhopang', 'qwer12!@', now(), 'nv.huy1'),
('common', 'MofficeSDS', 'moffice.sds', 'qwer1212!', now(), 'nv.huy1'),
('common', 'MofficeApp', 'moffice.app', 'qwer1212!', now(), 'nv.huy1'),
('common', 'Wifi', 'admin', 'Abc!3579', now(), 'nv.huy1'),
('common', 'Wifi', 'gdcv2021', 'Abc!3579', now(), 'nv.huy1'),
('common', 'Adobe', 'ge.lee@samsung.com', 'dkssud79@', now(), 'nv.huy1'),
('common', 'Adobe', 'seo_y.park@samsung.com', 'Qwer12!@', now(), 'nv.huy1'),
('common', 'SonarQube', 'moffice.sds', 'qwer1212!', now(), 'nv.huy1'),
('common', 'S-Core', 'ori79.kim', 'Thangbay@07!', now(), 'nv.huy1'),
('common', 'S-Core', 'shiny.park', '2022Abc!@#', now(), 'nv.huy1'),
('common', 'S-Core', 'goun.yu', 'dptmzhdj3!', now(), 'nv.huy1'),
('common', 'S-Core', 'hye2n.seo', 'dptmzhdj4!', now(), 'nv.huy1');

truncate table passwords;
select * from passwords;

