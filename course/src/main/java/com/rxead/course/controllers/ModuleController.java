package com.rxead.course.controllers;

import com.rxead.course.dtos.CourseDto;
import com.rxead.course.dtos.ModuleDto;
import com.rxead.course.models.CourseModel;
import com.rxead.course.models.ModuleModel;
import com.rxead.course.services.CourseService;
import com.rxead.course.services.ModuleService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses/{courseId}/modules")
        public ResponseEntity<List<ModuleModel>> getAllModules(@PathVariable(value = "courseId") UUID courseId){
        return ResponseEntity.ok().body(moduleService.findAllByCourse(courseId));
    }

    @GetMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> getOneModule(@PathVariable(value = "courseId") UUID courseId,
                                               @PathVariable (value = "moduleId") UUID moduleId){

        Optional<ModuleModel> moduleModelOptional = moduleService.findModuleIntoCourse(courseId, moduleId);
        if (!moduleModelOptional.isPresent() ) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module Not Found for this course.");

        return ResponseEntity.ok().body(moduleModelOptional.get());
    }

    @PostMapping("/courses/{courseId}/modules")
    public ResponseEntity<Object> saveModule(@PathVariable(value = "courseId") UUID courseId, @RequestBody @Valid ModuleDto moduleDto){

        Optional<CourseModel> courseModel = courseService.findById(courseId);
        if (!courseModel.isPresent()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found.");

        var moduleModel = new ModuleModel();
        BeanUtils.copyProperties(moduleDto, moduleModel);
        moduleModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        moduleModel.setCourse(courseModel.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.save(moduleModel));
    }
    @DeleteMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> deleteModule(@PathVariable(value = "courseId") UUID courseId,
                                               @PathVariable(value = "moduleId") UUID moduleId){
        Optional<ModuleModel> moduleModelOptional = moduleService.findModuleIntoCourse(courseId, moduleId);
        if (!moduleModelOptional.isPresent() ) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module Not Found for this course.");
        moduleService.delete(moduleModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Module deleted successfully.");
    }
    @PutMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> updateModule(@PathVariable(value = "courseId") UUID courseId,
                                               @PathVariable (value = "moduleId") UUID moduleId,
                                               @RequestBody @Valid ModuleDto moduleDto){
        Optional<ModuleModel> moduleModelOptional = moduleService.findModuleIntoCourse(courseId, moduleId);
        if (!moduleModelOptional.isPresent() ) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module Not Found for this course.");

        var moduleModel = moduleModelOptional.get();
        moduleModel.setTitle(moduleDto.getTitle());
        moduleModel.setDescription(moduleDto.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.save(moduleModel));
    }

}
