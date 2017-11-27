package cz.muni.fi.service.config;

import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.ComponentDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.dto.TeamDTO;
import cz.muni.fi.entities.Car;
import cz.muni.fi.entities.Component;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.entities.Team;
import cz.muni.fi.enums.ComponentType;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

public class EntityMapping extends BeanMappingBuilder {
    @Override
    protected void configure() {
        mapping(Component.class, ComponentDTO.class, TypeMappingOptions.mapNull(false))
                .fields(field("componentType").accessible(true), field("type").accessible(true));
        mapping(Driver.class, DriverDTO.class, TypeMappingOptions.mapNull(false))
                .fields(field("specialSkill").accessible(true), field("specialSkill").accessible(true));
        mapping(Team.class, TeamDTO.class, TypeMappingOptions.mapNull(false))
                .fields(field("carOne").accessible(true), field("carOne").accessible(true))
                .fields(field("carTwo").accessible(true), field("carTwo").accessible(true));
        mapping(Car.class, CarDTO.class, TypeMappingOptions.mapNull(false))
                .fields(field("driver").accessible(true), field("driver").accessible(true))
                .fields(field("engine").accessible(true), field("engine").accessible(true))
                .fields(field("aerodynamics").accessible(true), field("aerodynamics").accessible(true))
                .fields(field("suspension").accessible(true), field("suspension").accessible(true))
                .fields(field("transmission").accessible(true), field("transmission").accessible(true))
                .fields(field("brakes").accessible(true), field("brakes").accessible(true));
    }
}