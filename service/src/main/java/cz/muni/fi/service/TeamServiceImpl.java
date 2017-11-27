package cz.muni.fi.service;

import cz.muni.fi.dao.TeamDao;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Matus Macko
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    @Override
    public Team findTeamById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id argument cannot be null!");
        }

        return teamDao.findTeamById(id);
    }

    @Override
    public List<Team> findAllTeams() {
        return teamDao.listTeams();
    }

    @Override
    public List<Driver> findAllTeamCarDrivers() {
        List<Driver> drivers = new ArrayList<>();
        for (Team team : teamDao.listTeams()) {
            drivers.addAll(Arrays.asList(team.getCarOne().getDriver(), team.getCarTwo().getDriver()));
        }

        return drivers;
    }

    @Override
    public Team createTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team argument cannot be null!");
        }
        return teamDao.addTeam(team);
    }

    @Override
    public Team updateTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team argument cannot be null!");
        }
        return teamDao.updateTeam(team);
    }

    @Override
    public void deleteTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team argument cannot be null!");
        }
        teamDao.deleteTeam(team);
    }
}
