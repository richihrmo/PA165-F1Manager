package cz.muni.fi.facade;

import cz.muni.fi.dto.CarDTO;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.dto.TeamEditDTO;
import cz.muni.fi.dto.TeamDTO;

import java.util.List;

/**
 * @author Matus Macko
 */
public interface TeamFacade {
    /**
     * Get all teams
     *
     * @return list of all teams
     */
    List<TeamDTO> getAllTeams();

    /**
     * Get Team by id
     *
     * @param teamId team id
     * @return found Team or Null
     */
    TeamDTO getTeamById(Long teamId);

    /**
     * Create new Team
     *
     * @param team team to be created
     * @return created Team
     */
    TeamDTO createTeam(TeamEditDTO team);

    /**
     * Update team
     *
     * @param team team to be updated
     * @return updated Team
     */
    TeamDTO updateTeam(TeamEditDTO team);

    /**
     * Delete team
     *
     * @param team team to be deleted
     */
    void deleteTeam(TeamDTO team);

    /**
     * Get all car drivers of team cars
     *
     * @return list of car drivers
     */
    List<DriverDTO> getAllTeamCarDrivers();


    /**
     * Get all cars used by teams
     *
     * @return list of cars
     */
    List<CarDTO> getAllTeamCars();
}
