package com.neuporfolio.server.utils.formformat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ObjectForm {
    Object obj;

    public ObjectForm(Object o) {
        this.obj = o;
    }

    public ResponseEntity<Object> toResponseEntity() {
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
