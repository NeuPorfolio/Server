package com.neuporfolio.server.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Subjects {
    @Id
    private String name;
    @NotNull
    private Long sponsor;
    @NotNull
    private Date generationDate;
}
