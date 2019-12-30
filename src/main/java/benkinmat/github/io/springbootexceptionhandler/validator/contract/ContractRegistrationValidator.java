package benkinmat.github.io.springbootexceptionhandler.validator.contract;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import benkinmat.github.io.springbootexceptionhandler.dto.request.ContractRegistrationRequest;

public class ContractRegistrationValidator implements ConstraintValidator<ContractRegistration, ContractRegistrationRequest> {

	@Override
	public boolean isValid(ContractRegistrationRequest value, ConstraintValidatorContext context) {
		if ((value.getStatus().equals("InActive") && value.getWorkspace() == null) || 
				(!value.getStatus().equals("InActive") && value.getWorkspace() != null))
			return true;
		return false;
	}

}
