
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
import ru.rellai.ecrf.auth.dto.UserDto;
import ru.rellai.ecrf.auth.service.MenuService;
import ru.rellai.ecrf.auth.service.UserService;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final MenuService menuService;

    @AddMenu
    @GetMapping("/users")
    public String list(HttpServletRequest request, Model model, @Param("keyword") String keyword) {
        
    /*    List<UserDto> users = new ArrayList<UserDto>();

        if (keyword == null) {
            userService.findAll().forEach(users::add);
          } else {
            userService.findAllByUsername(keyword).forEach(users::add);
            model.addAttribute("keyword", keyword);
          }
       
        
        model.addAttribute("users", users);

     */
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

            model.addAttribute("user", user);
            model.addAttribute("title", "Edit User (ID: " + id + ")");

            return "user";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/users";
        }
    }


    @PostMapping("/users/save")
    public String save(UserDto user, RedirectAttributes redirectAttributes) {
        try {
            userService.save(user);

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
        model.addAttribute("title", "Create new User");

        return "user";
    }
    
}
