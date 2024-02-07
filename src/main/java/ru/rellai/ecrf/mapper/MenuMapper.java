package ru.rellai.ecrf.mapper;

import org.mapstruct.*;
import ru.rellai.ecrf.dto.MenuDto;
import ru.rellai.ecrf.entity.Menu;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuMapper {
    Menu toEntity(MenuDto menuDto);

    @AfterMapping
    default void linkChilds(@MappingTarget Menu menu) {
        menu.getChilds().forEach(child -> child.setMenu(menu));
    }

    MenuDto toDto(Menu menu);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Menu partialUpdate(MenuDto menuDto, @MappingTarget Menu menu);
}