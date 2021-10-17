//package com.neuporfolio.server.api.login;
//
//
//import com.neuporfolio.server.utils.ConstValues;
//import com.neuporfolio.server.utils.formformat.ComForm;
//import com.neuporfolio.server.config.MyAuthenticationProvider;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//
///***
// * 处理登陆成功但没有完成注册的情况
// */
//
//@ControllerAdvice
//public class TempLoginExceptionCatch {
//    @Resource
//    HttpSession httpSession;
//
//    @ExceptionHandler(MyAuthenticationProvider.RegistrationNotFinishedException.class)
//    public ResponseEntity<?> notFinishedRegException(MyAuthenticationProvider.RegistrationNotFinishedException ex) {
//        /*
//        将用户密码暂时存入session以备下次自动登陆
//         */
//        httpSession.setAttribute(MyAuthenticationProvider.getTempLoginCredFlagParameter(), Boolean.TRUE);
//        httpSession.setAttribute(MyAuthenticationProvider.getTempLoginCredUsernameParameter(), ex.username);
//        httpSession.setAttribute(MyAuthenticationProvider.getTempLoginCredPasswordParameter(), ex.password);
//
//        return new TempLoginForm(ex.code, ex.identity).toResponseEntity();
//    }
//
//    /***
//     * {
//     *     "code":200,
//     *     "identity":"student",
//     *     "iscomplete":false //是否已经完成了注册（选择科目班级）
//     * }
//     */
//    private static class TempLoginForm extends ComForm {
//        protected TempLoginForm(int code, String role) {
//            super(code);
//            String identity = ConstValues.roleComplexToSimplyParameterMapper.get(role);
//            if (identity == null) {
//                identity = role;
//            }
//            super.put("identity", identity);
//            super.put("iscomplete", false);
//        }
//    }
//}
