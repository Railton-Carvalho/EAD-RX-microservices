package com.rxead.course.specification;

import com.rxead.course.models.CourseModel;
import com.rxead.course.models.LessonModel;
import com.rxead.course.models.ModuleModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.UUID;

public class SpecificationTemplate {

    @And({
            @Spec(path = "courseLevel", spec = Equal.class),
            @Spec(path = "courseStatus", spec = Equal.class),
            @Spec(path = "name", spec = Like.class)
    })
    public interface CourseSpec extends Specification<CourseModel>{}


    @Spec(path = "title", spec = Like.class)
    public interface ModuleSpec extends Specification<ModuleModel> {}

    @Spec(path = "title", spec = Like.class)
    public interface LessonSpec extends Specification<LessonModel> {}

    public static Specification<ModuleModel> moduleCourseId(final UUID couseId){
        return ((root, criteriaQuery, criteriaBuilder) -> {
           criteriaQuery.distinct(true);
           Root<ModuleModel> module = root;
           Root<CourseModel> course = criteriaQuery.from(CourseModel.class);

           Expression<Collection<ModuleModel>> coursesModules = course.get("modules");

           return criteriaBuilder.and(criteriaBuilder.equal(course.get("courseId"), couseId), criteriaBuilder.isMember(module, coursesModules));
        });
    }

    public static Specification<LessonModel> lessonModuleId(final UUID moduleId){
        return ((root, criteriaQuery, criteriaBuilder) -> {
           criteriaQuery.distinct(true);
           Root<LessonModel> lesson = root;
           Root<ModuleModel> module = criteriaQuery.from(ModuleModel.class);

           Expression<Collection<ModuleModel>> moduleLessons = module.get("lessons");

           return criteriaBuilder.and(criteriaBuilder.equal(module.get("moduleId"), moduleId), criteriaBuilder.isMember(module, moduleLessons));
        });
    }
}
