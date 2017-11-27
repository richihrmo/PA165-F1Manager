package cz.muni.fi.dao;

import cz.muni.fi.entities.Component;

import java.util.List;

/**
 * @author Matus Macko
 */
public interface ComponentDao {
    /**
     * List all available components in DB
     *
     * @return list of all available components
     */
    List<Component> listAvailableComponents();

    /**
     * List all components in DB
     *
     * @return list of all components
     */
    List<Component> listAllComponents();

    /**
     * Find component in DB by name
     *
     * @param name component name
     * @return found component or null
     * @throws IllegalArgumentException when argument name is null
     */
    Component findComponentByName(String name);

    /**
     * Find component in DB by id
     *
     * @param id component id
     * @return found component or null
     * @throws IllegalArgumentException when id is null
     */
    Component findComponentById(Long id);

    /**
     * Add component to DB
     *
     * @param component component to be added
     * @throws IllegalArgumentException when argument is null or component id is not null
     *         or not nullable component attributes are null
     */
    Component addComponent(Component component);

    /**
     * Update component in DB
     *
     * @param component component to be updated
     * @throws IllegalArgumentException when argument is null or not nullable component attributes are null
     */
    Component updateComponent(Component component);

    /**
     * Delete component from DB
     *
     * @param component component to be deleted
     * @throws IllegalArgumentException when argument is null or not nullable component attributes are null
     */
    Component deleteComponent(Component component);
}