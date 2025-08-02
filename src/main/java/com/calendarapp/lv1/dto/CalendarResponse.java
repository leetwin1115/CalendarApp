package com.calendarapp.lv1.dto;

import com.calendarapp.lv1.entity.Calendar;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CalendarResponse(Calendar calendar) {
        this.id = calendar.getId();
        this.title = calendar.getTitle();
        this.content = calendar.getContent();
        this.author = calendar.getAuthor();
        this.createdAt = calendar.getCreatedAt();
        this.modifiedAt = calendar.getModifiedAt();
    }
}
