package com.develop.lv4.controller;

import com.develop.lv4.dto.CalendarRequest;
import com.develop.lv4.dto.CalendarResponse;
import com.develop.lv4.entity.User;
import com.develop.lv4.service.CalendarService;
import com.develop.lv4.service.UserService;
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

    // 일정 생성
    @PostMapping
    public ResponseEntity<CalendarResponse> createCalendar(
            @RequestBody CalendarRequest request, @SessionAttribute(name = UserService.AUTH_SESSION) User user) {
        CalendarResponse response = calendarService.createCalendar(request, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 전체 일정 목록 조회
    @GetMapping
    public ResponseEntity<List<CalendarResponse>> getCalendars() {
        List<CalendarResponse> responses = calendarService.getCalendars();
        return ResponseEntity.ok(responses);
    }

    // 선택 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<CalendarResponse> getCalendar(@PathVariable Long id) {
        CalendarResponse response = calendarService.getCalendarById(id);
        return ResponseEntity.ok(response);
    }

    // 선택 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<CalendarResponse> updateCalendar(
            @PathVariable Long id, @RequestBody CalendarRequest request,
            @SessionAttribute(name = UserService.AUTH_SESSION) User user) {
        CalendarResponse response = calendarService.updateCalendar(id, request, user);
        return ResponseEntity.ok(response);
    }

    // 선택 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendar(
            @PathVariable Long id, @SessionAttribute(name = UserService.AUTH_SESSION) User user) {
        calendarService.deleteCalendar(id, user);
        return ResponseEntity.noContent().build();
    }
}