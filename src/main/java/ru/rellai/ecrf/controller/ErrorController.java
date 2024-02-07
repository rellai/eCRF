package ru.rellai.ecrf.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rellai.ecrf.service.MenuService;

@Controller
@RequiredArgsConstructor
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private final MenuService menuService;

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        var menu = menuService.findAllbyMenuId(1L).stream().peek(menuDto -> menuDto.setActive(menuDto.getUrl().equals("/error")));
        model.addAttribute("menu", menu);
        model.addAttribute("title", "Error");
        model.addAttribute("error", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));
        model.addAttribute("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return "error";
    }

}
