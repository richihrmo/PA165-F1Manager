package cz.muni.fi.dto;

import cz.muni.fi.enums.ComponentType;

public class ComponentDTO {

    private Long id;
    private String name;
    private ComponentType type;
    private Boolean isAvailable;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ComponentType getType() {
        return type;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ComponentType type) {
        this.type = type;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComponentDTO that = (ComponentDTO) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getType() == that.getType();
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        return result;
    }
}
