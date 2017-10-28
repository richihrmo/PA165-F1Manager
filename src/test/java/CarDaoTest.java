import cz.muni.fi.PersistenceApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * @author Richard Hrmo
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class CarDaoTest{
     @PersistenceContext
     private EntityManager em;

     @PersistenceUnit
     private EntityManagerFactory emf;

//     @Autowired
//     private CarDaoImpl carDao = new CarDaoImpl();

    // mne sa to naozaj nechce robit naslepo
    // pockam kym bude aspon kostra k tomu a potom mozem robit testy,
    // naozaj ma nebavi vsetko pisat
    // dakujem za pochopenie
}
