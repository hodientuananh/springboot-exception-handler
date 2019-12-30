package benkinmat.github.io.springbootexceptionhandler.error.contract;

import org.springframework.stereotype.Component;

import benkinmat.github.io.springbootexceptionhandler.error.ErrorCode;
import benkinmat.github.io.springbootexceptionhandler.error.ExceptionToErrorCode;
import benkinmat.github.io.springbootexceptionhandler.error.ServiceException;

class ContractRegistrationExceptionMapper {
    @Component
    static class GeeksAlreadyExceptionToErrorCode implements ExceptionToErrorCode {

        @Override
        public ErrorCode toErrorCode(ServiceException exception) {
            return exception.getErrorCode();
        }
    }
}