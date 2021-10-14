package com.neuporfolio.server.api;

import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * {
 *     "code":200,
 *     "class":{
 *         "list":[
 *             {
 *                 "id":1,
 *                 "name":"xxx"
 *             }
 *         ],
 *         "page_info":{
 *             "current_page": 1,
 *             "current_size": 30,
 *             "has_next_page": false,
 *             "has_prev_page": false
 *         }
 *     }
 * }
 */

public class ObjectPagerForm extends ComForm {
    public ObjectPagerForm(int code, String objectName, List<?> list, PageInfo<?> pageInfo) {
        super(code);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("page_info", new PageInfoFormat(pageInfo));
        super.put(objectName, map);
    }
}
