package service;

import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import model.Horse;

@RequiredArgsConstructor
public class HorseService {
    private final List<Horse> horses;

    public List<Horse> getHorses() {
        return horses;
    }

    private boolean horseMatchesSearchString(Horse h, String searchString) {
        searchString = searchString.toLowerCase();
        return h.getName().toLowerCase().contains(searchString) ||
            h.getBreed().getName().toLowerCase().contains(searchString) ||
            h.getRider().getName().toLowerCase().contains(searchString) ||
            h.getRider().getUniformColor().toLowerCase().contains(searchString) ||
            h.getNumber().toString().contains(searchString);
    }

    public List<Horse> findHorses(String searchString) {
        List<Horse> resultList = new LinkedList<>();
        horses.forEach(horse -> {
            if (horseMatchesSearchString(horse, searchString)) {
                resultList.add(horse);
            }
        });
        return resultList;
    }
}
