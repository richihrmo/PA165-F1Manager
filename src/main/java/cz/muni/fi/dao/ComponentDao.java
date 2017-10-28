package cz.muni.fi.dao;

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
    public List<Component> listAvailableComponents();

    /**
     * List all components in DB
     *
     * @return list of all components
     */
    public List<Component> listAllComponents();

    /**
     * Find component in DB by name
     *
     * @param name component name
     * @return found component or null
     */
    public Component findComponent(String name);

    /**
     * Find component in DB by id
     *
     * @param id component id
     * @return found component or null
     */
    public Component findComponent(Long id);

    /**
     * Add component to DB
     *
     * @param component component to be added
     */
    public void addComponent(Component component);

    /**
     * Update component in DB
     *
     * @param component compondent to be updated
     */
    public void updateComponent(Component component);

    /**
     * Delete component from DB
     *
     * @param component component to be deleted
     */
    public void deleteComponent(Component component);
}