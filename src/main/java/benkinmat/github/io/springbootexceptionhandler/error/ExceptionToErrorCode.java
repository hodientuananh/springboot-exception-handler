package benkinmat.github.io.springbootexceptionhandler.error;

public interface ExceptionToErrorCode {
	
//    boolean canHandle(ServiceException exception);

    ErrorCode toErrorCode(ServiceException exception);
}