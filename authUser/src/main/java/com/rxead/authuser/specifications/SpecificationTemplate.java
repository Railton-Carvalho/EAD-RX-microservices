package com.rxead.authuser.specifications;

import com.rxead.authuser.models.UserModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationTemplate {

    @And({
          @Spec(path = "email", spec = Like.class),
          @Spec(path = "userStatus", spec = Equal.class),
          @Spec(path = "userType", spec = Equal.class),
    })
    public interface UserSpec extends Specification<UserModel> {

    }

}
