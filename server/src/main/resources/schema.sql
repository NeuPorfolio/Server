--创建存放角色权限表的roleAuthsT表
if
not exists (select * from sysobjects where id = object_id('roleAuthsT')
and OBJECTPROPERTY(id, 'IsUserTable') = 1)
create table roleAuths
(
    role char(10) primary key--角色
);
go

--分别在roleAuths表中插入角色
--插入student
if
not exists (select * from roleAuths where role = 'student')
insert into roleAuths (role) values ('student');
go

--插入teacher
if not exists (select * from roleAuths where role = 'teacher')
insert into roleAuths (role) values ('teacher');
go

--插入admin
if not exists (select * from roleAuths where role = 'admin')
insert into roleAuths (role) values ('admin');
go

--插入super
if not exists (select * from roleAuths where role = 'super')
insert into roleAuths (role) values ('super');
go
--分别在roleAuths表中插入列，表示该role被授予的操作


--创建存放账号信息的users表
if not exists (select * from sysobjects where id = object_id('users')
and OBJECTPROPERTY(id, 'IsUserTable') = 1)
create table users
(
    id                int primary key,      --user_id
    username          varchar(40) not null,
    password          char(256)   not null, --hash
    role              char(10) references roleAuths (role),
    enabled           bit default 1,        --是否启用
    registration_date datetime    not null, -- 注册日期
    last_login_date   datetime,             -- 最后访问
    email             varchar(256) check (email like '%@%.%'),
    phone             varchar(25)
);
go

--插入测试用账号
--插入beta_test
if not exists (select * from users where username = 'beta_test')
insert into users (id,username,password,role,registration_date) values (1,'beta_test','beta_test','super',getdate());
go

--创建存放学生信息的student表,只有role为student在内
if not exists (select * from sysobjects where id = object_id('students')
and OBJECTPROPERTY(id, 'IsUserTable') = 1)
create table students
(
    user_id       int primary key references users (id),
    sno           char(20),
    realname      nchar(20),
    major_class   nchar(20),--行政班级
    department    nchar(15), --院系
    subdepartment nchar(15), --次级院系
    profile       nvarchar(2048), --个人简介
);
go

--创建存放教师信息的teacher表,只有role为teacher在内
if not exists (select * from sysobjects where id = object_id('teachers')
and OBJECTPROPERTY(id, 'IsUserTable') = 1)
create table teachers
(
    user_id    int primary key references users (id),
    realname   nchar(20) not null,
    department nchar(15),     -- 院系
    profile    nvarchar(2048) -- 个人简介
);
go

--创建课程表
if not exists (select * from sysobjects where id = object_id('courses')
and OBJECTPROPERTY(id, 'IsUserTable') = 1)
create table courses
(
    id              int primary key,
    name            varchar(40) not null,
    sponsor         int references users (id),   --发起者
    sup_id          int references courses (id), --上级课程
    generation_date datetime    not null,        --创建时间
    closed_date     datetime                     --关闭时间
);
go


--创建students选课表
if not exists (select * from sysobjects where id = object_id('stuToCourse')
and OBJECTPROPERTY(id, 'IsUserTable') = 1)
create table stuToCourse
(
    student_id int references students (user_id),
    course_id  int references courses (id),
    primary key (student_id, course_id)
);
go

--创建teachers选课表
if not exists (select * from sysobjects where id = object_id('teaToCourse')
and OBJECTPROPERTY(id, 'IsUserTable') = 1)
create table teaToCourse
(
    teacher_id int references teachers (user_id),
    course_id  int references courses (id),
    primary key (teacher_id, course_id)
);
go

--创建作业表
if not exists (select * from sysobjects where id = object_id('courseTasks')
and OBJECTPROPERTY(id, 'IsUserTable') = 1)
create table courseTasks
(
    id              int primary key,
    course_id       int references courses (id),--课程id
    sponsor         int references users (id),--发起者
    sup_id          int references courseTasks (id),--上级作业
    generation_date datetime not null,--创建时间
    closed_date     datetime,--关闭时间
    type            varchar(20),--作业类型
    html            ntext --详细内容
);
go

--创建资源表
if not exists (select * from sysobjects where id = object_id('courseResources')
and OBJECTPROPERTY(id, 'IsUserTable') = 1)
create table courseResources
(
    id              int primary key,
    course_id       int references courses (id),
    sponsor         int references users (id), --发起者
    link_to         varchar(256),              --链接到
    url             varchar(256),              --资源地址
    generation_date datetime not null,
    closed_date     datetime,
    type            varchar(20),--资源类型
    note            ntext
);
go