package com.neuporfolio.server.api.subject;

import com.neuporfolio.server.domain.Subjects;
import lombok.Data;

import java.util.List;

@Data
public class SubjectResponseForm {
    private Long count;
    private List<Subjects> results;

    public SubjectResponseForm(List<Subjects> list) {
        this.count = Long.valueOf(list.size());
        this.results = list;
    }

}
