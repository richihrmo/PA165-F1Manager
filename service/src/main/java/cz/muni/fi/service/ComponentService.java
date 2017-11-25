package cz.muni.fi.service;

import cz.muni.fi.entities.Component;
import cz.muni.fi.persistanceEnums.ComponentType;

import java.util.List;

public interface ComponentService {

    /**
     * Returns all Components from the database
     *
     * @return List of all Components, empty List if no Component is found
     */
    List<Component> listAllComponents();

    /**
     * Returns all Components with exact type from the database
     *
     * @return List of all Components, empty List if no Component is found
     */
    List<Component> listAllComponentsWithType(ComponentType type) throws IllegalArgumentException;

    /**
     * Returns all available Components from the database
     *
     * @return List of all Components, empty List if no Component is found
     */
    List<Component> listAllAvailableComponents();

    /**
     * Returns all available Components with exact type from the database
     *
     * @return List of all Components, empty List if no Component is found
     */
    List<Component> listAllAvailableComponentsWithType(ComponentType type) throws IllegalArgumentException;

    /**
     * Takes unique id of Component and returns a corresponding entity if found
     *
     * @param id of Component object, non-null
     * @return Component entity if found, null otherwise
     */
    Component findComponentByID(Long id) throws IllegalArgumentException;

    /**
     * Takes unique name of Component and returns a corresponding entity if found
     *
     * @param name non-null string representing name
     * @return component with given name if exists, null otherwise
     */
    Component findComponentByName(String name) throws IllegalArgumentException;

    /**
     * Takes an object of type Component and creates an entry in a database
     *
     * @param component non-null object to be created in a database
     */
    void createComponent(Component component) throws IllegalArgumentException;

    /**
     * Takes an object of type Component and updates an entry in a database
     *
     * @param component non-null object to be updated in a database
     */
    void updateComponent(Component component) throws IllegalArgumentException;

    /**
     * Takes an object of type Component and deletes an entry from the database
     *
     * @param component non-null object to be deleted from the database
     */
    void deleteComponent(Component component) throws IllegalArgumentException;
}
