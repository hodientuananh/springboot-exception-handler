package benkinmat.github.io.springbootexceptionhandler.dto.request;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkSpaceRequest {

	@NotNull(message = "os-required")
	private String os;
	
	@NotNull(message = "ide-required")
	private String ide;
	
	@NotNull(message = "code-required")
	private String code;
	
	@Valid
	private Set<ProjectRequest> projects;
	
}
