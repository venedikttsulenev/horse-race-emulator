package configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.RaceService;

@Configuration
@Import(HorseServiceConfiguration.class)
public class RaceServiceConfiguration {
    @Autowired
    HorseServiceConfiguration horseServiceConfiguration;

    @Bean
    RaceService raceService() {
        return new RaceService(100.0, horseServiceConfiguration.horseService());
    }
}
