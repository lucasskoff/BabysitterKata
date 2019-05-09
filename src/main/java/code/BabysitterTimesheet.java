package code;

import constants.FamilyCodeConstant;
import constants.FamilyPayConstants;
import constants.PaymentErrorCodes;
import constants.TimeConstants;

import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.List;

public class BabysitterTimesheet {
    private List<Character> familyCodeList;

    public BabysitterTimesheet() {
        familyCodeList = new ArrayList<>();
        familyCodeList.add(FamilyCodeConstant.FAMILY_CODE_A);
        familyCodeList.add(FamilyCodeConstant.FAMILY_CODE_B);
        familyCodeList.add(FamilyCodeConstant.FAMILY_CODE_C);
    }

    public boolean isValidStartTime(int time) {
        return (time >= TimeConstants.EARLIEST_START_TIME && time < TimeConstants.MIDNIGHT) || time < TimeConstants.LATEST_END_TIME;
    }

    public boolean isValidEndTime(int time) {
        return (time > TimeConstants.EARLIEST_START_TIME && time < TimeConstants.MIDNIGHT) || time <= TimeConstants.LATEST_END_TIME;
    }

    public boolean isValidTimePeriod(int startTime, int endTime) {
        return normalizeTime(endTime) > startTime && isValidStartTime(startTime) && isValidEndTime(endTime);
    }

    public int computePay(int startTime, int endTime, char familyCode) {
        if(!isValidTimePeriod(startTime, endTime)) {
            return PaymentErrorCodes.INVALID_HOURS_ERROR_CODE;
        }
        if(!familyCodeList.contains(familyCode)) {
            return PaymentErrorCodes.INVALID_FAMILY_CODE_ERROR_CODE;
        }
        int modifiedEndTime = normalizeTime(endTime);
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
        return hour < TimeConstants.ELEVEN_PM ? FamilyPayConstants.FAMILY_A_BASE_PAY : FamilyPayConstants.FAMILY_A_POST_11_PAY;
    }

    private int computePayFamilyB(int hour) {
        if(hour < TimeConstants.TEN_PM) {
            return FamilyPayConstants.FAMILY_B_BASE_PAY;
        } else if (hour < TimeConstants.MIDNIGHT){
            return FamilyPayConstants.FAMILY_B_BETWEEN_10_PM_AND_MIDNIGHT_PAY;
        } else {
            return FamilyPayConstants.FAMILY_B_POST_MIDNIGHT_PAY;
        }
    }

    private int computePayFamilyC(int hour) {
        return hour < TimeConstants.NINE_PM ? FamilyPayConstants.FAMILY_C_BASE_PAY : FamilyPayConstants.FAMILY_C_POST_9_PM_PAY;
    }

    private int normalizeTime(int hour) {
        return hour < TimeConstants.NOON ? hour + TimeConstants.MIDNIGHT : hour;
    }
}
