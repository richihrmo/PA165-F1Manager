package cz.muni.fi.facade;

import cz.muni.fi.dto.ComponentDTO;
import cz.muni.fi.enums.ComponentType;

import java.util.List;

/**
 * @author Robert Tamas
 */
public interface ComponentFacade {

    /**
     * Returns all Components from the database
     *
     * @return List of all Components, empty List if no Component is found
     */
    List<ComponentDTO> listAllComponents();

    /**
     * Returns all Components with exact type from the database
     *
     * @return List of all Components, empty List if no Component is found
     */
    List<ComponentDTO> listAllComponentsWithType(ComponentType type);

    /**
     * Returns all available Components from the database
     *
     * @return List of all Components, empty List if no Component is found
     */
    List<ComponentDTO> listAllAvailableComponents();

    /**
     * Returns all available Components with exact type from the database
     *
     * @return List of all Components, empty List if no Component is found
     */
    List<ComponentDTO> listAllAvailableComponentsWithType(ComponentType type);

    /**
     * Takes unique id of Component and returns a corresponding entity if found
     *
     * @param id of Component object, non-null
     * @return Component entity if found, null otherwise
     */
    ComponentDTO findComponentByID(Long id);

    /**
     * Takes unique name of Component and returns a corresponding entity if found
     *
     * @param name non-null string representing name
     * @return component with given name if exists, null otherwise
     */
    ComponentDTO findComponentByName(String name);

    /**
     * Takes an object of type Component and creates an entry in a database
     *
     * @param component non-null object to be created in a database
     * @return created component
     */
    ComponentDTO createComponent(ComponentDTO component);

    /**
     * Takes an object of type Component and updates an entry in a database
     *
     * @param component non-null object to be updated in a database
     * @return created component
     */
    ComponentDTO updateComponent(ComponentDTO component);

    /**
     * Takes an object of type Component and deletes an entry from the database
     *
     * @param component non-null object to be deleted from the database
     */
    void deleteComponent(ComponentDTO component);

}
