package ru.rellai.ecrf.aspect;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.rellai.ecrf.annotation.GetMenu;
import ru.rellai.ecrf.service.MenuService;

@Component
@Aspect
@RequiredArgsConstructor
public class MenuAspect {

    private final MenuService menuService;

    @Before("@annotation(auditable) && args(request, model,..) ")
    public void logAllMethodCallsAdvice(HttpServletRequest request, Model model, GetMenu auditable) throws Throwable {
        var menu = menuService.findAllbyMenuId(auditable.menuId()).stream().
                peek(menuDto -> menuDto.setActive(menuDto.getUrl().equals(request.getRequestURI())));
        model.addAttribute("menu", menu);
    }

}
