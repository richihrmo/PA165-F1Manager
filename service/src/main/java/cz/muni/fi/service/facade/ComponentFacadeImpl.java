package cz.muni.fi.service.facade;

import cz.muni.fi.dto.ComponentCreateDTO;
import cz.muni.fi.dto.ComponentDTO;
import cz.muni.fi.entities.Component;
import cz.muni.fi.facade.ComponentFacade;
import cz.muni.fi.service.BeanMappingService;
import cz.muni.fi.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Robert Tamas
 */
public class ComponentFacadeImpl implements ComponentFacade {

    @Autowired
    private ComponentService componentService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<ComponentDTO> listAllComponents() {

        return beanMappingService.mapTo(componentService.listAllComponents(), ComponentDTO.class);
    }

    @Override
    public List<ComponentDTO> listAllComponentsWithType(cz.muni.fi.enums.ComponentType type) {
        return beanMappingService.mapTo(componentService.listAllComponentsWithType(beanMappingService.mapTo(type, cz.muni.fi.persistanceEnums.ComponentType.class)), ComponentDTO.class);
    }

    @Override
    public List<ComponentDTO> listAllAvailableComponents() {
        return beanMappingService.mapTo(componentService.listAllAvailableComponents(), ComponentDTO.class);
    }

    @Override
    public List<ComponentDTO> listAllAvailableComponentsWithType(cz.muni.fi.enums.ComponentType type) {
        return beanMappingService.mapTo(componentService.listAllComponentsWithType(beanMappingService.mapTo(type, cz.muni.fi.persistanceEnums.ComponentType.class)), ComponentDTO.class);
    }

    @Override
    public ComponentDTO findComponentByID(Long id) {
        return beanMappingService.mapTo(componentService.findComponentByID(id), ComponentDTO.class);
    }

    @Override
    public ComponentDTO findComponentByName(String name) {
        return beanMappingService.mapTo(componentService.findComponentByName(name), ComponentDTO.class);
    }

    @Override
    public void createComponent(ComponentCreateDTO component) {
        componentService.createComponent(beanMappingService.mapTo(component, Component.class));
    }

    @Override
    public void updateComponent(ComponentDTO component) {
        componentService.updateComponent(beanMappingService.mapTo(component, Component.class));
    }

    @Override
    public void deleteComponent(ComponentDTO component) {
        componentService.deleteComponent(beanMappingService.mapTo(component, Component.class));
    }
}
