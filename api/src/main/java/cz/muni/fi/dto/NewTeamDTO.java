package cz.muni.fi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Matus Macko
 */
public class NewTeamDTO {
    @Getter @Setter private String name;
    @Getter @Setter private CarDTO carOne;
    @Getter @Setter private CarDTO carTwo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof NewTeamDTO)) return false;

        NewTeamDTO that = (NewTeamDTO) o;

        if (!name.equals(that.name)) return false;
        if (!carOne.equals(that.carOne)) return false;
        return carTwo.equals(that.carTwo);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + carOne.hashCode();
        result = 31 * result + carTwo.hashCode();
        return result;
    }
}