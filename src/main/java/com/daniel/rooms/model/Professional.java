package com.daniel.rooms.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(	name = "professional")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotNull
    private LocalTime beginat;

    @NotNull
    private LocalTime endat;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable( name = "professional_room",
        joinColumns = { @JoinColumn(name = "professional_id", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "room_id", referencedColumnName = "id") })
    private Room room;

    @ElementCollection
    private List<String> dayofweekList;

    private boolean requiresSpecialtyRoom;

    public Professional() { }

    public Professional(String name, LocalTime beginAt, LocalTime endat, List<String> daysOfWeek, boolean requiresSpecialtyRoom) {
        this.name = name;
        this.beginat = beginAt;
        this.endat = endat;
        this.dayofweekList = daysOfWeek;
        this.requiresSpecialtyRoom = requiresSpecialtyRoom;
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

    public LocalTime getBeginat() {
        return beginat;
    }

    public void setBeginat(LocalTime beginat) {
        this.beginat = beginat;
    }

    public LocalTime getEndat() {
        return endat;
    }

    public void setEndat(LocalTime endat) {
        this.endat = endat;
    }

    public List<String> getDayofweekList() {
        return dayofweekList;
    }

    public void setDayofweekList(List<String> dayofweekList) {
        this.dayofweekList = dayofweekList;
    }

    public boolean isRequiresSpecialtyRoom() {
        return requiresSpecialtyRoom;
    }

    public void setRequiresSpecialtyRoom(boolean requiresSpecialtyRoom) {
        this.requiresSpecialtyRoom = requiresSpecialtyRoom;
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
                "professionalid=" + id +
                ", name='" + name + '\'' +
                ", beginat=" + beginat +
                ", endat=" + endat +
                ", room=" + room +
                ", dayofweekList=" + dayofweekList +
                ", requiresSpecialtyRoom=" + requiresSpecialtyRoom +
                '}';
    }
}