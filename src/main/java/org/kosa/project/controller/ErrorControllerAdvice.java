package org.kosa.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.kosa.project.service.exception.meeting.MeetingUserNotLoginException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.IOException;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(value = {MeetingUserNotLoginException.class})
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String handleException(Exception ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String requestURI = request.getRequestURI();
        redirectAttributes.addAttribute("redirect", requestURI);
        return "redirect:/login";
    }



//    @ExceptionHandler(value = {NoResourceFoundException.class})
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handleNoResourceFoundException(Exception ex, Model model) {
//        model.addAttribute("error", ex.getMessage());
//        return "error";
//    }

}
