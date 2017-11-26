package cz.muni.fi.service;

import cz.muni.fi.dao.TeamDao;
import cz.muni.fi.entities.Driver;
import cz.muni.fi.entities.Team;
import cz.muni.fi.service.exceptions.TeamFormulaDataAccessException;
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
        try {
            return teamDao.findTeamById(id);
        } catch (Throwable ex) {
            throw new TeamFormulaDataAccessException("Error during findTeamById", ex);
        }
    }

    @Override
    public List<Team> findAllTeams() {
        try {
            return teamDao.listTeams();
        } catch (Throwable ex) {
            throw new TeamFormulaDataAccessException("Error during findAllTeams", ex);
        }
    }

    @Override
    public List<Driver> findAllTeamCarDrivers() {
        try {
            List<Driver> drivers = new ArrayList<>();
            for (Team team : teamDao.listTeams()) {
                drivers.addAll(Arrays.asList(team.getCarOne().getDriver(), team.getCarTwo().getDriver()));
            }

            return drivers;
        } catch (Throwable ex) {
            throw new TeamFormulaDataAccessException("Error during findAllTeamCarDrivers", ex);
        }
    }

    @Override
    public void createTeam(Team team) {
        try {
            teamDao.addTeam(team);
        } catch (Throwable ex) {
            throw new TeamFormulaDataAccessException("Error during createTeam", ex);
        }
    }

    @Override
    public void updateTeam(Team team) {
        try {
            teamDao.updateTeam(team);
        } catch (Throwable ex) {
            throw new TeamFormulaDataAccessException("Error during updateTeam", ex);
        }
    }

    @Override
    public void deleteTeam(Team team) {
        try {
            teamDao.deleteTeam(team);
        } catch (Throwable ex) {
            throw new TeamFormulaDataAccessException("Error during deleteTeam", ex);
        }
    }
}
