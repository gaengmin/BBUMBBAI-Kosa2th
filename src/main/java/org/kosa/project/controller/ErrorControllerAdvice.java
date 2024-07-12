package org.kosa.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.kosa.project.service.exception.meeting.MeetingUserNotLoginException;
import org.kosa.project.service.exception.meeting.MeetingUserNotSufficientException;
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

    @ExceptionHandler(value = {MeetingUserNotSufficientException.class})
    public String redirectMeeting(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String requestURI = request.getRequestURI();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(name -> {
                    redirectAttributes.addAttribute(name, request.getParameter(name));
                });
        return "redirect:" + requestURI;
    }


    @ExceptionHandler(value = {MeetingUserNotLoginException.class})
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String handleException(Exception ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String requestURI = request.getRequestURI();
        redirectAttributes.addAttribute("redirect", requestURI);
        return "redirect:/login";
    }


}
