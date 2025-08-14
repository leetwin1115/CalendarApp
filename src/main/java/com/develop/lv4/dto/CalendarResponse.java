package com.develop.lv4.dto;

import com.develop.lv4.entity.Calendar;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String username; // author -> username
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CalendarResponse(Calendar calendar) {
        this.id = calendar.getId();
        this.title = calendar.getTitle();
        this.content = calendar.getContent();
        this.username = calendar.getUser().getUsername();
        this.createdAt = calendar.getCreatedAt();
        this.modifiedAt = calendar.getModifiedAt();
    }
}