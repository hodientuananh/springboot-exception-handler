package benkinmat.github.io.springbootexceptionhandler.error;

import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ErrorCodes {
    private final ApplicationContext context;

    ErrorCodes(ApplicationContext context) {
        this.context = context;
    }

    public ErrorCode of(ServiceException exception) {
        return implementations()
                .findFirst()
                .map(impl -> impl.toErrorCode(exception))
                .orElse(ErrorCode.UnknownErrorCode.INSTANCE);
    }

    private Stream<ExceptionToErrorCode> implementations() {
        return context.getBeansOfType(ExceptionToErrorCode.class).values().stream();
    }
}