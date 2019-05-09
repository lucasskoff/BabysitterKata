package code;

import constants.FamilyCodeConstant;

public class BabysitterTimesheet {
    private static final int LATEST_END_TIME = 4;
    private static final int NOON = 12;
    private static final int EARLIEST_START_TIME = 17;
    private static final int TEN_PM = 22;
    private static final int ELEVEN_PM = 23;
    private static final int MIDNIGHT = 24;
    private static final int FAMILY_A_BASE_PAY = 15;
    private static final int FAMILY_A_POST_11_PAY = 20;
    private static final int FAMILY_B_BASE_PAY = 12;
    private static final int FAMILY_B_BETWEEN_10_PM_AND_MIDNIGHT_PAY = 8;
    private static final int FAMILY_B_POST_MIDNIGHT_PAY = 16;


    public boolean isValidStartTime(int time) {
        return (time >= EARLIEST_START_TIME && time < MIDNIGHT) || time < LATEST_END_TIME;
    }

    public boolean isValidEndTime(int time) {
        return (time > EARLIEST_START_TIME && time < MIDNIGHT) || time <= LATEST_END_TIME;
    }

    public boolean isValidTimePeriod(int startTime, int endTime) {
        int modifiedEndTime = endTime;
        if(modifiedEndTime < NOON) {
            modifiedEndTime += MIDNIGHT;
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
        if(endTime < NOON) {
            modifiedEndTime += MIDNIGHT;
        }
        int payAmount = 0;
        for(int i = startTime; i < modifiedEndTime; i++) {
            if(familyCode == FamilyCodeConstant.FAMILY_CODE_A) {
                payAmount += computePayFamilyA(i);
            } else if (familyCode == FamilyCodeConstant.FAMILY_CODE_B){
                payAmount += computePayFamilyB(i);
            } else {
                if(i < 21) {
                    payAmount += 21;
                } else {
                    payAmount += 15;
                }
            }
        }
        return payAmount;
    }

    private int computePayFamilyA(int hour) {
        return hour < ELEVEN_PM ? FAMILY_A_BASE_PAY : FAMILY_A_POST_11_PAY;
    }

    private int computePayFamilyB(int hour) {
        if(hour < TEN_PM) {
            return FAMILY_B_BASE_PAY;
        } else if (hour < MIDNIGHT){
            return FAMILY_B_BETWEEN_10_PM_AND_MIDNIGHT_PAY;
        } else {
            return FAMILY_B_POST_MIDNIGHT_PAY;
        }
    }
}
