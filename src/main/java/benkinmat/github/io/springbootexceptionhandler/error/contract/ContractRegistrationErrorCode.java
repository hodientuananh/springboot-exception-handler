package benkinmat.github.io.springbootexceptionhandler.error.contract;

import org.springframework.http.HttpStatus;

import benkinmat.github.io.springbootexceptionhandler.error.ErrorCode;

public class ContractRegistrationErrorCode implements ErrorCode {
	private final String code;
    private final HttpStatus httpStatus;
    private final Object[] params;

    public ContractRegistrationErrorCode(String code, HttpStatus httpStatus, Object[] params) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.params = params;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

	@Override
	public Object[] params() {
		return params;
	}
}