
package ru.rellai.ecrf.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rellai.ecrf.annotation.GetMenu;
import ru.rellai.ecrf.dto.UserDto;
import ru.rellai.ecrf.service.MenuService;
import ru.rellai.ecrf.service.UserService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final MenuService menuService;

    @GetMenu
    @GetMapping("/users")
    public String list(HttpServletRequest request, Model model) {
        List<UserDto> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("title", "Users");
        return "index";
    }
    
}
