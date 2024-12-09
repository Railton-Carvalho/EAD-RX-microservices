package com.rxead.course.services.impl;

import com.rxead.course.repositories.LessonRepository;
import com.rxead.course.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

}
