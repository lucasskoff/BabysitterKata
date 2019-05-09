package code;

import constants.FamilyCodeConstant;
import constants.TimeConstants;

public class BabysitterTimesheet {
    private static final int FAMILY_A_BASE_PAY = 15;
    private static final int FAMILY_A_POST_11_PAY = 20;
    private static final int FAMILY_B_BASE_PAY = 12;
    private static final int FAMILY_B_BETWEEN_10_PM_AND_MIDNIGHT_PAY = 8;
    private static final int FAMILY_B_POST_MIDNIGHT_PAY = 16;


    public boolean isValidStartTime(int time) {
        return (time >= TimeConstants.EARLIEST_START_TIME && time < TimeConstants.MIDNIGHT) || time < TimeConstants.LATEST_END_TIME;
    }

    public boolean isValidEndTime(int time) {
        return (time > TimeConstants.EARLIEST_START_TIME && time < TimeConstants.MIDNIGHT) || time <= TimeConstants.LATEST_END_TIME;
    }

    public boolean isValidTimePeriod(int startTime, int endTime) {
        int modifiedEndTime = endTime;
        if(modifiedEndTime < TimeConstants.NOON) {
            modifiedEndTime += TimeConstants.MIDNIGHT;
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
        if(familyCode != FamilyCodeConstant.FAMILY_CODE_A && familyCode != FamilyCodeConstant.FAMILY_CODE_B && familyCode != FamilyCodeConstant.FAMILY_CODE_C) {
            return -2;
        }
        int modifiedEndTime = endTime;
        if(endTime < TimeConstants.NOON) {
            modifiedEndTime += TimeConstants.MIDNIGHT;
        }
        int payAmount = 0;
        for(int i = startTime; i < modifiedEndTime; i++) {
            if(familyCode == FamilyCodeConstant.FAMILY_CODE_A) {
                payAmount += computePayFamilyA(i);
            } else if (familyCode == FamilyCodeConstant.FAMILY_CODE_B){
                payAmount += computePayFamilyB(i);
            } else {
                payAmount += computePayFamilyC(i);
            }
        }
        return payAmount;
    }

    private int computePayFamilyA(int hour) {
        return hour < TimeConstants.ELEVEN_PM ? FAMILY_A_BASE_PAY : FAMILY_A_POST_11_PAY;
    }

    private int computePayFamilyB(int hour) {
        if(hour < TimeConstants.TEN_PM) {
            return FAMILY_B_BASE_PAY;
        } else if (hour < TimeConstants.MIDNIGHT){
            return FAMILY_B_BETWEEN_10_PM_AND_MIDNIGHT_PAY;
        } else {
            return FAMILY_B_POST_MIDNIGHT_PAY;
        }
    }

    private int computePayFamilyC(int hour) {
        return hour < TimeConstants.NINE_PM ? 21 : 15;
    }
}
