package com.neuporfolio.server.domain;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class CourseTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String courseName;
    @NotNull
    private String courseSubject;
    @NotNull
    private Long sponsor;
    private Long supId;
    @NotNull
    private Date datetime;

    private Date closed_date;
    private String type;
    private String html;
}
