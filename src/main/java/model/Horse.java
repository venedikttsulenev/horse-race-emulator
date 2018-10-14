package model;

import lombok.Data;

@Data
public class Horse {
    private Integer number;
    private String name;
    private Breed breed;
    private Rider rider;
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
