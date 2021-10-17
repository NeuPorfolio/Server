package com.neuporfolio.server.api.complete;

import com.neuporfolio.server.config.MyAuthenticationManager;
import com.neuporfolio.server.config.MyAuthenticationProvider;
import com.neuporfolio.server.domain.Student;
import com.neuporfolio.server.domain.Teacher;
import com.neuporfolio.server.domain.User;
import com.neuporfolio.server.service.StudentService;
import com.neuporfolio.server.service.TeacherService;
import com.neuporfolio.server.service.UserService;
import com.neuporfolio.server.utils.ConstValues;
import com.neuporfolio.server.utils.formformat.ComFailureForm;
import com.neuporfolio.server.utils.formformat.ComForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/api/complete")
public class CompleteController {

    @Resource
    UserService userService;

    @Resource
    StudentService studentService;

    @Resource
    TeacherService teacherService;

    @Resource
    MyAuthenticationManager myAuthenticationManager;

    /**
     * 登陆第二步-完成注册
     */
    @PostMapping
    public ResponseEntity<?> registerComplete(HttpServletRequest request, @RequestBody Map<String, Object> json, HttpSession httpSession) {
        /*
         * 验证session是否有登录信息
         */
        CompleteForm completeForm = new CompleteForm(json);
        Boolean flag = (Boolean) httpSession.getAttribute(MyAuthenticationProvider.getTempLoginCredFlagParameter());
        if (!(flag != null && flag.equals(Boolean.TRUE))) {
            return new ComFailureForm(403, "Forbidden").toResponseEntity();
        }

        String username = (String) httpSession.getAttribute(MyAuthenticationProvider.getTempLoginCredUsernameParameter());
        String password = (String) httpSession.getAttribute(MyAuthenticationProvider.getTempLoginCredPasswordParameter());

        User user = (User) userService.loadUserByUsername(username);

        /*
         * 表操作
         */
        if (user.getRole().equals(ConstValues.roleStudentParameter)) {
            Student student = new Student();
            student.setUid(user.getUid());
            student.setMajorId(completeForm.major);
            student.setRealName(completeForm.nick);
            student.setSno(completeForm.studentnumber);
            student.setMajorClass(String.valueOf(completeForm.classroom));
            studentService.buildStudent(student);

            this.fastLogin(request, username, password);
            /*
            自动登录
             */
            return new ResponseForm2(200, "student").toResponseEntity();
        } else if (user.getRole().equals(ConstValues.roleTeacherParameter)) {
            Teacher teacher = new Teacher();
            teacher.setUid(user.getUid());
            teacher.setMajorId(completeForm.major);
            teacher.setRealName(completeForm.nick);
            teacherService.buildTeacher(teacher);

            /*
            自动登录
             */
            this.fastLogin(request, username, password);

            return new ResponseForm2(200, "teacher").toResponseEntity();
        }
        return new ComFailureForm(-2, "未知的身份类型").toResponseEntity();
    }

    private void fastLogin(HttpServletRequest request, String username, String password) {
        /*
        这里详细讲解了security上下文相关
        https://blog.csdn.net/nero_claudius/article/details/87992354?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link
         */
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        Authentication authentication = myAuthenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /***
     * {
     *     "code":200,
     *     "identity":"teacher"
     * }
     */
    public static class ResponseForm2 extends ComForm {
        public ResponseForm2(int code, String role) {
            super(code);
            super.put("identity", role);
        }
    }

    private static class CompleteForm {
        String nick;
        String studentnumber;
        Integer major;
        /*
        Param("class")
        int class;
         */
        Integer classroom;

        CompleteForm(Map<String, Object> json) {
            this.nick = (String) json.get("nick");
            this.studentnumber = (String) json.get("studentnumber");
            this.major = (Integer) json.get("major");
            this.classroom = (Integer) json.get("class");
        }

    }
}
