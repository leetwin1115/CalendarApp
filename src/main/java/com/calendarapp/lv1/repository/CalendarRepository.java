package com.calendarapp.lv1.repository;

import com.calendarapp.lv1.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
