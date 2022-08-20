package one.appscale.relayclientapi.domain.csv;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;

public record CsvResource(InputStreamResource inputStreamResource,
                          String fileName) {
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");
        return headers;
    }
}
