
package ru.rellai.ecrf.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.rellai.ecrf.auth.annotation.AddMenu;
import ru.rellai.ecrf.auth.dto.RoleDto;
import ru.rellai.ecrf.auth.dto.UserDto;
import ru.rellai.ecrf.auth.service.RoleService;
import ru.rellai.ecrf.auth.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RoleService roleService;


    @AddMenu
    @GetMapping("/users")
    public String list(HttpServletRequest request, Model model, @Param("keyword") String keyword) {
        
        model.addAttribute("title", "Users");
        return "users";
    }

    @AddMenu
    @GetMapping("/users/{id}")
    public String edit(HttpServletRequest request,
                       Model model,
                       @PathVariable("id") Long id,
                       RedirectAttributes redirectAttributes) {
        try {
            UserDto user = userService.findById(id);

            List<Long> selectedRoles = new ArrayList<>();

            model.addAttribute("user", user);
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("title", "Edit User (ID: " + id + ")");
            if (!user.roles().isEmpty()) {
                selectedRoles = user.roles().stream().map(RoleDto::getId).collect(Collectors.toList());
            }
            model.addAttribute("selectedRoles", selectedRoles);



            return "user";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/users";
        }
    }


    @PostMapping("/users/save")
    public String save(UserDto user,
                       long[] selectedRoles,
                       RedirectAttributes redirectAttributes) {
        try {
            userService.saveUserWithPass(user, selectedRoles);

            redirectAttributes.addFlashAttribute("message", "The User has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/users";
    }
    
    @AddMenu
    @GetMapping("/users/add")
    public String add(HttpServletRequest request,Model model) {
        UserDto user = new UserDto(0, null, null, null, null);

        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("title", "Create new User");
        model.addAttribute("selectedRoles", new ArrayList<>());

        return "user";
    }
    
}
