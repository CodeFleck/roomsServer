package com.daniel.rooms.service;

import com.daniel.rooms.model.Room;
import com.daniel.rooms.repository.RoomRepository;
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

    public Optional<Room> findByName(String roomName) {
        return roomRepository.findByRoomName(roomName);
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public void delete(Room room) {
        roomRepository.delete(room);
    }

    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
}
