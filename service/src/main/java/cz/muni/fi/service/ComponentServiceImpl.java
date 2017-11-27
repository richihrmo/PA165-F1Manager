package cz.muni.fi.service;

import cz.muni.fi.dao.ComponentDao;
import cz.muni.fi.entities.Component;
import cz.muni.fi.persistanceEnums.ComponentType;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComponentServiceImpl implements ComponentService {

    @Inject
    private ComponentDao componentDao;

    @Override
    public List<Component> listAllComponents() {
        return componentDao.listAllComponents();
    }

    @Override
    public List<Component> listAllComponentsWithType(ComponentType type) {
        if (type == null) throw new IllegalArgumentException("Type cannot be null");
        return componentDao.listAllComponents().stream().filter(p -> p.getComponentType() == type).collect(Collectors.toList());
    }

    @Override
    public List<Component> listAllAvailableComponents() {
        return componentDao.listAvailableComponents();
    }

    @Override
    public List<Component> listAllAvailableComponentsWithType(ComponentType type) {
        if (type == null) throw new IllegalArgumentException("Type cannot be null");
        return componentDao.listAvailableComponents().stream().filter(p -> p.getComponentType() == type).collect(Collectors.toList());
    }

    @Override
    public Component findComponentByID(Long id) {
        if (id == null) throw new IllegalArgumentException("Type cannot be null");
        return componentDao.findComponentById(id);
    }

    @Override
    public Component findComponentByName(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        return componentDao.findComponentByName(name);
    }

    @Override
    public void createComponent(Component component) {
        if (component == null) throw new IllegalArgumentException("Component cannot be null");
        componentDao.addComponent(component);
    }

    @Override
    public void updateComponent(Component component) {
        if (component == null) throw new IllegalArgumentException("Component cannot be null");
        componentDao.updateComponent(component);
    }

    @Override
    public void deleteComponent(Component component) {
        if (component == null) throw new IllegalArgumentException("Component cannot be null");
        componentDao.deleteComponent(component);
    }
}
