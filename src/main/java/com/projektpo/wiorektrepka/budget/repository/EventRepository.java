package com.projektpo.wiorektrepka.budget.repository;

import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("eventRepository")
public interface EventRepository extends JpaRepository<Event,Integer> {
    @Query("select e from Event e  where e.owner = ?0 and e.eventDate between ?1 and ?2")
    List<Event> findEventsByOwnerWhereEventDateBetween(User currentUser, String startDate, String endDate);
}