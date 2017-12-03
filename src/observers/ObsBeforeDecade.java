package observers;

import javafx.scene.control.Label;
import models.Time;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * An observer class which handles converting EST to number of days left in before the end of this decade,
 * the last day being December 31, 2019.
 */
public class ObsBeforeDecade extends AbsTimeZoneObserver {

    /**
     * Constructor; Should set the time zone for the calendar object
     *
     * @param time  Time object we are listening to
     * @param label Label to display this observers information
     */
    public ObsBeforeDecade(Time time, Label label) {
        super(time, label);
    }

    @Override
    public void update() {
        LocalDate localDate = this.time.getZonedDateTime().toLocalDate();
        int year = (int) Math.floor(localDate.getYear() / 10.) * 10 + 9;
        LocalDate endOfDecade = LocalDate.of(year, 12, 31);
        long daysBeforeEnd = ChronoUnit.DAYS.between(localDate, endOfDecade);
        this.label.setText(String.format("%d BD", daysBeforeEnd));
    }
}
