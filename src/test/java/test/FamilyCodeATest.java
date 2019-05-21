package test;

import code.FamilyCodeA;
import code.FamilyInterface;
import constants.FamilyCodeConstant;
import constants.FamilyPayConstants;
import constants.TimeConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FamilyCodeATest {

    private FamilyInterface familyCodeA;

    @Before
    public void setup() {
        familyCodeA = new FamilyCodeA();
    }

    @Test
    public void givenEarliestHourReturnFamilyCodeABasePay() {
        int actual = familyCodeA.computePay(TimeConstants.EARLIEST_START_TIME);
        int expected = FamilyPayConstants.FAMILY_A_BASE_PAY;
        assertEquals(expected, actual);
    }

    @Test
    public void givenTimeAfter11ReturnFamilyCodeATimeAfter11Pay() {
        int actual = familyCodeA.computePay(TimeConstants.ELEVEN_PM + 1);
        int expected = FamilyPayConstants.FAMILY_A_POST_11_PAY;
        assertEquals(expected, actual);
    }
}
