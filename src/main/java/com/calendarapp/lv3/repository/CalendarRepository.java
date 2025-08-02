package com.calendarapp.lv3.repository;

import com.calendarapp.lv3.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    // 전체 일정 조회 (수정일 기준 내림차순)
    List<Calendar> findAllByOrderByModifiedAtDesc();

    // 작성자별 일정 조회 (수정일 기준 내림차순)
    List<Calendar> findByAuthorOrderByModifiedAtDesc(String author);

    // 작성자명으로 필터링 가능한 조회 (Optional)
    @Query("SELECT c FROM Calendar c WHERE (:author IS NULL OR c.author = :author) ORDER BY c.modifiedAt DESC")
    List<Calendar> findByAuthorWithOptional(@Param("author") String author);
}
