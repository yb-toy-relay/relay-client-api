package one.appscale.relayclientapi.domain.csv;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogProvider;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogProviderFactory;
import one.appscale.relayclientapi.domain.activitylog.ActivityLogSearchQuery;
import one.appscale.relayclientapi.domain.csv.exception.CsvException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvService {
    private final ActivityLogProviderFactory factory;

    public boolean hasData(final ActivityLogSearchQuery searchQuery) {
        final ActivityLogProvider provider = factory.getProvider(searchQuery.activityKind());
        return provider.hasData(searchQuery);
    }

    private CsvData getCsvDataBy(final ActivityLogSearchQuery searchQuery) {
        final ActivityLogProvider provider = factory.getProvider(searchQuery.activityKind());
        return provider.getCsvData(searchQuery);
    }

    public CsvResource getCsvResource(final ActivityLogSearchQuery searchQuery) {
        final CsvData csvData = getCsvDataBy(searchQuery);
        final CSVFormat csvFormat = CSVFormat.Builder.create()
                                                     .setHeader(csvData.getHeaders())
                                                     .build();
        final List<List<Object>> bodies = csvData.getBodies();
        final CsvMetadata csvMetadata = csvData.getCsvMetadata();
        try (
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), csvFormat)
        ) {
            for (List<Object> body : bodies) {
                csvPrinter.printRecord(body);
            }
            csvPrinter.flush();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
            final InputStreamResource inputStreamResource = new InputStreamResource(byteArrayInputStream);
            return new CsvResource(inputStreamResource.getInputStream(), csvMetadata);
        } catch (IOException e) {
            throw new CsvException(e.getMessage());
        }
    }
}
