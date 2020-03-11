package com.code.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request, Model model){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        /**if(status != null){
            int statusCode = Integer.parseInt(status.toString());

           if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "403";.

            }

        }**/
        model.addAttribute("statusCode", status.toString());
        model.addAttribute("statusPage", status + " Error Page");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}
