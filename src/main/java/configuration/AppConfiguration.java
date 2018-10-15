package configuration;

import app.HorseRaceEmulatorApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    HorseServiceConfiguration.class,
    RaceServiceConfiguration.class,
    EmulationServiceConfiguration.class,
    IOConfiguration.class
})
public class AppConfiguration {

    @Autowired
    HorseServiceConfiguration horseServiceConfiguration;

    @Autowired
    RaceServiceConfiguration raceServiceConfiguration;

    @Autowired
    EmulationServiceConfiguration emulationServiceConfiguration;

    @Autowired
    IOConfiguration ioConfiguration;

    @Bean
    HorseRaceEmulatorApp appBean() {
        return new HorseRaceEmulatorApp(
            horseServiceConfiguration.horseService(),
            raceServiceConfiguration.raceService(),
            emulationServiceConfiguration.emulationService(),
            ioConfiguration.bufferedReader(),
            ioConfiguration.printStream()
        );
    }
}
