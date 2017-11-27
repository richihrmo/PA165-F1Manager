package cz.muni.fi.service;

import cz.muni.fi.dao.TeamDao;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.entities.Team;
import cz.muni.fi.service.exception.ServiceDataAccessException;
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
        if (id == null) throw new IllegalArgumentException("Id argument cannot be null!");

        try {
            return teamDao.findTeamById(id);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot find team with id :" + id, e);
        }
    }

    @Override
    public List<Team> findAllTeams() {
        return teamDao.listTeams();
    }

    @Override
    public List<Driver> findAllTeamCarDrivers() {
        List<Driver> drivers = new ArrayList<>();

        try {
            for (Team team : teamDao.listTeams()) {
                drivers.addAll(Arrays.asList(team.getCarOne().getDriver(), team.getCarTwo().getDriver()));
            }
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot find all team drivers", e);
        }

        return drivers;
    }

    @Override
    public Team createTeam(Team team) {
        if (team == null) throw new IllegalArgumentException("Team argument cannot be null!");

        try {
            return teamDao.addTeam(team);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot create team :" + team.toString(), e);
        }
    }

    @Override
    public Team updateTeam(Team team) {
        if (team == null) throw new IllegalArgumentException("Team argument cannot be null!");

        try {
            return teamDao.updateTeam(team);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot update team:" + team.toString(), e);
        }
    }

    @Override
    public void deleteTeam(Team team) {
        if (team == null) throw new IllegalArgumentException("Team argument cannot be null!");

        try {
            teamDao.deleteTeam(team);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot delete team :" + team.toString(), e);
        }
    }
}
