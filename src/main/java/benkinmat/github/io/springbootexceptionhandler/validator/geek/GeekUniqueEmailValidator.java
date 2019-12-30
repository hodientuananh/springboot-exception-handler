package benkinmat.github.io.springbootexceptionhandler.validator.geek;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import benkinmat.github.io.springbootexceptionhandler.model.Geek;
import benkinmat.github.io.springbootexceptionhandler.repository.GeekRepository;

public class GeekUniqueEmailValidator implements ConstraintValidator<GeekUniqueEmail, String> {
	
	@Autowired
	GeekRepository geekRepository;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (email == null)
			return true;
		else {
			Optional<Geek> geek = Optional.ofNullable(geekRepository.findByEmail(email));
			return !geek.isPresent();
		}	
	}

}
