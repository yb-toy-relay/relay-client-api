package one.appscale.relayclientapi.api.csv;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.api.csv.CsvDownloadRequest;
import one.appscale.relayclientapi.domain.csv.CsvData;
import one.appscale.relayclientapi.domain.csv.CsvResource;
import one.appscale.relayclientapi.domain.csv.CsvService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/relay/v1/callback")
public class CsvDownloadController {
    private final CsvService csvService;

    @GetMapping(value = "/csv", produces = "text/csv")
    public ResponseEntity<Resource> exportCsv(@Valid final CsvDownloadRequest request) {
        final CsvData csvData = csvService.getCsvDataBy(request.toActivityLogSearchQuery());
        final CsvResource csvResource = csvService.getCsvResource(csvData);
        return new ResponseEntity<>(csvResource.inputStreamResource(),
                                    csvResource.httpHeaders(),
                                    HttpStatus.OK);
    }
}
