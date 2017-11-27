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
/*
    private static Map<ComponentType, cz.muni.fi.persistanceEnums.ComponentType> enumMap =
            new HashMap<ComponentType, cz.muni.fi.persistanceEnums.ComponentType>() {{
                for (int i = 0; i < ComponentType.values().length; i++) {
                    put(ComponentType.values()[i], cz.muni.fi.persistanceEnums.ComponentType.values()[i]);
                }
    }};*/

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
        return beanMappingService.mapTo(componentService.findComponentByName(name), ComponentDTO.class);
    }

    @Override
    public void createComponent(ComponentDTO componentDTO) {
        Component component = beanMappingService.mapTo(componentDTO, Component.class);
        componentService.createComponent(component);
        componentDTO.setId(component.getId());
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
