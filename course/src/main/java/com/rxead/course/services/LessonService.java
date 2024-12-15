package com.rxead.course.services;

import com.rxead.course.models.LessonModel;
import com.rxead.course.models.ModuleModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonService {

    void delete(LessonModel lessonModel);

    LessonModel save(LessonModel lessonModel);

    Optional<LessonModel> findLessonIntoModule(UUID moduleId, UUID lessonId);

    List<LessonModel> findAllByModule(UUID moduleId);
}
