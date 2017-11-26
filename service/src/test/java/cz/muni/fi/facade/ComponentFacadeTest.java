package cz.muni.fi.facade;

import cz.muni.fi.dto.ComponentCreateDTO;
import cz.muni.fi.dto.ComponentDTO;
import cz.muni.fi.entities.Component;
import cz.muni.fi.enums.ComponentType;
import cz.muni.fi.service.BeanMappingService;
import cz.muni.fi.service.ComponentService;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.dozer.inject.Inject;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ComponentFacadeTest extends AbstractTestNGSpringContextTests {

    @Inject
    BeanMappingService mapper;
    @Inject
    ComponentFacade componentFacade;
    @Inject
    ComponentService componentService;

    public static ComponentCreateDTO createComponent(String name, ComponentType type, Boolean isAvailable) {
        ComponentCreateDTO component = new ComponentCreateDTO();
        component.setName(name);
        component.setType(type);
        component.setIsAvailable(isAvailable);
        return component;
    }

    @Test
    public void test() {
        Component component = new Component();
        component.setComponentType(cz.muni.fi.persistanceEnums.ComponentType.BRAKES);
        component.setName("brakes");
        component.setAvailable(true);
        ComponentDTO componentDTO = mapper.mapTo(component, ComponentDTO.class);
        componentDTO.setType(mapper.mapTo(component.getComponentType(), ComponentType.class));
        System.out.println(componentDTO.getType().getClass());
    }



}
