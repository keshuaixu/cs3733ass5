package observers;

import javafx.scene.control.Label;
import models.Time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * An observer class which handles converting to the Pacific Time Zone (PST).
 * Do not worry about daylights savings time.
 */
public class ObsPacificStandard extends AbsTimeZoneObserver {

    /**
     * Constructor; Should set the time zone for the calendar object
     *
     * @param time  Time object we are listening to
     * @param label Label to display this observers information
     */
    public ObsPacificStandard(Time time, Label label) {
        super(time, label);
    }

    // Hint: SimpleDateFormat, calendar.getTime(), and calendar.get(Calendar.HOUR_OF_DAY) can be useful here
    @Override
    public void update() {
    }
}
