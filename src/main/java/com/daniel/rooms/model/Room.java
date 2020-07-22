package com.daniel.rooms.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Entity
@Table( name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String roomName;

    @NotBlank
    private String unit;

    @NotNull
    private LocalTime openAt;

    @NotNull
    private LocalTime closeAt;

    @NotNull
    private boolean specialtyRoom;

    @OneToOne(mappedBy = "room")
    private Professional professional;

    public Room() { }

    public Room(String roomName, String unit, LocalTime openAt, LocalTime closeAt, boolean specialtyRoom, Professional professional) {
        this.roomName = roomName;
        this.unit = unit;
        this.openAt = openAt;
        this.closeAt = closeAt;
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

    public LocalTime getOpenAt() {
        return openAt;
    }

    public void setOpenAt(LocalTime openAt) {
        this.openAt = openAt;
    }

    public LocalTime getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(LocalTime closeAt) {
        this.closeAt = closeAt;
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
                ", openAt=" + openAt +
                ", closeAt=" + closeAt +
                ", specialtyRoom=" + specialtyRoom +
                ", professional=" + professional +
                '}';
    }
}
