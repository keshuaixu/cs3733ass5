package observers;

import javafx.scene.control.Label;
import models.Time;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * An observer class which handles converting EST to  number of seconds since January 1, 1970 at midnight (0:00). EST.
 * Note that this is NOT quite the same as Unix epoch time.
 */
public class ObsJan1970EST extends AbsTimeZoneObserver {

    /**
     * Constructor; Should set the time zone for the calendar object
     *
     * @param time  Time object we are listening to
     * @param label Label to display this observers information
     */
    public ObsJan1970EST(Time time, Label label) {
        super(time, label);
    }

    @Override
    public void update() {
        ZonedDateTime epoch = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("-00:00"));
        long secSinceEpoch = ChronoUnit.SECONDS.between(epoch, this.time.getZonedDateTime());
        this.label.setText(String.format("%d Jan1970EST", secSinceEpoch));
    }
}
