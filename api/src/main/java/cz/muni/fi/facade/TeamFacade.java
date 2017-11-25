package cz.muni.fi.facade;

import java.util.List;

import cz.muni.fi.dto.NewTeamDto;
import cz.muni.fi.dto.TeamDto;

/**
 * @author Matus Macko
 */
public interface TeamFacade {
    /**
     * Get all teams
     *
     * @return list of all teams
     */
    List<TeamDto> getAllTeams();

    /**
     * Get Team by id
     *
     * @param teamId team id
     * @return found Team or Null
     */
    TeamDto getTeamById(Long teamId);

    /**
     * Create new Team
     *
     * @param team team to be created
     */
    void createTeam(NewTeamDto team);

    /**
     * Update team
     *
     * @param team team to be updated
     */
    void updateTeam(TeamDto team);

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
    List<DriverDto> getAllTeamCarDrivers();
}
