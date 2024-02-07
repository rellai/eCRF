
package ru.rellai.ecrf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rellai.ecrf.dto.UserDto;
import ru.rellai.ecrf.service.MenuService;
import ru.rellai.ecrf.service.UserService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final MenuService menuService;

    @GetMapping("/users/")
    public String list(Model model) {
        List<UserDto> users = userService.findAll();
        var menu = menuService.findAllbyMenuId(1L).stream().peek(menuDto -> menuDto.setActive(menuDto.getUrl().equals("/users/")));
        model.addAttribute("menu", menu);
        model.addAttribute("users", users);
        model.addAttribute("title", "Users");
        return "index";
    }
    
}
