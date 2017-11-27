package cz.muni.fi.service;

import java.util.List;

import cz.muni.fi.entities.Team;
import cz.muni.fi.entities.Driver;

/**
 * @author Matus Macko
 */
public interface TeamService {
    /**
     * Find team by id
     *
     * @param id team id
     * @return found team
     * @throws IllegalArgumentException if id argument id null
     */
    Team findTeamById(Long id);

    /**
     * Find all teams
     *
     * @return list of found teams
     */
    List<Team> findAllTeams();

    /**
     * Find all team drivers
     *
     * @return list of team drivers
     */
    List<Driver> findAllTeamCarDrivers();

    /**
     * Create new team
     *
     * @param team team to be created
     * @return created Team
     * @throws IllegalArgumentException if team argument id null
     */
    Team createTeam(Team team);

    /**
     * Update team
     *
     * @param team team to be updated
     * @return updated Team
     * @throws IllegalArgumentException if team argument id null
     */
    Team updateTeam(Team team);

    /**
     * Delete team
     *
     * @param team team to be updated
     * @throws IllegalArgumentException if team argument id null
     */
    void deleteTeam(Team team);
}
