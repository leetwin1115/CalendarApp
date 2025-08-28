package com.calendarapp.lv3.controller;

import com.calendarapp.lv3.dto.CalendarRequest;
import com.calendarapp.lv3.dto.CalendarResponse;
import com.calendarapp.lv3.dto.CalendarUpdateRequest;
import com.calendarapp.lv3.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendars")
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

    // Lv3: 선택 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<CalendarResponse> updateCalendar(
            @PathVariable Long id,
            @RequestBody CalendarUpdateRequest request) {
        CalendarResponse response = calendarService.updateCalendar(id, request);
        return ResponseEntity.ok(response);
    }

}
