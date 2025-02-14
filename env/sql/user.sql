create table permission
(
  permission_id   int auto_increment comment '权限 id'
    primary key,
  permission_name varchar(32) not null comment '权限名',
  code            varchar(32) not null comment '权限代码',
  constraint permission_permission_name_code_uindex
    unique (permission_name, code)
) comment '权限表';

create table role
(
  role_id   int auto_increment comment '角色 id'
    primary key,
  role_name varchar(32) not null comment '角色名',
  code      varchar(32) not null comment '角色代码',
  constraint role_role_name_code_uindex
    unique (role_name, code)
) comment '角色表';

create table role_permission_map
(
  id            int auto_increment comment 'id'
    primary key,
  role_id       int null comment '角色 id',
  permission_id int not null comment '权限 id',
  constraint role_permission_map_permission_permission_id_fk
    foreign key (permission_id) references permission (permission_id),
  constraint role_permission_map_role_role_id_fk
    foreign key (role_id) references role (role_id)
) comment '角色-权限 表';

create table user
(
  user_id     bigint            not null comment '用户 id'
    primary key,
  user_name   varchar(32)       null comment '用户名',
  email       varchar(64)       null comment '邮箱',
  password    varchar(128)      null comment '密码',
  create_time datetime          null comment '创建时间',
  update_time datetime          null comment '更新时间',
  enable      tinyint default 1 not null comment '是否启用',
  constraint user_user_name_email_uindex
    unique (user_name, email)
) comment '用户表';

create table user_role_map
(
  id      int auto_increment comment 'id'
    primary key,
  user_id bigint not null comment '用户 id',
  role_id int    not null comment '角色 id',
  constraint user_role_map_role_role_id_fk
    foreign key (role_id) references role (role_id),
  constraint user_role_map_user_user_id_fk
    foreign key (user_id) references user (user_id)
) comment '用户-角色 表';



INSERT INTO permission (permission_id, permission_name, code)
VALUES (1, 'INSERT', '10');
INSERT INTO permission (permission_id, permission_name, code)
VALUES (2, 'DELETE', '20');
INSERT INTO permission (permission_id, permission_name, code)
VALUES (3, 'UPDATE', '30');
INSERT INTO permission (permission_id, permission_name, code)
VALUES (4, 'SELECT', '40');

INSERT INTO role (role_id, role_name, code)
VALUES (1, 'ADMIN', '100');
INSERT INTO role (role_id, role_name, code)
VALUES (2, 'USER', '200');

INSERT INTO user (user_id, user_name, email, password, create_time, update_time, enable)
# password is mcdd1024@pwd
VALUES (1878882461714399233, 'mcdd1024', 'mcdd1024@gmail.com',
        '{bcrypt}$2a$10$N3Of1D6gRKmrGzbtaYHAWurzUk67HLzExwh31Spab3MdPOV0bO3vy',
        '2025-01-14 03:11:01', '2025-01-14 03:12:55', 1);

INSERT INTO role_permission_map (id, role_id, permission_id)
VALUES (1, 1, 1);
INSERT INTO role_permission_map (id, role_id, permission_id)
VALUES (2, 1, 2);
INSERT INTO role_permission_map (id, role_id, permission_id)
VALUES (3, 1, 3);
INSERT INTO role_permission_map (id, role_id, permission_id)
VALUES (4, 1, 4);
INSERT INTO role_permission_map (id, role_id, permission_id)
VALUES (5, 2, 4);

INSERT INTO user_role_map (id, user_id, role_id)
VALUES (1, 1878882461714399233, 1);


