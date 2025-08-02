package com.calendarapp.lv4.service;

import com.calendarapp.lv4.dto.CalendarRequest;
import com.calendarapp.lv4.dto.CalendarResponse;
import com.calendarapp.lv4.dto.CalendarUpdateRequest;
import com.calendarapp.lv4.entity.Calendar;
import com.calendarapp.lv4.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    // Lv2: 전체/작성자별 일정 목록 조회
    public List<CalendarResponse> getCalendars(String author) {
        List<Calendar> calendars;

        if (author != null && !author.trim().isEmpty()) {
            calendars = calendarRepository.findByAuthorOrderByModifiedAtDesc(author);
        } else {
            calendars = calendarRepository.findAllByOrderByModifiedAtDesc();
        }

        return calendars.stream()
                .map(CalendarResponse::new)
                .collect(Collectors.toList());
    }

    // Lv2: 선택 일정 조회
    public CalendarResponse getCalendarById(Long id) {
        Calendar calendar = findCalendarById(id);
        return new CalendarResponse(calendar);
    }

    // Lv3: 선택 일정 수정
    @Transactional
    public CalendarResponse updateCalendar(Long id, CalendarUpdateRequest request) {
        Calendar calendar = findCalendarById(id);

        // 비밀번호 확인
        if (!calendar.isPasswordMatch(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        calendar.update(request.getTitle(), request.getAuthor());
        return new CalendarResponse(calendar);
    }

    // Lv4: 선택 일정 삭제
    @Transactional
    public void deleteCalendar(Long id, String password) {
        Calendar calendar = findCalendarById(id);

        // 비밀번호 확인
        if (!calendar.isPasswordMatch(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        calendarRepository.delete(calendar);
    }

    // 공통 메서드: ID로 일정을 찾고 없으면 예외 발생
    private Calendar findCalendarById(Long id) {
        return calendarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 일정을 찾을 수 없습니다. ID: " + id));
    }
}
