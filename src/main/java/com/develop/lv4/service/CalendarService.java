package com.develop.lv4.service;

import com.develop.lv4.dto.CalendarRequest;
import com.develop.lv4.dto.CalendarResponse;
import com.develop.lv4.entity.Calendar;
import com.develop.lv4.entity.User;
import com.develop.lv4.repository.CalendarRepository;
import com.develop.lv4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarService {

    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    @Transactional
    public CalendarResponse createCalendar(CalendarRequest request, User user) {
        Calendar calendar = new Calendar(request, user);
        Calendar savedCalendar = calendarRepository.save(calendar);
        return new CalendarResponse(savedCalendar);
    }

    public List<CalendarResponse> getCalendars() {
        return calendarRepository.findAllByOrderByModifiedAtDesc().stream()
                .map(CalendarResponse::new)
                .collect(Collectors.toList());
    }

    public CalendarResponse getCalendarById(Long id) {
        Calendar calendar = findCalendarById(id);
        return new CalendarResponse(calendar);
    }

    @Transactional
    public CalendarResponse updateCalendar(Long id, CalendarRequest request, User user) {
        Calendar calendar = findCalendarById(id);

        // 일정 작성자인지 확인
        if (!Objects.equals(calendar.getUser().getId(), user.getId())) {
            throw new IllegalArgumentException("일정 작성자만 수정할 수 있습니다.");
        }

        calendar.update(request);
        return new CalendarResponse(calendar);
    }

    @Transactional
    public void deleteCalendar(Long id, User user) {
        Calendar calendar = findCalendarById(id);

        if (!Objects.equals(calendar.getUser().getId(), user.getId())) {
            throw new IllegalArgumentException("일정 작성자만 삭제할 수 있습니다.");
        }

        calendarRepository.delete(calendar);
    }

    private Calendar findCalendarById(Long id) {
        return calendarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("선택한 일정을 찾을 수 없습니다. ID: " + id));
    }
}