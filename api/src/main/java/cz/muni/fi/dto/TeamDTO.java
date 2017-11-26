package cz.muni.fi.dto;

import lombok.Setter;
import lombok.Getter;

/**
 * @author Matus Macko
 */
public class TeamDTO {
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private CarDTO carOne;
    @Getter @Setter private CarDTO carTwo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof TeamDTO)) return false;

        TeamDTO teamDto = (TeamDTO) o;

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