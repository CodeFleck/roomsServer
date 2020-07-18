package com.daniel.rooms.repository;

import com.daniel.rooms.model.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Long> {

	Optional<DayOfWeek> findByName(String name);
}
