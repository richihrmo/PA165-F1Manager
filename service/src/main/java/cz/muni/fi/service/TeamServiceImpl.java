package cz.muni.fi.service;

import cz.muni.fi.dao.CarDao;
import cz.muni.fi.dao.DriverDao;
import cz.muni.fi.dao.TeamDao;
import cz.muni.fi.dto.DriverDTO;
import cz.muni.fi.entities.Car;
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

    @Autowired
    private DriverDao driverDao;

    @Autowired
    private CarDao carDao;

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
    public List<Car> findAllTeamCars(){
        List<Car> cars = new ArrayList<>();

        for (Team team: teamDao.listTeams()) {
            cars.add(team.getCarOne());
            cars.add(team.getCarTwo());
        }

        return cars;
    }

    @Override
    public List<Driver> findAllTeamCarDrivers() {
        List<Driver> drivers = new ArrayList<>();

        try {
            for (Team team : teamDao.listTeams()) {
                Driver carOneDriver = team.getCarOne().getDriver();
                Driver carTwoDriver = team.getCarTwo().getDriver();

                if (!drivers.contains(carOneDriver)) {
                    drivers.add(carOneDriver);
                }

                if (!drivers.contains(carTwoDriver)) {
                    drivers.add(carTwoDriver);
                }
            }
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot find all team drivers", e);
        }

        return drivers;
    }

    @Override
    public Team createTeam(Team team) {
        if (team == null) throw new IllegalArgumentException("Team argument cannot be null!");
        if (team.getCarOne() == null || team.getCarTwo() == null)
            throw new IllegalArgumentException("Team car cannot be null!");

        driverUpdateUtil(team, true);

        try {
            return teamDao.addTeam(team);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot create team :" + team.toString(), e);
        }
    }

    @Override
    public Team updateTeam(Team team) {
        if (team == null) throw new IllegalArgumentException("Team argument cannot be null!");
        if (team.getCarOne() == null || team.getCarTwo() == null)
            throw new IllegalArgumentException("Team car cannot be null!");

        Team old_team = teamDao.findTeamById(team.getId());
        driverUpdateUtil(old_team, false);
        driverUpdateUtil(team, true);

        try {
            return teamDao.updateTeam(team);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot update team:" + team.toString(), e);
        }
    }

    @Override
    public void deleteTeam(Team team) {
        if (team == null) throw new IllegalArgumentException("Team argument cannot be null!");
        if (team.getCarOne() == null || team.getCarTwo() == null)
            throw new IllegalArgumentException("Team car cannot be null!");

        driverUpdateUtil(team, false);

        try {
            teamDao.deleteTeam(team);
        } catch (Throwable e) {
            throw new ServiceDataAccessException("Cannot delete team :" + team.toString(), e);
        }
    }

    private void driverUpdateUtil(Team team, boolean main){
        Driver one = team.getCarOne().getDriver();
        Driver two = team.getCarTwo().getDriver();
        if (main){
            one.setAsMainDriver();
            two.setAsMainDriver();
        } else {
            one.setAsTestDriver();
            two.setAsTestDriver();
        }
        driverDao.updateDriver(one);
        driverDao.updateDriver(two);
    }
}
