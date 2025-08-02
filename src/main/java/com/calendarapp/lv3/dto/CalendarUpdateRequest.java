package com.calendarapp.lv3.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CalendarUpdateRequest {
    private String title;
    private String author;
    private String password;
}
