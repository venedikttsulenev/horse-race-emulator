package model;

import java.util.List;
import lombok.Data;

@Data
public class Race {
    private List<Horse> participants;
    private Double distance;

    public String toString() {
        String prefix = String.format("Race:%n  distance: %f%n  participants:", distance);
        StringBuilder stringBuilder = new StringBuilder(prefix);
        participants.forEach(horse -> {
            stringBuilder.append("\n    ");
            stringBuilder.append(horse);
        });
        return stringBuilder.toString();
    }
}
