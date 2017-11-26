package cz.muni.fi.service.facade;

import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.dto.NewTeamDTO;
import cz.muni.fi.dto.TeamDTO;
import cz.muni.fi.entities.Team;
import cz.muni.fi.facade.TeamFacade;
import cz.muni.fi.service.BeanMappingService;
import cz.muni.fi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matus Macko
 */
@Service
@Transactional
public class TeamFacadeImpl implements TeamFacade{
    @Inject
    private TeamService teamService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<TeamDTO> getAllTeams() {
        return beanMappingService.mapTo(teamService.findAllTeams(), TeamDTO.class);
    }

    @Override
    public TeamDTO getTeamById(Long teamId) {
        return beanMappingService.mapTo(teamService.findTeamById(teamId), TeamDTO.class);
    }

    @Override
    public void createTeam(NewTeamDTO team) {
        Team mappedTeam = beanMappingService.mapTo(team, Team.class);
        teamService.createTeam(mappedTeam);
    }

    @Override
    public void updateTeam(TeamDTO team) {
        Team mappedTeam = beanMappingService.mapTo(team, Team.class);
        teamService.updateTeam(mappedTeam);
    }

    @Override
    public void deleteTeam(TeamDTO team) {
        Team mappedTeam = beanMappingService.mapTo(team, Team.class);
        teamService.deleteTeam(mappedTeam);
    }

    @Override
    public List<DriverDTO> getAllTeamCarDrivers() {
        return beanMappingService.mapTo(teamService.findAllTeamCarDrivers(), DriverDTO.class);
    }
}