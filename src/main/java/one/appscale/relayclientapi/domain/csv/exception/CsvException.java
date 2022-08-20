package one.appscale.relayclientapi.domain.csv.exception;

import one.appscale.relayclientapi.common.exception.AbstractServerException;

import java.io.Serial;

public class CsvException extends AbstractServerException {
    @Serial
    private static final long serialVersionUID = 8246722360103356691L;

    public CsvException(final String reason) {
        super(reason);
    }
}
