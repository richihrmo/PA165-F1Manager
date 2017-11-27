package cz.muni.fi.service;

import cz.muni.fi.entities.Component;
import cz.muni.fi.persistanceEnums.ComponentType;
import cz.muni.fi.service.exceptions.TeamFormulaDataAccessException;

import java.util.List;

public interface ComponentService {

    /**
     * Returns all Components from the database
     *
     * @return List of all Components, empty List if no Component is found
     * @throws TeamFormulaDataAccessException when error on data access layer occurs
     */
    List<Component> listAllComponents();

    /**
     * Returns all Components with exact type from the database
     *
     * @param type component type
     * @return List of all Components, empty List if no Component is found
     * @throws IllegalArgumentException when type argument is null
     * @throws TeamFormulaDataAccessException when error on data access layer occurs
     */
    List<Component> listAllComponentsWithType(ComponentType type);

    /**
     * Returns all available Components from the database
     *
     * @return List of all Components, empty List if no Component is found
     * @throws TeamFormulaDataAccessException when error on data access layer occurs
     */
    List<Component> listAllAvailableComponents();

    /**
     * Returns all available Components with exact type from the database
     *
     * @param type component type
     * @return List of all Components, empty List if no Component is found
     * @throws IllegalArgumentException when type argument is null
     * @throws TeamFormulaDataAccessException when error on data access layer occurs
     */
    List<Component> listAllAvailableComponentsWithType(ComponentType type);

    /**
     * Takes unique id of Component and returns a corresponding entity if found
     *
     * @param id of Component object, non-null
     * @return Component entity if found, null otherwise
     * @throws IllegalArgumentException when id argument is null
     * @throws TeamFormulaDataAccessException when error on data access layer occurs
     */
    Component findComponentByID(Long id);

    /**
     * Takes unique name of Component and returns a corresponding entity if found
     *
     * @param name non-null string representing name
     * @return component with given name if exists, null otherwise
     * @throws IllegalArgumentException when name argument is null
     * @throws TeamFormulaDataAccessException when error on data access layer occurs
     */
    Component findComponentByName(String name);

    /**
     * Takes an object of type Component and creates an entry in a database
     *
     * @param component non-null object to be created in a database
     * @throws IllegalArgumentException when component argument is null
     * @throws TeamFormulaDataAccessException when error on data access layer occurs
     */
    void createComponent(Component component);

    /**
     * Takes an object of type Component and updates an entry in a database
     *
     * @param component non-null object to be updated in a database
     * @throws IllegalArgumentException when component argument is null
     * @throws TeamFormulaDataAccessException when error on data access layer occurs
     */
    void updateComponent(Component component);

    /**
     * Takes an object of type Component and deletes an entry from the database
     *
     * @param component non-null object to be deleted from the database
     * @throws IllegalArgumentException when component argument is null
     * @throws TeamFormulaDataAccessException when error on data access layer occurs
     */
    void deleteComponent(Component component);
}
