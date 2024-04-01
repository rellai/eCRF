package ru.rellai.ecrf.auth.mapper;

import org.mapstruct.*;
import ru.rellai.ecrf.auth.dto.RoleDto;
import ru.rellai.ecrf.auth.entity.Role;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    Role toEntity(RoleDto roleDto);

    RoleDto toDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleDto roleDto, @MappingTarget Role role);
}