package service;

import lombok.RequiredArgsConstructor;
import model.Race;

@RequiredArgsConstructor
public class RaceService {

    private final Double distance;
    private final HorseService horseService;

    public Race getUpcomingRace() {
        Race race = new Race();
        race.setDistance(distance);
        race.setParticipants(horseService.getHorses());
        return race;
    }
}
