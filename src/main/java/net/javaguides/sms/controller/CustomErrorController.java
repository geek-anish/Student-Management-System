package net.javaguides.sms.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;



@ControllerAdvice
@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String handleError(HttpServletRequest request) {
        // Get the status code from the request
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (statusCode == null) {
            // Handle other types of errors (e.g., exception-based handling)
            return "error/generic"; // Use a generic error template for unknown errors
        }

        return switch (statusCode) {

            case 404 -> "error/404"; // Return the 404.html template for 404 errors

            case 500 -> "error/500"; // Return the 500.html template for 500 errors

            // Add more cases for other specific error scenarios as needed

            default -> "error/generic"; // Use a generic error template for other status codes
        };
    }


}

