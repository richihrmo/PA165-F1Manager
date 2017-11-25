package cz.muni.fi.dto;

import lombok.Setter;
import lombok.Getter;

/**
 * @author Matus Macko
 */
public class TeamDto {
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private CarDto carOne;
    @Getter @Setter private CarDto carTwo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof TeamDto)) return false;

        TeamDto teamDto = (TeamDto) o;

        if (!name.equals(teamDto.name)) return false;
        if (!carOne.equals(teamDto.carOne)) return false;
        return carTwo.equals(teamDto.carTwo);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + carOne.hashCode();
        result = 31 * result + carTwo.hashCode();
        return result;
    }
}