package com.calendarapp.lv1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CalendarRequest {
    private String title;
    private String content;
    private String author;
    private String password;
}
