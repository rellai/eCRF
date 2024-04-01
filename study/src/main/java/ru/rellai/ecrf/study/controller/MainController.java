
package ru.rellai.ecrf.study.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.rellai.ecrf.study.annotation.AddMenu;


@Controller
@RequiredArgsConstructor
public class MainController {



    @AddMenu
    @GetMapping("/")
    public String list(HttpServletRequest request, Model model) {
        model.addAttribute("title", "Welcome to eCRF");
        model.addAttribute("requestURI", "/login");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Please login to eCRF");
        return "login";
    }




    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Используйте SecurityContextHolder для очистки контекста безопасности
        SecurityContextHolder.clearContext();

        // Удаление сессии
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Очистка кук
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        String logoutUrl = "http://localhost:9000/logout?redirect_uri=http://localhost:8080/login";

        return "redirect:" + logoutUrl;
    }
}
