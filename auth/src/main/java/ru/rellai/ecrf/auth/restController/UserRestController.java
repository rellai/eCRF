
package ru.rellai.ecrf.auth.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.rellai.ecrf.auth.dto.UserDto;
import ru.rellai.ecrf.auth.service.MenuService;
import ru.rellai.ecrf.auth.service.UserService;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    private final MenuService menuService;

    @GetMapping("/api/v1/users/")
    public List<UserDto> getAllUsers () {
          return  userService.findAll();

    }

    @DeleteMapping("/api/v1/users/{id}")
    public void delete(@PathVariable("id") Long id) {
            userService.deleteById(id);
    }

    
}
