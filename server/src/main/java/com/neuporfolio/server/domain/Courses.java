package com.neuporfolio.server.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@IdClass(Courses.CoursesId.class)
public class Courses {
    @Id
    private String name;
    @Id
    private String subject;
    @NotNull
    private Long sponsor;
    private String supCourseName;
    private String supCourseSubject;
    @NotNull
    private Date generationDate;
    private Date closedDate;

    public static class CoursesId implements Serializable {
        public String name;
        public String subject;

        @Override
        public int hashCode() {
            return name.hashCode() + subject.hashCode();

        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }

            if (this == obj) {
                return true;
            }

            if (getClass() != obj.getClass()) {
                return false;
            }

            final CoursesId o = (CoursesId) obj;
            return o.name.equals(this.name) && o.subject.equals(this.subject);
        }
    }
}
