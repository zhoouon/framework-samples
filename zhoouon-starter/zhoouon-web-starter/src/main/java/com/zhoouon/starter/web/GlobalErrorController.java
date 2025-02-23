package com.zhoouon.starter.web;

import com.zhoouon.starter.common.result.BaseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;

/**
 * 处理SpringBoot默认异常,诸如默认的404异常
 */
@Slf4j
@RestController
@RequestMapping("/error")
public class GlobalErrorController extends AbstractErrorController {

    public GlobalErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @RequestMapping
    // @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseResult<Void> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        ErrorAttributeOptions of = ErrorAttributeOptions.of(ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.STACK_TRACE);

        Map<String, Object> body = getErrorAttributes(request, of);

        String message = String.format("%s: %s", body.get("message"), body.get("path"));

        return BaseResult.buildFail(String.valueOf(status), message);

    }

    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        ErrorAttributeOptions of = ErrorAttributeOptions.of(ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.EXCEPTION
        // ,ErrorAttributeOptions.Include.STACK_TRACE
        );
        Map<String, Object> model = Collections
                .unmodifiableMap(getErrorAttributes(request, of));
        response.setStatus(status.value());
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
    }
}
