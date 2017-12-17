package cz.muni.fi.service.facade;

import cz.muni.fi.dto.ComponentDTO;
import cz.muni.fi.entities.Component;
import cz.muni.fi.persistanceEnums.ComponentType;
import cz.muni.fi.facade.ComponentFacade;
import cz.muni.fi.service.BeanMappingService;
import cz.muni.fi.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Robert Tamas
 */
@Service
@Transactional
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
        return beanMappingService.mapTo(componentService.listAllComponentsWithType(ComponentType.getDTOType(type)), ComponentDTO.class);
    }

    @Override
    public List<ComponentDTO> listAllAvailableComponents() {
        return beanMappingService.mapTo(componentService.listAllAvailableComponents(), ComponentDTO.class);
    }

    @Override
    public List<ComponentDTO> listAllAvailableComponentsWithType(cz.muni.fi.enums.ComponentType type) {
        return beanMappingService.mapTo(componentService.listAllAvailableComponentsWithType(ComponentType.getDTOType(type)), ComponentDTO.class);
    }

    @Override
    public ComponentDTO findComponentByID(Long id) {
        return beanMappingService.mapTo(componentService.findComponentByID(id), ComponentDTO.class);
    }

    @Override
    public ComponentDTO findComponentByName(String name) {
        Component component = componentService.findComponentByName(name);
        if (component == null) {
            return null;
        }
        return beanMappingService.mapTo(component, ComponentDTO.class);
    }

    @Override
    public ComponentDTO createComponent(ComponentDTO componentDTO) {
        Component component = beanMappingService.mapTo(componentDTO, Component.class);
        componentService.createComponent(component);
        componentDTO.setId(component.getId());
        return componentDTO;
    }

    @Override
    public ComponentDTO updateComponent(ComponentDTO component) {
        Component result;
        result = componentService.updateComponent(beanMappingService.mapTo(component, Component.class));
        result.setId(component.getId());
        return beanMappingService.mapTo(result, ComponentDTO.class);
    }

    @Override
    public void deleteComponent(ComponentDTO component) {
        componentService.deleteComponent(beanMappingService.mapTo(component, Component.class));
    }
}
