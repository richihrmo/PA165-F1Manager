package cz.muni.fi.service.facade;

import cz.muni.fi.dto.ComponentCreateDTO;
import cz.muni.fi.dto.ComponentDTO;
import cz.muni.fi.enums.ComponentType;
import cz.muni.fi.facade.ComponentFacade;

import java.util.List;

/**
 * @author Robert Tamas
 */
public class ComponentFacadeImpl implements ComponentFacade {

    @Override
    public List<ComponentDTO> listAllComponents() {
        return null;
    }

    @Override
    public List<ComponentDTO> listAllComponentsWithType(ComponentType type) {
        return null;
    }

    @Override
    public List<ComponentDTO> listAllAvailableComponents() {
        return null;
    }

    @Override
    public List<ComponentDTO> listAllAvailableComponentsWithType() {
        return null;
    }

    @Override
    public ComponentDTO findComponentByID(Long id) {
        return null;
    }

    @Override
    public ComponentDTO findComponentByName(String name) {
        return null;
    }

    @Override
    public void createComponent(ComponentCreateDTO component) {

    }

    @Override
    public void updateComponent(ComponentDTO component) {

    }

    @Override
    public void deleteComponent(ComponentDTO component) {

    }
}
