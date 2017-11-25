package cz.muni.fi.service.config;
import cz.muni.fi.PersistenceApplicationContext;
import cz.muni.fi.facade.ComponentFacade;
import cz.muni.fi.service.ComponentService;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Robert Tamas
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses={ComponentService.class, ComponentFacade.class})
public class ServiceConfiguration {
    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        return dozer;
    }

}
