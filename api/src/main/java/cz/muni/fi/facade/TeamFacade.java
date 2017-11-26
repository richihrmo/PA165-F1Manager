package cz.muni.fi.facade;

import java.util.List;

import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.dto.NewTeamDTO;
import cz.muni.fi.dto.TeamDTO;

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
    void createTeam(NewTeamDTO team);

    /**
     * Update team
     *
     * @param team team to be updated
     */
    void updateTeam(TeamDTO team);

    /**
     * Delete team
     *
     * @param teamId id of a team to be deleted
     */
    void deleteTeam(Long teamId);

    /**
     * Get all car drivers of team cars
     *
     * @return list of car drivers
     */
    List<DriverDTO> getAllTeamCarDrivers();
}
