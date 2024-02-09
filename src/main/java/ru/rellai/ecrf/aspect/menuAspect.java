package ru.rellai.ecrf.aspect;


import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ru.rellai.ecrf.service.MenuService;

@Component
@Aspect
@RequiredArgsConstructor
public class menuAspect {

    private final MenuService menuService;

    @Before("@annotation(auiditable) && args(model,..) ")
    public void logAllMethodCallsAdvice(Model model, ru.rellai.ecrf.annotation.Menu auiditable) throws Throwable {
        var menu = menuService.findAllbyMenuId(1L).stream().peek(menuDto -> menuDto.setActive(menuDto.getUrl().equals(auiditable.value())));
        model.addAttribute("menu", menu);
    }

}
