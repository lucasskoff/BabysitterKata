package code;

import constants.FamilyPayConstants;
import constants.TimeConstants;

public class FamilyCodeA implements FamilyInterface {
    @Override
    public int computePay(int hour) {
        return hour < TimeConstants.ELEVEN_PM ? FamilyPayConstants.FAMILY_A_BASE_PAY : FamilyPayConstants.FAMILY_A_POST_11_PAY;
    }
}
