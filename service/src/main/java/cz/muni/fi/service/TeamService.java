package cz.muni.fi.service;

import java.util.List;

import cz.muni.fi.entities.Car;
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
     * @throws cz.muni.fi.service.exception.ServiceDataAccessException if there is problem with dao
     */
    Team findTeamById(Long id);

    /**
     * Find all teams
     *
     * @return list of found teams
     * @throws cz.muni.fi.service.exception.ServiceDataAccessException if there is problem with dao
     */
    List<Team> findAllTeams();


    /**
     * Find all cars used by teams
     *
     * @return list of found cars
     * @throws cz.muni.fi.service.exception.ServiceDataAccessException if there is problem with dao
     */
    List<Car> findAllTeamCars();

    /**
     * Find all team drivers
     *
     * @return list of team drivers
     * @throws cz.muni.fi.service.exception.ServiceDataAccessException if there is problem with dao
     */
    List<Driver> findAllTeamCarDrivers();

    /**
     * Create new team
     *
     * @param team team to be created
     * @return created Team
     * @throws IllegalArgumentException if team argument id null
     * @throws cz.muni.fi.service.exception.ServiceDataAccessException if there is problem with dao
     */
    Team createTeam(Team team);

    /**
     * Update team
     *
     * @param team team to be updated
     * @return updated Team
     * @throws IllegalArgumentException if team argument id null
     * @throws cz.muni.fi.service.exception.ServiceDataAccessException if there is problem with dao
     */
    Team updateTeam(Team team);

    /**
     * Delete team
     *
     * @param team team to be updated
     * @throws IllegalArgumentException if team argument id null
     * @throws cz.muni.fi.service.exception.ServiceDataAccessException if there is problem with dao
     */
    void deleteTeam(Team team);
}
