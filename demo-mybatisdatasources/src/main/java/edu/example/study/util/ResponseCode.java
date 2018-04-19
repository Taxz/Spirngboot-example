package edu.example.study.util;

/**
 * Created by Taxz on 2018/4/18.
 */
public enum ResponseCode {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);
    public int code;

    ResponseCode(int code) {
        this.code = code;
    }
}
