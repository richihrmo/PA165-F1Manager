package cz.muni.fi.facade;

import cz.muni.fi.dto.DriverDTO;
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
     */
    void createTeam(TeamDTO team);

    /**
     * Update team
     *
     * @param team team to be updated
     */
    void updateTeam(TeamDTO team);

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
}
