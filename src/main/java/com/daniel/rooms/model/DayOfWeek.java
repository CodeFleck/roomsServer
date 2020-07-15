package com.daniel.rooms.model;

import javax.persistence.*;

@Entity
@Table(name = "dayofweek")
public class DayOfWeek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dayofweekid;

    private String name;

    public DayOfWeek() {}

    public DayOfWeek(String name) {
        this.name = name;
    }

    public Long getId() {
        return dayofweekid;
    }

    public void setId(Long id) {
        this.dayofweekid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DayOfWeek{" +
                "id=" + dayofweekid +
                ", name='" + name + '\'' +
                '}';
    }
}
