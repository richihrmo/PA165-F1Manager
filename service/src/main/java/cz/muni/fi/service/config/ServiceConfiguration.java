package cz.muni.fi.service.config;
import cz.muni.fi.PersistenceApplicationContext;
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
@ComponentScan(basePackages = "cz.muni.fi")
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new EntityMapping());
        return dozer;
    }

}
