package com.neuporfolio.server.api.register;

import com.neuporfolio.server.domain.Students;
import com.neuporfolio.server.domain.Teachers;
import com.neuporfolio.server.domain.Users;
import com.neuporfolio.server.service.StudentsService;
import com.neuporfolio.server.service.TeachersService;
import com.neuporfolio.server.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping(path = "/api/register")
public class RegistrationController {

    @Resource
    private UsersService usersService;

    @Resource
    private StudentsService studentsService;

    @Resource
    private TeachersService teachersService;

    @PostMapping
    public ResponseEntity<RegistrationResponseForm> processRegistration(RegistrationRequestForm registrationRequestForm) {
        //身份填写错误
        if (!registrationRequestForm.isWhole()) {
            return new ResponseEntity<>(new RegistrationResponseForm("表单信息缺失"), HttpStatus.BAD_REQUEST);
        }
        if (!(registrationRequestForm.getRole().equals("student") || registrationRequestForm.getRole().equals("teacher"))) {
            return new ResponseEntity<>(new RegistrationResponseForm("用户身份不可用."), HttpStatus.NOT_ACCEPTABLE);
        }
        //用户已存在
        if (usersService.findByUserName(registrationRequestForm.getUsername()) != null) {
            return new ResponseEntity<>(new RegistrationResponseForm("用户" + usersService.findByUserName(registrationRequestForm.getUsername()).getUsername() + "已经存在."), HttpStatus.NOT_ACCEPTABLE);
        }

        //检查完整性
        if (registrationRequestForm.getRole().equals("student")) {
            if (registrationRequestForm.getSno() == null) {
                return new ResponseEntity<>(new RegistrationResponseForm("sno不能为空"), HttpStatus.BAD_REQUEST);
            }
        }

        //注册
        usersService.save(registrationRequestForm.toUser());
        Users users = usersService.findByUserName(registrationRequestForm.getUsername());
        //添加信息到教室和学生表
        if (registrationRequestForm.getRole().equals("student")) {
            Students students = registrationRequestForm.toStudents(users.getId());
            studentsService.add(students);
        } else if (registrationRequestForm.getRole().equals("teacher")) {
            Teachers teachers = registrationRequestForm.toTeachers(users.getId());
            teachersService.add(teachers);
        }

        return new ResponseEntity<>(new RegistrationResponseForm("注册成功"), HttpStatus.OK);
    }
}
