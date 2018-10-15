package app;

import configuration.AppConfiguration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import model.Horse;
import model.Race;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.EmulationService;
import service.HorseService;
import service.RaceService;

@RequiredArgsConstructor
public class HorseRaceEmulatorApp {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
            AppConfiguration.class);
        HorseRaceEmulatorApp app = applicationContext.getBean(HorseRaceEmulatorApp.class);
        app.run(args);
    }

    private final HorseService horseService;
    private final RaceService raceService;
    private final EmulationService emulationService;
    private final BufferedReader bufferedReader;
    private final PrintStream printStream;

    public void run(String[] args) throws IOException {
        // show information about upcoming race
        Race race = raceService.getUpcomingRace();
        printStream.println(race);

        // choose a horse (make a bet). search by name, breed, rider's name
        Horse betHorse = null;
        do {
            printStream.print("Choose horse for bet: ");
            String searchString = bufferedReader.readLine().trim();
            Collection<Horse> searchResult = horseService.findHorses(searchString);
            final int resultSize = searchResult.size();
            if (resultSize == 0) {
                printStream.format("Horse not found for query: '%s'%n", searchString);
            } else if (resultSize > 1) {
                printStream.format("%d horses found for query: '%s':%n", resultSize, searchString);
                searchResult.forEach(printStream::println);
            } else {
                betHorse = searchResult.iterator().next();
                printStream.format("So you want to bet on %s? (y/n) ", betHorse);
                String answer = bufferedReader.readLine().trim().toLowerCase();
                if (answer.equals("yes") || answer.equals("y")) {
                    printStream.format("You bet on: %s%n", betHorse);
                } else {
                    betHorse = null;
                }
            }
        } while (betHorse == null);

        // start race emulation
        Horse winner = emulationService.runRace(race, betHorse);

        // announce winner
        printStream.format("The WINNER: %n    %s%n", winner);
        if (winner == betHorse) {
            printStream.println("YOU WIN, MY FRIEND!");
        } else {
            printStream.println("Nah, bad luck.");
        }
    }

}
