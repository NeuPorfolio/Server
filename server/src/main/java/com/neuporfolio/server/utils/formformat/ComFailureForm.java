package com.neuporfolio.server.utils.formformat;

import java.util.HashMap;
import java.util.Map;

/***
 * {
 *     "code":2,
 *     "detail":{
 *         "msg":"邮箱不存在"
 *     }
 * }
 * HTTPSTATUS=HTTP.FORBIDDEN
 */

public class ComFailureForm extends ComForm {
    public ComFailureForm(int code, String msg) {
        super(code);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        super.put("detail", map);
    }
}
