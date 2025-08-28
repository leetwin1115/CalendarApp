package com.calendarapp.lv1.controller;

import com.calendarapp.lv1.dto.CalendarRequest;
import com.calendarapp.lv1.dto.CalendarResponse;
import com.calendarapp.lv1.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calendars")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping
    public ResponseEntity<CalendarResponse> createCalendar(@RequestBody CalendarRequest request) {
        CalendarResponse response = calendarService.createCalendar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
