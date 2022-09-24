package one.appscale.relayclientapi.api.activitylog;

import lombok.RequiredArgsConstructor;
import one.appscale.relayclientapi.domain.apikey.ApiKeyService;
import one.appscale.relayclientapi.infra.kafka.ActivityLogRequestProducer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
public class ActivityLogController {
    private final ApiKeyService apiKeyService;
    private final ActivityLogRequestProducer producer;

    @PostMapping(value = "/relay/v1/activity-log/csv")
    public void produceCsvRequest(@Valid @RequestBody final ActivityLogRequest request) {
        apiKeyService.checkValidRequest(request.apiKey(), request.appToken());
        producer.sendCsvRequest(request.toActivityLogCsvRequest());
    }
}
