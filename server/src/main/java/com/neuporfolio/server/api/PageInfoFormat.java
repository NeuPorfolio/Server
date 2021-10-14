package com.neuporfolio.server.api;

import com.github.pagehelper.PageInfo;

/***
 *"current_page": 1,
 *"current_size": 30,
 *"has_next_page": false,
 *"has_prev_page": false
 */
public class PageInfoFormat {
    public int current_page = 1;
    public int current_size = 30;
    public boolean has_next_page = false;
    public boolean has_prev_page = false;

    public PageInfoFormat(PageInfo<?> pageInfo) {
        this.current_page = pageInfo.getPageNum();
        this.current_size = pageInfo.getPageSize();
        this.has_next_page = pageInfo.isHasNextPage();
        this.has_prev_page = pageInfo.isHasPreviousPage();
    }
}