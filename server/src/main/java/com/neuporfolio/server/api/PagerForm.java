package com.neuporfolio.server.api;

import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * {
 *     "list":[
 *         {
 *             "id":1,
 *             "class":19001,
 *             "major":"计算机科学与技术"
 *         }
 *     ],
 *     "page_info":{
 *         "current_page": 1,
 *         "current_size": 30,
 *         "has_next_page": false,
 *         "has_prev_page": false
 *     }
 *
 * }
 */
public class PagerForm extends ComForm {
    public PagerForm(int code, List<?> list, PageInfo<?> pageInfo) {
        super(code);
        super.put("list", list);
        super.put("page_info", new PageInfoFormat(pageInfo));
    }
}
