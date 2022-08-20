package one.appscale.relayclientapi.api.support;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ErrorResponse {
    private final HttpStatus status;
    private final int code;
    private final List<ErrorMessage> messages;

    public ErrorResponse(final HttpStatus status,
                         final List<ErrorMessage> errors) {
        this.status = status;
        this.code = status.value();
        this.messages = errors;
    }

    public ErrorResponse(final HttpStatus status, final ErrorMessage error) {
        this.status = status;
        this.code = status.value();
        this.messages = List.of(error);
    }
}
