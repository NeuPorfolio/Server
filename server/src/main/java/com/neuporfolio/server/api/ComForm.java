package com.neuporfolio.server.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/***
 * {
 *     "code":200,
 * }
 * code=200=HTTP.OK;
 * code=401=HTTP.UNAUTHORIZED;
 * else HTTP.FORBIDDEN;
 */
public class ComForm extends JSONObject {

    public ComForm(int code) {
        super.put("code", code);
    }

    public ResponseEntity<?> toResponseEntity() {
        if (200 == (int) super.get("code")) {
            return new ResponseEntity<JSONObject>(this, HttpStatus.OK);
        }
        if (401 == (int) super.get("code")) {
            return new ResponseEntity<JSONObject>(this, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<JSONObject>(this, HttpStatus.FORBIDDEN);
    }
}
