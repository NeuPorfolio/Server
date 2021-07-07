# Neuporfolio-server

## 目录

- API
    - login
    - course
    - subject

## 一、API

### 0.register

url: /api/register\
请求体:\
role==student

```
    private String username; //*
    private String password; //*
    private String role; //*用户身份，可为student或teacher
    private String sno; //*
    private String realName;
    private String majorClass;
    private String department;
    private String subDepartment;
    private String profile;
```

role==teacher

```
    private String username;//*
    private String password;//*
    private String role; //*用户身份，可为student或teacher
    private String realName;
    private String department;
    private String profile;
```

返回体:\
表单必要信息缺失: 400

````
{
    "msg": "表单信息缺失"
}
````

身份填写错误: 406

````
{
    "msg": "用户身份不可用."
}
````

用户已经存在: 406

````
{
    "msg": "用户已经存在."
}
````

### 1.login

url: /api/login \
默认跳转登录页: /login \

#### PostMapping 登录用户

请求体:

```
{
  String username, //*
  String password //*
}
```

响应体:\
失败:401

```
{
    "msg": "org.springframework.security.authentication.BadCredentialsException: Bad credentials",
    "status": 401
}
 ```

成功:200

 ```
{
    "msg": {
        "password": null, //密码不显示
        "username": "beta_test",
        "authorities": [
            {
                "authority": "ROLE_super" //用户身份
            }
        ],
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true,
        "enabled": true
    },
    "status": 200
}
```

### 2.course

url: /api/course

#### GetMapping 查找课程

UrlAargs:\
subject = [VALUE] //根据专业查找课程\
请求体:

```
{
}
```

响应体:\
成功:200

```
{
    "count": 1,
    "msg": null,
    "results": [
        {
            "name": "计算机科学与技术",
            "subject": "计算机",
            "sponsor": 0,
            "supCourseName": null,
            "supCourseSubject": null,
            "generationDate": "2021-07-06T16:00:00.000+00:00",
            "closedDate": null
        }
    ]
}
```

#### PostMapping 添加课程

请求体:

```
{
    private String name; //*课程名称
    private String subject;  //*隶属于的专业
    private String supCourseName; //隶属于的上级课程
    private String supCourseSubject; //上级课程的隶属的专业
    private Date closedDate;  //自动清空时间
}
```

响应体:\
成功: 200

```
{
    "msg": "添加课程成功",
    "courses": {
        "name": "计算机让我飞黄腾达01",
        "subject": "计算机",
        "sponsor": 0,
        "supCourseName": null,
        "supCourseSubject": null,
        "generationDate": "2021-07-07T06:31:30.510+00:00",
        "closedDate": null
    }
}
```

失败，表单信息不完全: 400

```
{
"msg": "失败，表单信息不完全",
"courses": null
}
```

失败，已经存在该课程: 400

```
{
"msg": "失败，已经存在该课程",
"courses": null
}
```

未验证的用户: 401

```
{
    "msg": "未验证的用户",
    "courses": null
}
```

### 3.subject

url: /api/subject

#### GetMapping 查找专业

请求体:

```
{
}
```

响应体:\
成功:200

```
{
    "count": 1,
    "results": [
        {
            "name": "计算机",
            "sponsor": 0,
            "generationDate": "2021-07-06T16:00:00.000+00:00"
        }
    ]
}

```