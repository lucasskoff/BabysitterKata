package code;

public class BabysitterTimesheet {
    private static final int EARLIEST_START_TIME = 17;
    private static final int LATEST_END_TIME = 4;
    private static final int MIDNIGHT = 24;
    public boolean isValidStartTime(int time) {
        return time >= EARLIEST_START_TIME;
    }

    public boolean isValidEndTime(int time) {
        return (time >= EARLIEST_START_TIME && time < 24) || time <= LATEST_END_TIME;
    }
}
