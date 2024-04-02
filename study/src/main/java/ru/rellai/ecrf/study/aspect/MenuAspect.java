package ru.rellai.ecrf.study.aspect;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.rellai.ecrf.study.annotation.AddMenu;
import ru.rellai.ecrf.study.service.MenuService;

@Component
@Aspect
@RequiredArgsConstructor
public class MenuAspect {

    private final MenuService menuService;

    @Before("@annotation(auditable) && args(request, model,..) ")
    public void logAllMethodCallsAdvice(HttpServletRequest request, Model model, AddMenu auditable) throws Throwable {
        var menu = menuService.findAllbyMenuId(auditable.menuId()).stream().
                peek(menuDto -> {
                    if (request.getRequestURI().length() > 1 && menuDto.getUrl().length() > 1) {
                        menuDto.setActive(request.getRequestURI().startsWith(menuDto.getUrl().substring(1),1));
                    } else {
                        menuDto.setActive(menuDto.getUrl().equals(request.getRequestURI()));
                    }
                });
        model.addAttribute("menu", menu);
    }

}
