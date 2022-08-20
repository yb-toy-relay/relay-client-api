package one.appscale.relayclientapi.domain.csv;

import java.util.List;

public interface Csv {
    List<String> headers();
    List<Object> body(String zoneId);
}
