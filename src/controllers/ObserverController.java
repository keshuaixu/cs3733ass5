package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.Time;
import observers.*;

import java.time.*;


public class ObserverController {

    // Hint: use getValue() to get a LocalDate. From there you can use getYear, getMonth, and getDayOfMonth
    @FXML
    DatePicker ESTDate;

    @FXML
    TextField txtESTHours, txtESTMinutes, txtESTSeconds;

    // Hint: Look at tests to see what these labels should looks like. Utilize LocalDate's toString() function if needed
    @FXML
    Label labelJan1970EST, labelBeforeDecade, labelPST;

    private Time time = new models.Time();

    /**
     * Controller object which will initialize what kind of observers are listening to the subject (ie the Eastern Time
     * Zone) and notify observers of updates when something changes in the EST fields.
     */
    @FXML
    public void initialize() {
        time.attachObserver(new ObsBeforeDecade(time, labelBeforeDecade));
        time.attachObserver(new ObsJan1970EST(time, labelJan1970EST));
        time.attachObserver(new ObsPacificStandard(time, labelPST));
    }

    // Hint: When using Calendar.set(), month starts with 0

    /**
     * Action function for the textfields and datepicker associated with the Eastern Time Zone.
     * Will set the value of the subject inherently notifying all of its observers.
     * Calendar.getInstance() and set() is usefull here
     */
    public void updateTime() throws NumberFormatException {
        LocalDate date = ESTDate.getValue();
        LocalTime localTime = LocalTime.of(Integer.parseInt(txtESTHours.getText()), Integer.parseInt(txtESTMinutes.getText()), Integer.parseInt(txtESTSeconds.getText()));
        LocalDateTime localDateTime = LocalDateTime.of(date, localTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("EST"));
        time.setValue(zonedDateTime);
    }
}
