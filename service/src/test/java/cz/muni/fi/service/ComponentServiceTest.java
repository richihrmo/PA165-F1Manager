package cz.muni.fi.service;

import cz.muni.fi.dao.ComponentDao;
import cz.muni.fi.entities.Component;
import cz.muni.fi.persistanceEnums.ComponentType;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Robert Tamas
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ComponentServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ComponentDao componentDao;

    @Autowired
    @InjectMocks
    private ComponentService componentService = new ComponentServiceImpl();

    private List<Component> allComponents;
    private List<Component> availableComponents;

    private Long counter = 20L;
    private Map<Long, Component> mockedComponents = new HashMap<>();

    @BeforeClass
    public void beforeClass() {

        MockitoAnnotations.initMocks(this);

        when(componentDao.addComponent(any(Component.class))).then(invoke -> {

            Component mockedComponent = invoke.getArgumentAt(0, Component.class);
            if (mockedComponent.getId() != null) throw new IllegalArgumentException("Component already exists");
            if (mockedComponent.getName() == null) throw new IllegalArgumentException("Component name cannot be null");
            if (mockedComponent.getComponentType() == null) throw new IllegalArgumentException("Component type cannot be null");

            long index = counter;
            mockedComponent.setId(index);
            mockedComponents.put(index, mockedComponent);
            counter++;
            return mockedComponent;
        });

        when(componentDao.updateComponent(any(Component.class))).then(invoke -> {

            Component mockedComponent = invoke.getArgumentAt(0, Component.class);
            if (mockedComponent.getId() == null) throw new IllegalArgumentException("Component ID cannot be null");
            if (mockedComponent.getName() == null) throw new IllegalArgumentException("Component name cannot be null");
            if (mockedComponent.getComponentType() == null) throw new IllegalArgumentException("Component type cannot be null");

            mockedComponents.replace(mockedComponent.getId(), mockedComponent);
            return mockedComponent;
        });

        when(componentDao.deleteComponent(any(Component.class))).then(invoke -> {

            Component mockedComponent = invoke.getArgumentAt(0, Component.class);
            if (mockedComponent.getId() == null) throw new IllegalArgumentException("Component is not stored");

            mockedComponents.remove(mockedComponent.getId(), mockedComponent);
            return mockedComponent;
        });

        when(componentDao.findComponentById(anyLong())).then(invoke -> {

            Long index = invoke.getArgumentAt(0, Long.class);
            if (index == null) throw new IllegalArgumentException("Cannot search with null ID");

            return mockedComponents.get(index);
        });

        when(componentDao.findComponentByName(anyString())).then(invoke -> {

            String name = invoke.getArgumentAt(0, String.class);
            if (name == null) throw new IllegalArgumentException("Cannont search with null name");
            return mockedComponents.values().stream().filter(p -> p.getName().equals(name)).findFirst();
        });

        when(componentDao.listAvailableComponents()).then(invoke ->
                mockedComponents.values().stream().filter(p -> p.isAvailability()).collect(Collectors.toList()));

        when(componentDao.listAllComponents()).then(invoke ->
                Collections.unmodifiableList(new ArrayList<>(mockedComponents.values())));
    }

    @BeforeMethod
    public void beforeMethod() {

        mockedComponents.clear();

        availableComponents = new ArrayList<Component>() {{
            add(new Component("Really fast engine", true, ComponentType.ENGINE));
            add(new Component("V8 twin turbo", true, ComponentType.ENGINE));
            add(new Component("Super effective brakes", true, ComponentType.BRAKES));
            add(new Component("Adaptive suspension", true, ComponentType.SUSPENSION));
            add(new Component("Non adaptive suspension", true, ComponentType.SUSPENSION));
            add(new Component("Five gear manual transmission", true, ComponentType.TRANSMISSION));
            add(new Component("Automatic transmission", true, ComponentType.TRANSMISSION));
            add(new Component("Double clutch transmission", true, ComponentType.TRANSMISSION));
            add(new Component("Active rear aerodynamics", true, ComponentType.AERODYNAMICS));
            add(new Component("Front splitter", true, ComponentType.AERODYNAMICS));
            add(new Component("Adaptive body", true, ComponentType.AERODYNAMICS));
        }};

        allComponents = new ArrayList<Component>() {{
            add(new Component("Slower than snail engine", false, ComponentType.ENGINE));
            add(new Component("Faster than light engine", false, ComponentType.ENGINE));
            add(new Component("Rusty brakes", false, ComponentType.BRAKES));
            add(new Component("Cute little smart suspension", false, ComponentType.SUSPENSION));
            add(new Component("Hard core suspension with metal fragments", false, ComponentType.SUSPENSION));
            add(new Component("Six gear manual transmission", false, ComponentType.TRANSMISSION));
            add(new Component("Semi manual transmission", false, ComponentType.AERODYNAMICS));
        }};

        allComponents.addAll(availableComponents);

        Long idCounter = 0L;
        for (Component component : allComponents) {
            component.setId(idCounter);
            mockedComponents.put(idCounter, component);
            idCounter++;
        }
    }

    @Test
    public void createComponent() throws DataAccessException {
        Component component = new Component("New component", true, ComponentType.ENGINE);

        int sizeBefore = mockedComponents.size();
        componentService.createComponent(component);

        assertThat(mockedComponents).hasSize(sizeBefore + 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullComponent() {
        componentService.createComponent(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createComponentNullName() {
        componentService.createComponent(new Component());
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createComponentNullType() {
        Component component = new Component();
        component.setName("Null type");
        componentService.createComponent(component);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void createExistingComponent() {
        componentService.createComponent(allComponents.get(0));
    }

    @Test
    public void updateComponent() throws DataAccessException {
        Component component = allComponents.get(0);
        component.setName("Updated name for");

        componentService.updateComponent(component);

        assertThat(mockedComponents.get(component.getId())).isEqualToComparingFieldByField(component);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void updateNullComponent() {
        componentService.updateComponent(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateComponentNullName() {
        componentService.updateComponent(new Component());
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateComponentNullType() {
        Component component = new Component();
        component.setName("Null type");
        componentService.updateComponent(component);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void updateComponentNullID() {
        Component component = new Component();
        component.setName("Null type");
        component.setComponentType(ComponentType.ENGINE);
        componentService.updateComponent(component);
    }

    @Test
    public void deleteComponent() throws DataAccessException {
        Component component = allComponents.get(0);

        int beforeSize = mockedComponents.size();
        componentService.deleteComponent(component);

        assertThat(mockedComponents).hasSize(beforeSize - 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullComponent() {
        componentService.deleteComponent(null);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void deleteComponentWithNullId() {
        componentService.deleteComponent(new Component());
    }

    @Test
    public void deleteComponentWhichIsNotInDB() throws DataAccessException {
        Component component = new Component("Lets summon some non-mocked component", true, ComponentType.TRANSMISSION);
        component.setId(666L);

        int beforeSize = mockedComponents.size();
        componentService.deleteComponent(component);

        assertThat(mockedComponents).hasSize(beforeSize);
    }

    @Test
    public void listAllComponents() {
        assertThat(componentService.listAllComponents()).containsExactlyElementsOf(allComponents);
    }

    @Test
    public void listAllAvailableComponents() {
        assertThat(componentService.listAllAvailableComponents()).containsExactlyElementsOf(availableComponents);
    }

    @Test
    public void listAllComponentsWithType() {
        for (ComponentType type : EnumSet.allOf(ComponentType.class)) {
            assertThat(componentService.listAllComponentsWithType(type))
                    .containsExactlyElementsOf(allComponents.stream().filter(p -> p.getComponentType() == type).collect(Collectors.toList()));
        }
    }

    @Test
    public void listAllAvailableComponentsWithType() {
        for (ComponentType type : EnumSet.allOf(ComponentType.class)) {
            assertThat(componentService.listAllAvailableComponentsWithType(type))
                    .containsExactlyElementsOf(availableComponents.stream().filter(p -> p.getComponentType() == type).collect(Collectors.toList()));
        }
    }
}
