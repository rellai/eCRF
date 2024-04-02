package ru.rellai.ecrf.study.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.rellai.ecrf.study.dto.MenuDto;
import ru.rellai.ecrf.study.entity.Menu;

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