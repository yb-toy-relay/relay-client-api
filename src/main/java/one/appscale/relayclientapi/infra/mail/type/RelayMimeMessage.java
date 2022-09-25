package one.appscale.relayclientapi.infra.mail.type;

import java.util.Map;

public interface RelayMimeMessage {
    String to();

    String templateName();

    String title();

    Map<String, String> variables();
}
