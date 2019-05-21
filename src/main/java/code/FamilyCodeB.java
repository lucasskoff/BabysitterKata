package code;

import constants.FamilyPayConstants;
import constants.TimeConstants;

public class FamilyCodeB implements FamilyInterface {
    @Override
    public int computePay(int hour) {
        if(hour < TimeConstants.TEN_PM) {
            return FamilyPayConstants.FAMILY_B_BASE_PAY;
        } else if (hour < TimeConstants.MIDNIGHT){
            return FamilyPayConstants.FAMILY_B_BETWEEN_10_PM_AND_MIDNIGHT_PAY;
        } else {
            return FamilyPayConstants.FAMILY_B_POST_MIDNIGHT_PAY;
        }
    }
}
