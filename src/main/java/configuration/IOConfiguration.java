package configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IOConfiguration {

    @Bean
    PrintStream printStream() {
        return System.out;
    }

    @Bean
    BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
