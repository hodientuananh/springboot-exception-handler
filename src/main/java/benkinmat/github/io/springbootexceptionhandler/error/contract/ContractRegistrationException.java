package benkinmat.github.io.springbootexceptionhandler.error.contract;

import org.springframework.http.HttpStatus;

import benkinmat.github.io.springbootexceptionhandler.error.ErrorCode;
import benkinmat.github.io.springbootexceptionhandler.error.ServiceException;

public class ContractRegistrationException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private Object[] params;
	
	public ContractRegistrationException(String code, Object[] params) {
		this.code = code;
		this.params = params;
	}

	@Override
	public ErrorCode getErrorCode() {
		return new ContractRegistrationErrorCode(this.code, HttpStatus.BAD_REQUEST, this.params);
	}
}
