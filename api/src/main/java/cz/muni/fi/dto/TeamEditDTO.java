package cz.muni.fi.dto;

import lombok.Setter;
import lombok.Getter;

import java.util.Objects;

/**
 * @author Matus Macko
 */
public class TeamEditDTO {
    @Getter @Setter private Long id;
    @Getter @Setter private String name;
    @Getter @Setter private Long carOneId;
    @Getter @Setter private Long carTwoId;

    public TeamEditDTO() {}

    public TeamEditDTO(String name, Long carOneId, Long carTwoId) {
        this.name = name;
        this.carOneId = carOneId;
        this.carTwoId = carTwoId;
    }

    public TeamEditDTO(Long id, String name, Long carOneId, Long carTwoId) {
        this.id = id;
        this.name = name;
        this.carOneId = carOneId;
        this.carTwoId = carTwoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof TeamEditDTO)) return false;
        TeamEditDTO that = (TeamEditDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(carOneId, that.carOneId) &&
                Objects.equals(carTwoId, that.carTwoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, carOneId, carTwoId);
    }
}