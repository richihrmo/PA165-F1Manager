package cz.muni.fi.dao;

import cz.muni.fi.entities.Car;
import java.util.List;

/**
 * @author Lucie Kureckova, 445264
 */
public interface TeamDao {
    
    /**
     * Add team to DB
     * @param team to be added
     */
    public void addTeam(Team team);
    
    /**
     * Update team in DB
     * @param team to be updated
     */
    public void updateTeam(Team team);
    
    /**
     * Delete team in DB
     * @param team to be deleted
     */
    public void deleteTeam(Team team);
    
    /**
     * List all teams in DB
     * @return list of teams in DB
     */
    public List<Team> listTeams();
    
    /**
     * Find team by name
     * @param name of team
     * @return founded Team
     */
    public Team findTeam(String name);
    
    /**
     * Find team by id
     * @param id of team
     * @return founded Team
     */
    public Team findTeam(Long id);
}
