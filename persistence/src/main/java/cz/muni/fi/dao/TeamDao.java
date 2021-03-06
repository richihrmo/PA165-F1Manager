package cz.muni.fi.dao;

import cz.muni.fi.entities.Team;

import java.util.List;

/**
 * @author Lucie Kureckova, 445264
 */
public interface TeamDao {
    
    /**
     * Add team to DB
     * 
     * @param team to be added
     * @return created Team
     * @throws IllegalArgumentException when argument is null, or not nullable attributes are null,
     *         or id is not null
     */
    Team addTeam(Team team);
    
    /**
     * Update team in DB
     * 
     * @param team to be updated
     * @return updated team
     * @throws IllegalArgumentException when argument is null or not nullable attributes are null
     */
    Team updateTeam(Team team);
    
    /**
     * Delete team in DB
     * 
     * @param team to be deleted
     * @return true if deleted successfully
     * @throws IllegalArgumentException when argument is null or not nullable attributes are null
     */
    boolean deleteTeam(Team team);
    
    /**
     * List all teams in DB
     * 
     * @return list of teams in DB
     */
    List<Team> listTeams();
    
    /**
     * Find team by name
     * 
     * @param name of team
     * @return found Team
     * @throws IllegalArgumentException when argument is null
     */
    Team findTeamByName(String name);
    
    /**
     * Find team by id
     * 
     * @param id of team
     * @return found Team
     * @throws IllegalArgumentException when argument is null
     */
    Team findTeamById(Long id);
}
