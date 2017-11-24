/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.dto;

import cz.muni.fi.enums.DrivingSkill;
import java.util.Objects;

/**
 * @author Lucie Kurečková, 445264
 */
public class DriverDTO {
    
    private Long id;

    private String name;
    
    private String surname;
    
    private String nationality;
    
    private boolean isMainDriver;
    
    private DrivingSkill specialSkill;

    public DriverDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isIsMainDriver() {
        return isMainDriver;
    }

    public void setIsMainDriver(boolean isMainDriver) {
        this.isMainDriver = isMainDriver;
    }

    public DrivingSkill getSpecialSkill() {
        return specialSkill;
    }

    public void setSpecialSkill(DrivingSkill specialSkill) {
        this.specialSkill = specialSkill;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.surname);
        hash = 97 * hash + Objects.hashCode(this.nationality);
        hash = 97 * hash + (this.isMainDriver ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.specialSkill);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DriverDTO)) {
            return false;
        }
        final DriverDTO other = (DriverDTO) obj;
        if (this.isMainDriver != other.isMainDriver) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.nationality, other.nationality)) {
            return false;
        }
        if (this.specialSkill != other.specialSkill) {
            return false;
        }
        return true;
    }
    
    
    
}
