package ru.rellai.ecrf.auth.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rellai.ecrf.auth.annotation.AddMenu;
import ru.rellai.ecrf.auth.service.MenuService;

@Controller
@RequiredArgsConstructor
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @AddMenu
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        model.addAttribute("title", "Error");
        model.addAttribute("error", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        model.addAttribute("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return "error";

    }

}
