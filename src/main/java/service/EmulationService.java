package service;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import model.Horse;
import model.Race;

@RequiredArgsConstructor
public class EmulationService {

    private final PrintStream output;
    private final Double velocityVariance;
    private final Double drawStep;

    @RequiredArgsConstructor
    private class RaceHorseRecord implements Comparable<RaceHorseRecord> {

        public final Horse horse;
        private double distanceTravelled = 0;

        @Override
        public int compareTo(RaceHorseRecord r) {
            return Double.compare(r.distanceTravelled, distanceTravelled);
        }
    }

    private <T> T getLastElement(List<T> list) {
        return list.get(list.size() - 1);
    }

    private void clearLine() {
        output.print("\033[1A");
        output.print("\033[2K");
    }

    private void printRed(int i) {
        output.print("\u001B[31;1m");
        output.print(i);
        output.print("\u001B[0m");
    }

    private RaceHorseRecord findHorseAt(double distance, List<RaceHorseRecord> participants) {
        for (RaceHorseRecord p : participants) {
            if (p.distanceTravelled >= distance - 0.5 * drawStep
                && p.distanceTravelled < distance + 0.5 * drawStep) {
                return p;
            }
        }
        return null;
    }

    private void printCurrentState(List<RaceHorseRecord> participants, Race race, Horse betHorse) {
        clearLine();
        for (double d = 0; d < Math.max(participants.get(0).distanceTravelled, race.getDistance()) + 0.5*drawStep; d += drawStep) {
            RaceHorseRecord r = findHorseAt(d, participants);
            if (r == null) {
                if (Math.abs(race.getDistance() - d) < 0.5*drawStep) {
                    output.print('|');
                } else {
                    output.print('.');
                }
            } else if (r.horse == betHorse) {
                printRed(r.horse.getNumber());
            } else {
                output.print(r.horse.getNumber());
            }
        }
        final double distanceTraveledPercentage =
            getLastElement(participants).distanceTravelled * 100 / race.getDistance();
        output.format(" (%.0f %%)%n", Math.min(distanceTraveledPercentage, 100.0));
    }

    private void printFinalStandings(List<RaceHorseRecord> participants) {
        output.println("Final standings:");
        for (int i = 0; i < participants.size(); ++i) {
            output.format("  %d | %s%n", i + 1, participants.get(i).horse);
        }
    }

    public Horse runRace(Race race, Horse betHorse) {
        List<RaceHorseRecord> participants = race.getParticipants().stream()
            .map(RaceHorseRecord::new)
            .collect(Collectors.toCollection(() -> new ArrayList<>(race.getParticipants().size())));
        ThreadLocalRandom random = ThreadLocalRandom.current();
        while (getLastElement(participants).distanceTravelled < race.getDistance()) {
            for (RaceHorseRecord p : participants) {
                p.distanceTravelled += p.horse.getVelocity() + random
                    .nextDouble(-velocityVariance, velocityVariance);
            }
            participants.sort(Comparator.naturalOrder());
            printCurrentState(participants, race, betHorse);
            try {
                Thread.sleep(960L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printFinalStandings(participants);

        return participants.get(0).horse;
    }
}
