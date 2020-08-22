package com.daniel.rooms.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String roomName;

    @NotBlank
    private String unit;

    @NotNull
    private String openat;

    @NotNull
    private String closeat;

    @NotNull
    private boolean specialtyRoom;

    @OneToOne(mappedBy = "room")
    private Professional professional;

    public Room() { }

    public Room(String roomName, String unit, String openat, String closeat, boolean specialtyRoom, Professional professional) {
        this.roomName = roomName;
        this.unit = unit;
        this.openat = openat;
        this.closeat = closeat;
        this.specialtyRoom = specialtyRoom;
        this.professional = professional;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long roomId) {
        this.id = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOpenat() {
        return openat;
    }

    public void setOpenat(String openAt) {
        this.openat = openAt;
    }

    public String getCloseat() {
        return closeat;
    }

    public void setCloseat(String closeAt) {
        this.closeat = closeAt;
    }

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public boolean isSpecialtyRoom() {
        return specialtyRoom;
    }

    public void setSpecialtyRoom(boolean specialtyRoom) {
        this.specialtyRoom = specialtyRoom;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", unit='" + unit + '\'' +
                ", openAt=" + openat +
                ", closeAt=" + closeat +
                ", specialtyRoom=" + specialtyRoom +
                ", professional=" + professional +
                '}';
    }
}
