package code;

public class BabysitterTimesheet {
    private static final int EARLIEST_START_TIME = 17;
    private static final int LATEST_END_TIME = 4;
    private static final int MIDNIGHT = 24;


    public boolean isValidStartTime(int time) {
        return (time >= EARLIEST_START_TIME && time < MIDNIGHT) || time < LATEST_END_TIME;
    }

    public boolean isValidEndTime(int time) {
        return (time > EARLIEST_START_TIME && time < MIDNIGHT) || time <= LATEST_END_TIME;
    }

    public boolean isValidTimePeriod(int startTime, int endTime) {
        int modifiedEndTime = endTime;
        if(modifiedEndTime < 12) {
            modifiedEndTime += 24;
        }
        if(modifiedEndTime <= startTime) {
            return false;
        }
        return isValidStartTime(startTime) && isValidEndTime(endTime);
    }

    public int computePay(int startTime, int endTime, char familyCode) {
        if(!isValidTimePeriod(startTime, endTime)) {
            return -1;
        }
        int modifiedEndTime = endTime;
        if(endTime < 12) {
            modifiedEndTime += 24;
        }
        int payAmount = 0;
        for(int i = startTime; i < modifiedEndTime; i++) {
            if(i < 23) {
                payAmount += 15;
            } else {
                payAmount += 20;
            }
        }
        return payAmount;
    }
}
