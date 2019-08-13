package com.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class ErrorHandlingController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        Optional<Object> status = Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));

        if(status.isPresent()){
            Integer statusCode = Integer.valueOf(status.get().toString());

            HttpStatus[] codes = HttpStatus.values();

            boolean match = Arrays.stream(codes).map(HttpStatus::value).anyMatch(c -> c.equals(statusCode));

            if(match)
                request.setAttribute("errorMessage", "error." + statusCode);
        }

        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
