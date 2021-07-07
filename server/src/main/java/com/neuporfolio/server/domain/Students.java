package com.neuporfolio.server.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Students {
    @Id
    private Long userId;
    @NotNull
    private String sno;
    private String realName;
    private String majorClass;
    private String department;
    private String subDepartment;
    private String profile;

}
