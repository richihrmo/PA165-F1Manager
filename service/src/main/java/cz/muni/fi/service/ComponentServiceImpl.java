package cz.muni.fi.service;

import cz.muni.fi.dao.ComponentDao;
import cz.muni.fi.entities.Component;
import cz.muni.fi.persistanceEnums.ComponentType;
import cz.muni.fi.service.exception.ServiceDataAccessException;
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
        try {
            return componentDao.listAllComponents();
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot list all components", e);
        }
    }

    @Override
    public List<Component> listAllComponentsWithType(ComponentType type) {
        if (type == null) throw new IllegalArgumentException("Type cannot be null");
        try {
            return componentDao.listAllComponents().stream().filter(p -> p.getComponentType() == type).collect(Collectors.toList());
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot list all components with type :" + type, e);
        }
    }

    @Override
    public List<Component> listAllAvailableComponents() {
        try {
            return componentDao.listAvailableComponents();
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot list all available components", e);
        }
    }

    @Override
    public List<Component> listAllAvailableComponentsWithType(ComponentType type) {
        if (type == null) throw new IllegalArgumentException("Type cannot be null");
        try {
            return componentDao.listAvailableComponents().stream().filter(p -> p.getComponentType() == type).collect(Collectors.toList());
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot list all available components with type :" + type, e);
        }
    }

    @Override
    public Component findComponentByID(Long id) {
        if (id == null) throw new IllegalArgumentException("Type cannot be null");
        try {
            return componentDao.findComponentById(id);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot find component with id :" + id, e);
        }
    }

    @Override
    public Component findComponentByName(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        try {
            return componentDao.findComponentByName(name);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Connot find component with name :" + name, e);
        }
    }

    @Override
    public void createComponent(Component component) {
        if (component == null) throw new IllegalArgumentException("Component cannot be null");
        try {
            componentDao.addComponent(component);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot create component :" + component.toString(), e);
        }
    }

    @Override
    public void updateComponent(Component component) {
        if (component == null) throw new IllegalArgumentException("Component cannot be null");
        try {
            componentDao.updateComponent(component);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot update component :" + component.toString(), e);
        }
    }

    @Override
    public void deleteComponent(Component component) {
        if (component == null) throw new IllegalArgumentException("Component cannot be null");
        try {
            componentDao.deleteComponent(component);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot delete component :" + component.toString(), e);
        }
    }
}
