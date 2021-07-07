package com.neuporfolio.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Teachers {
    @Id
    private Long userId;
    private String realName;
    private String department;
    private String profile;
}