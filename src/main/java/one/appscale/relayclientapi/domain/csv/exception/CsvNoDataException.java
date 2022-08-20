package one.appscale.relayclientapi.domain.csv.exception;


import one.appscale.relayclientapi.common.exception.AbstractClientException;

import java.io.Serial;

public class CsvNoDataException extends AbstractClientException {
    @Serial
    private static final long serialVersionUID = 532231404357695577L;

    public CsvNoDataException() {
        super("No Data");
    }
}
