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
    private Long roomid;

    @NotBlank
    private String roomname;

    @NotBlank
    private Long unitid;

    @NotNull
    private LocalTime openat;

    @NotNull
    private LocalTime closeat;

    @NotNull
    private boolean isspecialtyroom;

    public Room() { }

    public Room(String roomName, Long unitid, LocalTime openAt, LocalTime closeAt, boolean isSpecialtyRoom) {
        this.roomname = roomName;
        this.unitid = unitid;
        this.openat = openAt;
        this.closeat = closeAt;
        this.isspecialtyroom = isSpecialtyRoom;
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomId) {
        this.roomid = roomId;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomName) {
        this.roomname = roomName;
    }

    public Long getUnitid() {
        return unitid;
    }

    public void setUnitid(Long unitId) {
        this.unitid = unitId;
    }

    public LocalTime getOpenat() {
        return openat;
    }

    public void setOpenat(LocalTime openAt) {
        this.openat = openAt;
    }

    public LocalTime getCloseat() {
        return closeat;
    }

    public void setCloseat(LocalTime closeAt) {
        this.closeat = closeAt;
    }

    public boolean isIsspecialtyroom() {
        return isspecialtyroom;
    }

    public void setIsspecialtyroom(boolean isspecialtyroom) {
        this.isspecialtyroom = isspecialtyroom;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomid +
                ", roomName='" + roomname + '\'' +
                ", unitId=" + unitid +
                ", openAt=" + openat +
                ", closeAt=" + closeat +
                ", isSpecialtyRoom=" + isspecialtyroom +
                '}';
    }
}
