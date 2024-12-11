package com.rxead.course.services;

import com.rxead.course.models.ModuleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {

    void delete(ModuleModel moduleModel);

    ModuleModel save(ModuleModel moduleModel);

    Optional<ModuleModel> findById(UUID courseId);

    Optional<ModuleModel> findModuleIntoCourse(UUID courseId, UUID moduleId);

    Page<ModuleModel> findAll(Pageable pageable);

    List<ModuleModel> findAllByCourse(UUID courseId);
}
