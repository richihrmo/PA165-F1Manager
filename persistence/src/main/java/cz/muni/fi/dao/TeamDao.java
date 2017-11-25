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
     * @throws IllegalArgumentException when argumet is null
     */
    public void addTeam(Team team) throws IllegalArgumentException;
    
    /**
     * Update team in DB
     * 
     * @param team to be updated
     * @throws IllegalArgumentException when argumet is null
     */
    public void updateTeam(Team team) throws IllegalArgumentException;
    
    /**
     * Delete team in DB
     * 
     * @param team to be deleted
     * @throws IllegalArgumentException when argumet is null
     */
    public void deleteTeam(Team team) throws IllegalArgumentException;
    
    /**
     * List all teams in DB
     * 
     * @return list of teams in DB
     */
    public List<Team> listTeams();
    
    /**
     * Find team by name
     * 
     * @param name of team
     * @return found Team
     * @throws IllegalArgumentException when argumet is null
     */
    public Team findTeamByName(String name) throws IllegalArgumentException;
    
    /**
     * Find team by id
     * 
     * @param id of team
     * @return found Team
     * @throws IllegalArgumentException when argumet is null
     */
    public Team findTeamById(Long id) throws IllegalArgumentException;
}