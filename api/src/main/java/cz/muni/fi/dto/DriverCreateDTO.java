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
public class DriverCreateDTO {
    
    private Long id;

    private String name;
    
    private String surname;
    
    private String nationality;
    
    private DrivingSkill specialSkill;

    public DriverCreateDTO() {
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

    public DrivingSkill getSpecialSkill() {
        return specialSkill;
    }

    public void setSpecialSkill(DrivingSkill specialSkill) {
        this.specialSkill = specialSkill;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.surname);
        hash = 89 * hash + Objects.hashCode(this.nationality);
        hash = 89 * hash + Objects.hashCode(this.specialSkill);
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DriverCreateDTO other = (DriverCreateDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.nationality, other.nationality)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.specialSkill != other.specialSkill) {
            return false;
        }
        return true;
    }
    
}
