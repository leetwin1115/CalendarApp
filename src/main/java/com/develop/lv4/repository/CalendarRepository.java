package com.develop.lv4.repository;

import com.develop.lv4.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findAllByOrderByModifiedAtDesc();

    List<Calendar> findByAuthorOrderByModifiedAtDesc(String author);

    @Query("SELECT c FROM Calendar c WHERE (:author IS NULL OR c.author = :author) ORDER BY c.modifiedAt DESC")
    List<Calendar> findByAuthorWithOptional(@Param("author") String author);
}
