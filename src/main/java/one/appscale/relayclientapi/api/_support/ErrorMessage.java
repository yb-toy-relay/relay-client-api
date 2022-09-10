package one.appscale.relayclientapi.api._support;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
public class ErrorMessage {
    @JsonInclude(NON_NULL)
    private String path;
    @JsonInclude(NON_NULL)
    private String detail;

    public ErrorMessage(final String path, final String detail) {
        this.path = path;
        this.detail = detail;
    }

    public ErrorMessage(final String detail) {
        this.detail = detail;
    }
}
