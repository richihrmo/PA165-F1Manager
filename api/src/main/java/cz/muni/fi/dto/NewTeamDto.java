package cz.muni.fi.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Matus Macko
 */
public class NewTeamDto {
    @Getter @Setter private String name;
    @Getter @Setter private CarDto carOne;
    @Getter @Setter private CarDto carTwo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof NewTeamDto)) return false;

        NewTeamDto that = (NewTeamDto) o;

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