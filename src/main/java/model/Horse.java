package model;

import annotation.InjectRandomDouble;
import lombok.Data;

@Data
public class Horse {

    private Integer number;
    private String name;
    private Breed breed;
    private Rider rider;

    @InjectRandomDouble(min = 0.9, max = 1.5)
    private Double velocity;

    public String toString() {
        return String.format(
            "#%d, %s, rider: %s (%s)",
            number,
            name,
            rider.getName(),
            rider.getUniformColor()
        );
    }
}
