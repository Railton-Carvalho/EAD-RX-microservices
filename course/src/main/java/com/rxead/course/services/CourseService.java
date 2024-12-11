package com.rxead.course.services;

import com.rxead.course.models.CourseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CourseService {

    void delete(CourseModel courseModel);

    CourseModel save(CourseModel courseDto);

    Optional<CourseModel> findById(UUID courseId);

    Page<CourseModel> findAll(Pageable pageable);
}
