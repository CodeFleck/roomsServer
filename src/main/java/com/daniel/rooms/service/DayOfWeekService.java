package com.daniel.rooms.service;

import com.daniel.rooms.model.DayOfWeek;
import com.daniel.rooms.repository.DayOfWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DayOfWeekService {

    private DayOfWeekRepository dayOfWeekRepository;

    @Autowired
    public void setDayOfWeekRepository(DayOfWeekRepository dayOfWeekRepository) {
        this.dayOfWeekRepository = dayOfWeekRepository;
    }

    public List<DayOfWeek> findAll() {
        List<DayOfWeek> daysOfWeek = dayOfWeekRepository.findAll();
        return daysOfWeek;
    }

    public Optional<DayOfWeek> findById(Long dayOfWeekId) {
        return dayOfWeekRepository.findById(dayOfWeekId);
    }

    public Optional<DayOfWeek> findByName(String name) {
        return dayOfWeekRepository.findByName(name);
    }

    public DayOfWeek save(DayOfWeek dayOfWeek) {
        return dayOfWeekRepository.save(dayOfWeek);
    }

    public void delete(DayOfWeek dayOfWeek) {
        dayOfWeekRepository.delete(dayOfWeek);
    }

    public void deleteById(Long id) {
        dayOfWeekRepository.deleteById(id);
    }
}
