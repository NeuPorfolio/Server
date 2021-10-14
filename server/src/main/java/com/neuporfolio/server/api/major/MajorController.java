package com.neuporfolio.server.api.major;

import com.github.pagehelper.PageInfo;
import com.neuporfolio.server.api.ComFailureForm;
import com.neuporfolio.server.api.PagerForm;
import com.neuporfolio.server.domain.Major;
import com.neuporfolio.server.service.MajorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(path = "/api/major")
public class MajorController {
    /*
     {{baseurl}}/api/major?page=1&pagesize=30
     */
    @Resource
    MajorService majorService;

    @GetMapping
    public ResponseEntity<?> getMajorListByPage(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "pagesize", defaultValue = "30") Integer pagesize) {
        /*
          未登录返回401
                  {
              "detail":{
                  "msg":"未登录！"
              }
          }
         */
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return new ComFailureForm(401, "未登录").toResponseEntity();
        }
        /*
        page信息
         */
        List<Major> list = majorService.FindByPager(page, pagesize);
        PageInfo<Major> pageInfo = new PageInfo<>(list);
        return new PagerForm(200, list, pageInfo).toResponseEntity();
    }
}
