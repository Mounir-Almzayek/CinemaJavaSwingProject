/**
 * The Interval enum represents different time intervals in a day.
 * Each interval has a corresponding time range.
 */
public enum Interval {
    // First interval from 12:00 AM to 03:00 AM
    FIRST("12:00-03:00"),

    // Second interval from 03:00 AM to 06:00 AM
    SECOND("03:00-06:00"),

    // Third interval from 06:00 AM to 09:00 AM
    THIRD("06:00-09:00");

    // String to store the time range for each interval
    final String time;

    Interval(String time) {
        this.time = time;
    }
}
