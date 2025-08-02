package com.calendarapp.lv2.controller;

import com.calendarapp.lv2.dto.CalendarRequest;
import com.calendarapp.lv2.dto.CalendarResponse;
import com.calendarapp.lv2.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendars")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    // Lv1: 일정 생성
    @PostMapping
    public ResponseEntity<CalendarResponse> createCalendar(@RequestBody CalendarRequest request) {
        CalendarResponse response = calendarService.createCalendar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Lv2: 전체/작성자별 일정 목록 조회
    @GetMapping
    public ResponseEntity<List<CalendarResponse>> getCalendars(
            @RequestParam(required = false) String author) {
        List<CalendarResponse> responses = calendarService.getCalendars(author);
        return ResponseEntity.ok(responses);
    }

    // Lv2: 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<CalendarResponse> getCalendar(@PathVariable Long id) {
        CalendarResponse response = calendarService.getCalendarById(id);
        return ResponseEntity.ok(response);
    }

}
