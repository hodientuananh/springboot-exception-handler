package benkinmat.github.io.springbootexceptionhandler.validator.contract;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Constraint(validatedBy = ContractRegistrationValidator.class)
public @interface ContractRegistration {
	String message() default "contract-registration-invalid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
