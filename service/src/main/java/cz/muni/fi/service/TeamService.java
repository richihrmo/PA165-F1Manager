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
     */
    Team findTeamById(Long id);

    /**
     * Find all teams
     *
     * @return list of found teams
     * @throws
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
     */
    void createTeam(Team team);

    /**
     * Update team
     *
     * @param team team to be updated
     */
    void updateTeam(Team team);

    /**
     * Delete team
     *
     * @param team team to be updated
     */
    void deleteTeam(Team team);
}
