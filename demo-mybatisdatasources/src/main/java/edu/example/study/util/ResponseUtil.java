package edu.example.study.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Taxz on 2018/4/18.
 */
public class ResponseUtil {
    private static final Logger log = LoggerFactory.getLogger(ResponseUtil.class);

    public static CommonResponse generateResponse(boolean status) {
        CommonResponse commonResponse = new CommonResponse();
        if (status) {
            commonResponse.setCode(ResponseCode.SUCCESS)
                    .setMessage(CommonConstant.DEFAULT_SUCCESS_MESSAGE);
        } else {
            commonResponse.setCode(ResponseCode.FAIL)
                    .setMessage(CommonConstant.DEFAULT_FAIL_MESSAGE);
        }
        return commonResponse;
    }

    public static CommonResponse generateResponse(String message, boolean status) {
        CommonResponse commonResponse = new CommonResponse();
        if (status) {
            commonResponse.setMessage(message)
                    .setCode(ResponseCode.SUCCESS);
        } else {
            commonResponse.setCode(ResponseCode.FAIL)
                    .setMessage(message);
        }
        return commonResponse;
    }

    public static CommonResponse generateResponse(Object data) {
        CommonResponse commonResponse = new CommonResponse();
        if (data != null) {
            commonResponse.setCode(ResponseCode.SUCCESS)
                    .setMessage(CommonConstant.DEFAULT_SUCCESS_MESSAGE)
                    .setData(data);
        }else {
            commonResponse.setCode(ResponseCode.SUCCESS)
                    .setMessage(CommonConstant.DEFAULT_SUCCESS_MESSAGE);
        }
        return commonResponse;
    }

    public static HttpServletResponse handleResponse(HttpServletResponse response, Object object) {
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSONUtil.toString(object));
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

}
