package code;

public class BabysitterTimesheet {
    private static final int EARLIEST_START_TIME = 17;
    public boolean isValidStartTime(int time) {
        return time >= EARLIEST_START_TIME;
    }

    public boolean isValidEndTime(int time) {
        return false;
    }
}
