package com.neuporfolio.server.api.register;


import com.neuporfolio.server.domain.User;
import com.neuporfolio.server.service.IdentityService;
import com.neuporfolio.server.service.UserService;
import com.neuporfolio.server.utils.formformat.ComForm;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/***
 * 注册
 */

@Slf4j
@RestController
@RequestMapping(path = "/api/register")
public class RegistrationController {

    @Resource
    private UserService userService;

    @Resource
    private IdentityService identityService;

    /***
     * 注册
     * @param registrationRequestForm 注册表单
     * @return msg
     */
    @PostMapping
    public ResponseEntity<?> processRegistration(@RequestBody RegistrationRequestForm registrationRequestForm) {
        //表单缺漏
        if (!registrationRequestForm.isWhole()) {
            return new RegistrationFailForm(-1, "表单内容不完善").toResponseEntity();
        }

        //注册
        String username = registrationRequestForm.getUsername();
        String password = registrationRequestForm.getPassword();
        String role = registrationRequestForm.getRole();
        User user;
        try {
            user = userService.buildUser(username, password, role);
        } catch (UserService.ExistedUserException e) {
            return new RegistrationFailForm(-3, "用户已存在").toResponseEntity();
        } catch (UserService.UnavailableRoleException e) {
            return new RegistrationFailForm(-2, "用户身份不可用").toResponseEntity();
        } catch (UserService.DaoFactException e) {
            e.printStackTrace();
            return new RegistrationFailForm(-666, "预料之外的错误,请联系开发人员").toResponseEntity();
        }

        return new RegistrationSucceedForm(200, identityService.getByWholeName(user.getRole()).getSimplyRole()).toResponseEntity();
    }

    public static class RegistrationFailForm extends ComForm {
        Map<String, String> detail;

        public RegistrationFailForm(int code, String msg) {
            super(code);
            this.detail = new HashMap<>();
            detail.put("msg", msg);
        }
    }

    @Data
    public static class RegistrationRequestForm {
        @NotNull
        private String email;
        @NotNull
        private String password;
        @NotNull
        private String role;

        public boolean isWhole() {
            return email != null && password != null && role != null;
        }

        public boolean isRoleAvailable() {
            return role.equals("学生") || role.equals("教师");
        }

        public String getUsername() {
            return email;
        }
    }

    /***
     * {
     *     "code":200,
     *     "identity":"student"
     * }
     */
    private static class RegistrationSucceedForm extends ComForm {
        public RegistrationSucceedForm(int i, String role) {
            super(i);
            super.put("identity", role);
        }
    }
}