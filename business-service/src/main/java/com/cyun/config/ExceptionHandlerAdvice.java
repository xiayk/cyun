package com.cyun.config;


import com.cyun.exception.BadRequestException;
import com.cyun.exception.TokenException;
import com.cyun.exception.TokenUndefinedException;
import com.cyun.utils.http.HttpRewriteStatus;
import com.cyun.utils.http.HttpUtil;
import com.cyun.utils.http.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

//import feign.FeignException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * 没带token
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({BadRequestException.class})
    @Order(1)
    public JSONResult<String> badRequestException(BadRequestException exception) {
        log.error(exception.getMessage(), exception);

        return HttpUtil.writeJSON("", exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }


    /**
     * token 无效
     * 401
     * @param exception
     * @return
     */
    @ExceptionHandler({TokenException.class})
    @Order(2)
    public JSONResult<String> tokenException(TokenException exception) {
        log.error(exception.getMessage(), exception);
        return HttpUtil.writeJSON("", exception.getMessage(), 401);
    }

    /**
     * 请求异常
     *
     * @param exception
     * @return
     */
/*    @ExceptionHandler({FeignException.class})
    @Order(3)
    public JSONResult<String> tokenException(FeignException exception) {
        log.error(exception.getMessage(), exception);
        return HttpUtil.writeJSON("", "请求超时异常", HttpStatus.REQUEST_TIMEOUT.value());
    }*/

    /**
     * 请求异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class})
    @Order(4)
    public JSONResult<String> IllegalArgumentException(IllegalArgumentException exception) {
        return HttpUtil.writeFailJSON("", exception.getMessage());
    }

    /**
     * token Undefined
     * 411
     * @param exception
     * @return
     */
    @ExceptionHandler({TokenUndefinedException.class})
    @Order(5)
    public JSONResult<String> tokenUndefinedException(TokenUndefinedException exception) {
        log.error(exception.getMessage(), exception);
        // token为undefined时抛出411的错误
        return HttpUtil.writeJSON("", exception.getMessage(), HttpStatus.LENGTH_REQUIRED.value());
    }

    @ExceptionHandler({Exception.class})
    @Order(100)
    public JSONResult<String> lastException(Exception exception, final WebRequest req) {
        log.error("", exception);
        return HttpUtil.writeFailJSON("", HttpRewriteStatus.LOSE_INFO);
    }
}
