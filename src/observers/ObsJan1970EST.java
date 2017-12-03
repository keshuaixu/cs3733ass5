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
        /*
         * I think the provided tests are wrong.
         *
         * initialTestOriginJan1970EST test the seconds since 1970-01-01T00:00:00-05:00.
         * It should be zero. My code passes this test. But it does not pass other tests.
         *
         * The supplied tests are off by 4 **or** 5 hours.
         *
         * To make my code pass the initialTest1Jan1970EST and initialTest2Jan1970EST, change the ZoneId in the following line to "-00:00", but it fails initialTest3Jan1970EST
         * To make my code pass the initialTest3Jan1970EST , change the ZoneId in the following line to "-01:00"
         *
         */
        ZonedDateTime epoch = ZonedDateTime.of(1970, 1, 1, 0, 0, 0, 0, ZoneId.of("-05:00"));
        long secSinceEpoch = ChronoUnit.SECONDS.between(epoch, this.time.getZonedDateTime());
        this.label.setText(String.format("%d Jan1970EST", secSinceEpoch));
    }
}
