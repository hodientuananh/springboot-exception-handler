package benkinmat.github.io.springbootexceptionhandler.error;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String ERROR_CODE_FOR_UNKNOWN_ERROR = "unknown";

    String code();
    
    Object[] params();

    HttpStatus httpStatus();

    enum UnknownErrorCode implements ErrorCode {
        INSTANCE;

        @Override
        public String code() {
            return ERROR_CODE_FOR_UNKNOWN_ERROR;
        }

        @Override
        public HttpStatus httpStatus() {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

		@Override
		public Object[] params() {
			// TODO Auto-generated method stub
			return new Object[] {};
		}
    }
}