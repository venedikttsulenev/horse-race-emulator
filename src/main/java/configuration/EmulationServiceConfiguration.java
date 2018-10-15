package configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.EmulationService;

@Configuration
@Import(IOConfiguration.class)
public class EmulationServiceConfiguration {

    @Autowired
    IOConfiguration ioConfiguration;

    @Bean
    EmulationService emulationService() {
        return new EmulationService(
            ioConfiguration.printStream(),
            0.3,
            1.0,
            300L
        );
    }

}
