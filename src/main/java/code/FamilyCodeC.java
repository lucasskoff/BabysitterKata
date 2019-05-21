package code;

import constants.FamilyPayConstants;
import constants.TimeConstants;

public class FamilyCodeC implements FamilyInterface {
    @Override
    public int computePay(int hour) {
        return hour < TimeConstants.NINE_PM ? FamilyPayConstants.FAMILY_C_BASE_PAY : FamilyPayConstants.FAMILY_C_POST_9_PM_PAY;
    }
}
