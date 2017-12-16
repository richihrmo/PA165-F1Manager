package cz.muni.fi;


import cz.muni.fi.entities.Driver;
import cz.muni.fi.persistanceEnums.DrivingSkill;
import cz.muni.fi.service.DriverService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;


@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    DriverService driverService;
    
    @Override
    public void loadData() throws IOException {
        createDriver("Anakin", "Skywalker", "unknown", DrivingSkill.EXTREME_REFLEXES);
        createDriver("Michael", "Schumacher", "germany", DrivingSkill.POWER_SLIDING);
        createDriver("Lewis", "Hamilton", "unknown", DrivingSkill.POWER_SLIDING);
        createDriver("Sebastian", "Vettel", "unknown", DrivingSkill.DRIVING_ON_WET);
        
    }
    
    public void createDriver(String name, String surname, String nationality, DrivingSkill skill){
        Driver d = new Driver();
        d.setName(name);
        d.setSurname(surname);
        d.setNationality(nationality);
        d.setSpecialSkill(skill);
        driverService.addDriver(d);
}
}