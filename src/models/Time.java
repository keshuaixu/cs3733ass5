package models;

import observers.AbsTimeZoneObserver;

import java.lang.reflect.Array;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Observer;

/**
 * The time which keeps track of the time zone data we are interested with. Will notify any of its
 * observers when something changes with its value.
 */
public class Time {

    ArrayList<AbsTimeZoneObserver> observers = new ArrayList<>();
    private ZonedDateTime zonedDateTime;

    public Time() {
        // Don't forget to create an instance of Calendar.
        // Note Calendar cannot be initialized with new Calendar(), and Calendar.getInstance() must be used instead
    }

    /**
     * Accessor for the value field
     *
     * @return The current value of the time
     */
    @Deprecated
    public Calendar getValue() {
        return null;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

    /**
     * Setter for the value field
     *
     * @param value New value to propagate to clients
     */
    @Deprecated
    public void setValue(Calendar value) {
    }

    public void setValue(ZonedDateTime value) {
        this.zonedDateTime = value;
        notifyObservers();
    }

    /**
     * Adds a new observer client to the list of observers for this value
     *
     * @param observer An instance of an observer
     */
    public void attachObserver(AbsTimeZoneObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Calls the update function to tell each observer this time's value
     * has changed so they need to update.
     */
    public void notifyObservers() {
        this.observers.forEach(AbsTimeZoneObserver::update);
    }
}
