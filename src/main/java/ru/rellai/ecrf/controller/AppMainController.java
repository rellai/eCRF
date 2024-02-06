
package ru.rellai.ecrf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rellai.ecrf.dto.UserDto;
import ru.rellai.ecrf.service.UserService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class AppMainController {

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("title", "Welcome to eCRF");
        return "index";
    }
    
}
