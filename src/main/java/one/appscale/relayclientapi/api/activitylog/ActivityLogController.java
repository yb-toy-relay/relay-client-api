package one.appscale.relayclientapi.api.activitylog;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.appscale.relayclientapi.api._validator.activitylogrequest.ActivityLogRequestConstraint;
import one.appscale.relayclientapi.common.support.TraceIdUtils;
import one.appscale.relayclientapi.domain.apikey.ApiKeyService;
import one.appscale.relayclientapi.domain.csv.CsvService;
import one.appscale.relayclientapi.infra.kafka.producer.ActivityLogRequestProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ActivityLogController {
    private final ApiKeyService apiKeyService;
    private final ActivityLogRequestProducer producer;
    private final CsvService csvService;

    @PostMapping(value = "/relay/v1/activity-log/csv")
    public ResponseEntity<Object> produceCsvRequest(@Valid
                                                    @ActivityLogRequestConstraint
                                                    @RequestBody final ActivityLogRequest request) {
        final String traceId = TraceIdUtils.create();
        log.info("[activity-log][{}] request:{}", traceId, request);

        apiKeyService.checkValidRequest(request.apiKey(), request.appToken(), request.email());

        if (!csvService.hasData(request.toActivityLogSearchQuery(traceId))) {
            log.info("[activity-log][{}] no content", traceId);
            return ResponseEntity.noContent().build();
        }
        producer.sendCsvRequest(request.toActivityLogCsvRequest(traceId));
        return ResponseEntity.ok().build();
    }
}
