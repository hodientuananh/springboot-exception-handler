package benkinmat.github.io.springbootexceptionhandler.config;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import benkinmat.github.io.springbootexceptionhandler.error.ErrorCode;
import benkinmat.github.io.springbootexceptionhandler.error.ErrorCodes;
import benkinmat.github.io.springbootexceptionhandler.error.ErrorResponse;
import benkinmat.github.io.springbootexceptionhandler.error.ErrorResponse.ApiError;
import benkinmat.github.io.springbootexceptionhandler.error.contract.ContractRegistrationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApiErrorHandler {
	private static final String NO_MESSAGE_AVAILABLE = "No message available";
	
	@Autowired
	ErrorCodes errorCodes;

	@Autowired
	MessageSource apiErrorMessageSource;
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request,
			Locale locale) {
		Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

		List<ErrorResponse.ApiError> apiErrors = constraintViolations.stream()
				.map(constraintViolation -> constraintViolation.getMessage()).map(this::validationErrorCode)
				.map(code -> toApiError(code, locale)).collect(toList());
		return new ResponseEntity<Object>(ErrorResponse.ofErrors(HttpStatus.BAD_REQUEST, apiErrors), new HttpHeaders(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	ResponseEntity<ErrorResponse> handleServerExceptions(Exception ex, Locale locale) {
		log.error(ex.getMessage(), ex);
		ErrorCode errorCode = new ErrorCode() {

			@Override
			public Object[] params() {
				return new Object[] { ex.getMessage() };
			}

			@Override
			public HttpStatus httpStatus() {
				return HttpStatus.INTERNAL_SERVER_ERROR;
			}

			@Override
			public String code() {
				return "internal-server-error";
			}
		};
		ErrorResponse errorResponse = ErrorResponse.of(errorCode.httpStatus(), toApiError(errorCode, locale));

		return ResponseEntity.status(errorCode.httpStatus()).body(errorResponse);
	}

	@ExceptionHandler(ContractRegistrationException.class)
	ResponseEntity<ErrorResponse> handleServiceExceptions(ContractRegistrationException exception, Locale locale) {
		ErrorCode errorCode = errorCodes.of(exception);
		ErrorResponse errorResponse = ErrorResponse.of(errorCode.httpStatus(), toApiError(errorCode, locale));

		return ResponseEntity.status(errorCode.httpStatus()).body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception,
			Locale locale) {
		Stream<ObjectError> errors = exception.getBindingResult().getAllErrors().stream();
		List<ApiError> apiErrors = errors.map(ObjectError::getDefaultMessage).map(this::validationErrorCode)
				.map(code -> toApiError(code, locale)).collect(toList());

		return ResponseEntity.badRequest().body(ErrorResponse.ofErrors(HttpStatus.BAD_REQUEST, apiErrors));
	}

	private ApiError toApiError(ErrorCode errorCode, Locale locale) {
		String message;
		try {
			message = apiErrorMessageSource.getMessage(errorCode.code(), errorCode.params(), locale);
		} catch (NoSuchMessageException e) {
			log.error("Couldn't find any message for {} code under {} locale", errorCode.code(), locale);
			message = NO_MESSAGE_AVAILABLE;
		}

		return new ApiError(errorCode.code(), message);
	}

	private ErrorCode validationErrorCode(final String errorCode) {
		return new ErrorCode() {
			@Override
			public String code() {
				return errorCode;
			}

			@Override
			public HttpStatus httpStatus() {
				return HttpStatus.BAD_REQUEST;
			}

			@Override
			public Object[] params() {
				return null;
			}
		};
	}
}
