package com.calendarapp.lv1.service;

import com.calendarapp.lv1.dto.CalendarResponse;
import com.calendarapp.lv1.entity.Calendar;
import com.calendarapp.lv1.dto.CalendarRequest;
import com.calendarapp.lv1.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarService {

    private final CalendarRepository calendarRepository;

    // Lv1: 일정 생성
    @Transactional
    public CalendarResponse createCalendar(CalendarRequest request) {
        Calendar calendar = new Calendar(
                request.getTitle(),
                request.getContent(),
                request.getAuthor(),
                request.getPassword()
        );

        Calendar savedCalendar = calendarRepository.save(calendar);
        return new CalendarResponse(savedCalendar);
    }
}
