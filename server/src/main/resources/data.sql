--分别在roleAuths表中插入角色
--插入student
if not exists(select *
              from roleAuths
              where role = 'ROLE_student')
    insert into roleAuths (role) values ('ROLE_student');

--插入teacher
if not exists(select *
              from roleAuths
              where role = 'ROLE_teacher')
    insert into roleAuths (role) values ('ROLE_teacher');
--插入admin
if not exists(select *
              from roleAuths
              where role = 'ROLE_admin')
    insert into roleAuths (role) values ('ROLE_admin');
--插入super
if not exists(select *
              from roleAuths
              where role = 'ROLE_super')
    insert into roleAuths (role) values ('ROLE_super');
--分别在roleAuths表中插入列，表示该role被授予的操作

-- --插入测试用账号
-- --插入beta_test
-- if not exists (select * from users where username = 'beta_test')
--     insert into users (id,username,password,role,registration_date) values (1,'beta_test','beta_test','ROLE_super',getdate());
