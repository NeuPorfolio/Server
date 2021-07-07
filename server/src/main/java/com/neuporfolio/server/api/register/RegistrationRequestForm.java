package com.neuporfolio.server.api.register;

import com.neuporfolio.server.domain.Students;
import com.neuporfolio.server.domain.Teachers;
import com.neuporfolio.server.domain.Users;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class RegistrationRequestForm {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String role;
    private String sno;
    private String realName;
    private String majorClass;
    private String department;
    private String subDepartment;
    private String profile;

    public Students toStudents(Long id) {
        return new Students(id, sno, realName, majorClass, department, subDepartment, profile);
    }

    public Teachers toTeachers(Long id) {
        return new Teachers(id, realName, department, profile);
    }

    public Users toUser() {
        return new Users(username, password, "ROLE_" + role);
    }

    public boolean isWhole() {
        if (username == null) return false;
        if (password == null) return false;
        return role != null;
    }
}
