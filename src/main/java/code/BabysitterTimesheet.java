package code;

import constants.PaymentErrorCodes;
import constants.TimeConstants;

public class BabysitterTimesheet {

    public BabysitterTimesheet() {
    }

    public boolean isValidStartTime(int time) {
        return (time >= TimeConstants.EARLIEST_START_TIME && time < TimeConstants.MIDNIGHT) || time < TimeConstants.LATEST_END_TIME;
    }

    public boolean isValidEndTime(int time) {
        return (time > TimeConstants.EARLIEST_START_TIME && time < TimeConstants.MIDNIGHT) || time <= TimeConstants.LATEST_END_TIME;
    }

    public boolean isValidTimePeriod(int startTime, int endTime) {
        return normalizeTime(endTime) > normalizeTime(startTime) && isValidStartTime(startTime) && isValidEndTime(endTime);
    }

    public int computePay(int startTime, int endTime, FamilyInterface familyCode) {
        if(!isValidTimePeriod(startTime, endTime)) {
            return PaymentErrorCodes.INVALID_HOURS_ERROR_CODE;
        }
        if(familyCode == null) {
            return PaymentErrorCodes.INVALID_FAMILY_CODE_ERROR_CODE;
        }
        int modifiedEndTime = normalizeTime(endTime);
		int modifiedStartTime = normalizeTime(startTime);
        int payAmount = 0;
        for(int i = modifiedStartTime; i < modifiedEndTime; i++) {
            payAmount += familyCode.computePay(i);
        }
        return payAmount;
    }

    private int normalizeTime(int hour) {
        return hour < TimeConstants.NOON ? hour + TimeConstants.MIDNIGHT : hour;
    }
}
