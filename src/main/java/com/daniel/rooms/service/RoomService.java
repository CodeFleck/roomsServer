package com.daniel.rooms.service;

import com.daniel.rooms.model.Professional;
import com.daniel.rooms.model.Room;
import com.daniel.rooms.repository.RoomRepository;
import com.daniel.rooms.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }

    public Optional<Room> findById(Long roomId) {
        return roomRepository.findById(roomId);
    }

    public Room save(Room room) {
        room.setOpenat(TimeUtil.getTimeFromDate(room.getOpenat()));
        room.setCloseat(TimeUtil.getTimeFromDate(room.getCloseat()));
        return roomRepository.save(room);
    }

    public Room updateRoom(Room partialUpdate, Room room) {
        room.setSpecialtyRoom(partialUpdate.isSpecialtyRoom());
        return roomRepository.save(room);
    }

    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
}
