package web;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import security.DataException;

@Controller
public class ErrorController {
    private static final Logger logger = Logger.getLogger(HomeController.class);

    String role;
    int id;
    String name;

    public void setUserDetails(HttpServletRequest request) throws DataException {
        try {
            role = (String) request.getSession(false).getAttribute("role");
            id = (Integer) request.getSession(false).getAttribute("id");
            name = (String) request.getSession(false).getAttribute("name");
        } catch (NullPointerException e) {
            throw new DataException("Login failed");
        }
    }

    @RequestMapping("/error")
    public String customError(HttpServletRequest request, HttpServletResponse response, Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        // String servletName = (String)
        // request.getAttribute("javax.servlet.error.servlet_name");
        String exceptionMessage = getExceptionMessage(throwable, statusCode);
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        if (requestUri == null) {
            requestUri = "Unknown";
        }

        String message = MessageFormat.format("{0} returned for {1} with message {2}", statusCode, requestUri,
                exceptionMessage);

        model.addAttribute("errorMessage", message);
        return "customError";
    }

    private String getExceptionMessage(Throwable throwable, Integer statusCode) {

        if (throwable != null) {
            return ExceptionUtils.getRootCause(throwable).getMessage();
        }

        HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
        return httpStatus.getReasonPhrase();

    }

}
