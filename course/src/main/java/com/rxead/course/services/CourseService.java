package com.rxead.course.services;

import com.rxead.course.models.CourseModel;

import java.util.Optional;
import java.util.UUID;

public interface CourseService {

    void delete(CourseModel courseModel);

    CourseModel save(CourseModel courseDto);

    Optional<CourseModel> findById(UUID courseId);
}
