
package ru.rellai.ecrf.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rellai.ecrf.auth.annotation.AddMenu;
import ru.rellai.ecrf.auth.service.MenuService;


@Controller
@RequiredArgsConstructor
public class MainController {

    @AddMenu
    @GetMapping("/")
    public String list(HttpServletRequest request, Model model) {
        model.addAttribute("title", "Welcome to eCRF");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Please login to eCRF");
        return "login";
    }
}
