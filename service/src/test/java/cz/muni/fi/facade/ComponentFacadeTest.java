package cz.muni.fi.facade;

import cz.muni.fi.dto.ComponentDTO;
import cz.muni.fi.enums.ComponentType;
import cz.muni.fi.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Robert Tamas
 */
@DirtiesContext
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ComponentFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ComponentFacade componentFacade;

    private List<ComponentDTO> allComponents;
    private List<ComponentDTO> availableComponents;

    @BeforeMethod
    public void beforeMethod() {

        availableComponents = new ArrayList<ComponentDTO>() {{
            add(new ComponentDTO("Available brakes", true, ComponentType.BRAKES));
            add(new ComponentDTO("Available suspension", true, ComponentType.SUSPENSION));
            add(new ComponentDTO("Available aerodynamics", true, ComponentType.AERODYNAMICS));
            add(new ComponentDTO("Available transmission", true, ComponentType.TRANSMISSION));
        }};

        allComponents = new ArrayList<ComponentDTO>() {{
            add(new ComponentDTO("Not available brakes", false, ComponentType.BRAKES));
            add(new ComponentDTO("Not available engine", false, ComponentType.ENGINE));
            add(new ComponentDTO("Not available suspension", false, ComponentType.SUSPENSION));
        }};

        allComponents.addAll(availableComponents);
        for (ComponentDTO component : allComponents) {
            componentFacade.createComponent(component);
            assertThat(componentFacade.findComponentByID(component.getId())).isEqualTo(component);
        }
    }

    @AfterMethod
    public void afterMethod() {
        allComponents.forEach(componentFacade::deleteComponent);
        assertThat(componentFacade.listAllComponents()).isEmpty();
    }

    @Test
    public void listAllComponents() {
        assertThat(componentFacade.listAllComponents()).containsExactlyElementsOf(allComponents);
    }

    @Test
    public void listAllComponentsWithType() {
        for (ComponentType type : EnumSet.allOf(ComponentType.class)) {
            assertThat(componentFacade.listAllComponentsWithType(type))
                    .containsExactlyElementsOf(allComponents.stream().filter(p -> p.getType() == type).collect(Collectors.toList()));
        }
    }

    @Test
    public void listAllAvailableComponents() {
        assertThat(componentFacade.listAllAvailableComponents()).containsExactlyElementsOf(availableComponents);
    }

    @Test
    public void listAllAvailableComponentsWithType() {
        for (ComponentType type : EnumSet.allOf(ComponentType.class)) {
            assertThat(componentFacade.listAllAvailableComponentsWithType(type))
                    .containsExactlyElementsOf(availableComponents.stream().filter(p -> p.getType() == type).collect(Collectors.toList()));
        }
    }

    @Test
    public void createComponent() {
        ComponentDTO component = new ComponentDTO("New component", false, ComponentType.ENGINE);
        componentFacade.createComponent(component);
        allComponents.add(component);
        assertThat(componentFacade.findComponentByID(component.getId())).isEqualTo(component);
    }

    @Test
    public void updateComponent() {
        ComponentDTO component = allComponents.get(3);
        component.setName("Update name");
        componentFacade.updateComponent(component);
        assertThat(componentFacade.findComponentByID(component.getId())).isEqualTo(component);
    }

    @Test
    public void deleteComponent() {
        ComponentDTO component = allComponents.get(1);
        componentFacade.deleteComponent(component);
        allComponents.remove(component);
        assertThat(componentFacade.listAllComponents()).containsExactlyElementsOf(allComponents);
    }

    @Test
    public void findComponentByID() {
        for (ComponentDTO component : allComponents) {
            assertThat(componentFacade.findComponentByID(component.getId())).isEqualTo(component);
        }
    }

    @Test
    public void findComponentByName() {
        for (ComponentDTO component : allComponents) {
            assertThat(componentFacade.findComponentByName(component.getName())).isEqualTo(component);
        }
    }
}
