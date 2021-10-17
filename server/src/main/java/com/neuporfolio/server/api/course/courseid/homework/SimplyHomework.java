package com.neuporfolio.server.api.course.courseid.homework;

import com.neuporfolio.server.domain.Homework;

import java.util.Date;

/***
 *              "id":1,
 *             "classname":"19001",
 *             "title":"wang",
 *             "time":"balabala"
 */
public class SimplyHomework {
    Integer id;
    String classname;
    String title;
    Date time;

    public SimplyHomework(Homework i) {
        this.id = i.getHomewordId();
        this.classname = i.getClassName();
        this.title = i.getTitle();
        this.time = i.getTime();
    }
}
