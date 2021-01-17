package com.daniel.rooms.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    private String name;

    @NotNull
    private String beginat;

    private String endat;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable( name = "professional_room",
        joinColumns = { @JoinColumn(name = "professional_id", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "room_id", referencedColumnName = "id") })
    private Room room;

    @ElementCollection
    private Set<String> dayofweekList = new HashSet<>();

    private boolean requiresSpecialtyRoom;

    private boolean isBusy;

    public Professional() { }

    public Professional(String name, String beginAt, String endat, Set<String> daysOfWeek, boolean requiresSpecialtyRoom) {
        this.name = name;
        this.beginat = beginAt;
        this.endat = endat;
        this.dayofweekList = daysOfWeek;
        this.requiresSpecialtyRoom = requiresSpecialtyRoom;
        this.isBusy = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long professionalid) {
        this.id = professionalid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeginat() {
        return beginat;
    }

    public void setBeginat(String beginat) {
        this.beginat = beginat;
    }

    public String getEndat() {
        return endat;
    }

    public void setEndat(String endat) {
        this.endat = endat;
    }

    public Set<String> getDayofweekList() {
        return dayofweekList;
    }

    public void setDayofweekList(Set<String> dayofweekList) {
        this.dayofweekList = dayofweekList;
    }

    public Boolean isRequiresSpecialtyRoom() {
        return requiresSpecialtyRoom;
    }

    public void setRequiresSpecialtyRoom(Boolean requiresSpecialtyRoom) {
        this.requiresSpecialtyRoom = requiresSpecialtyRoom;
    }

    public Boolean getIsBusy() {
        return isBusy;
    }

    public void setIsBusy(Boolean busy) {
        this.isBusy = busy;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Professional{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beginat='" + beginat + '\'' +
                ", endat='" + endat + '\'' +
                ", room=" + room +
                ", dayofweekList=" + dayofweekList +
                ", requiresSpecialtyRoom=" + requiresSpecialtyRoom +
                ", busy=" + isBusy +
                '}';
    }
}