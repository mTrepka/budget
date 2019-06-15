package com.projektpo.wiorektrepka.budget.repository;

import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository("eventRepository")
public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findAllByEventDateBetweenAndOwner(Date startDate, Date endDate, User user);
    Integer countEventsByEventDateBetweenAndOwner(Date startDate, Date endDate, User user);
}

