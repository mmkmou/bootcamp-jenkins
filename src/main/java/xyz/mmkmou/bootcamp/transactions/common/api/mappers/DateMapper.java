package xyz.mmkmou.bootcamp.transactions.common.api.mappers;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class DateMapper {
    public OffsetDateTime asOffsetDateTime(Timestamp ts) {
        OffsetDateTime _return = null;
        if (ts != null)
            _return = OffsetDateTime.of(ts.toLocalDateTime().getYear(), ts.toLocalDateTime().getMonthValue(),
                    ts.toLocalDateTime().getDayOfMonth(), ts.toLocalDateTime().getHour(),
                    ts.toLocalDateTime().getMinute(), ts.toLocalDateTime().getSecond(),
                    ts.toLocalDateTime().getNano(), ZoneOffset.UTC);

        return _return;
    }

    public Timestamp asTimestamp(OffsetDateTime offsetDateTime) {
        Timestamp _return = null;

        if (offsetDateTime != null) _return = Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());

        return _return;
    }
}
