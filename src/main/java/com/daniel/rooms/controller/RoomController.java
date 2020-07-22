package com.daniel.rooms.controller;

import com.daniel.rooms.model.Room;
import com.daniel.rooms.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/rooms")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public List<Room> findAll() {
        return roomService.findAll();
    }

    @PostMapping("/")
    public Room newRoom(@RequestBody Room room) {
        return roomService.save(room);
    }

    @GetMapping("/{id}")
    public Room findById(@PathVariable Long id) {
        return roomService.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find any Room with id " + id));
    }

    @GetMapping("/{name}")
    public Room findByName(@PathVariable String name) {
        return roomService.findByName(name)
                .orElseThrow(() -> new RuntimeException("Could not find any Room with the name " + name));
    }

    @PutMapping("/{id}")
    public Room replaceRoom(@RequestBody Room newRoom, @PathVariable Long id) {

        return roomService.findById(id)
                .map(room -> {
                    room.setRoomName(newRoom.getRoomName());
                    room.setOpenAt(newRoom.getOpenAt());
                    room.setCloseAt(newRoom.getCloseAt());
                    room.setSpecialtyRoom(newRoom.isSpecialtyRoom());
                    room.setUnit(newRoom.getUnit());
                    return roomService.save(room);
                })
                .orElseGet(() -> {
                    return roomService.save(newRoom);
                });
    }

    @DeleteMapping("/{id}")
    void deleteRoom(@PathVariable Long id) {
        roomService.deleteById(id);
    }
}
