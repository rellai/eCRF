
package ru.rellai.ecrf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rellai.ecrf.dto.MenuDto;
import ru.rellai.ecrf.entity.Menu;
import ru.rellai.ecrf.service.MenuService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final MenuService menuService;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("title", "Welcome to eCRF");
        var menu = menuService.findAllbyMenuId(1L).stream().peek(menuDto -> menuDto.setActive(menuDto.getUrl().equals("/")));
        model.addAttribute("menu", menu);

        return "index";
    }

    // Login form
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Please login to eCRF");
        return "login";
    }

    // Login form with error
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("title", "Please login to eCRF");
        model.addAttribute("loginError", true);
        return "login";
    }
    
}
