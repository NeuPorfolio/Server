package com.neuporfolio.server.utils.formformat;

import java.util.List;

public class ListForm extends ComForm {
    public ListForm(int code, List<?> list) {
        super(code);
        super.put("list", list);
    }
}
