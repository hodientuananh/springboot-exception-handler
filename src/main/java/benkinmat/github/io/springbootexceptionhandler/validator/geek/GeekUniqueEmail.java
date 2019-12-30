package benkinmat.github.io.springbootexceptionhandler.validator.geek;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = GeekUniqueEmailValidator.class)
public @interface GeekUniqueEmail {
	String message() default "geek-email-exist";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
