package benkinmat.github.io.springbootexceptionhandler.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import benkinmat.github.io.springbootexceptionhandler.validator.contract.ContractRegistration;
import benkinmat.github.io.springbootexceptionhandler.validator.geek.GeekUniqueEmail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeekRegistrationRequest {

	@NotNull(message = "first-name-required")
	private String firstName;

	@NotNull(message = "last-name-required")
	private String lastName;

	@NotNull(message = "phone-required")
	@Pattern(regexp = "^[0]\\d{9}$", message = "phone-pattern")
	private String phone;

	@Pattern(regexp = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$", message = "email-invalid")
	@GeekUniqueEmail
	private String email;

	@NotNull(message = "user-id-required")
	@Pattern(regexp = "^((\\d{12})|(\\d{9})|([a-zA-z]{1}\\d{8}))$", message = "user-id-pattern")
	private String userId;
	
	@NotNull(message = "status-required")
	private String status;
	
}
