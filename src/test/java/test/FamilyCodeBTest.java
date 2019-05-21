package test;

import code.FamilyCodeB;
import code.FamilyInterface;
import constants.FamilyPayConstants;
import constants.TimeConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FamilyCodeBTest {

    private FamilyInterface familyCodeB;

    @Before
    public void setup() {
        familyCodeB = new FamilyCodeB();
    }

    @Test
    public void givenEarliestHourReturnFamilyCodeBBasePay() {
        int actual = familyCodeB.computePay(TimeConstants.EARLIEST_START_TIME);
        int expected = FamilyPayConstants.FAMILY_B_BASE_PAY;
        assertEquals(expected, actual);
    }

    @Test
    public void givenTimeAfter10ReturnFamilyCodeBTimeBetween10And11Pay() {
        int actual = familyCodeB.computePay(TimeConstants.ELEVEN_PM);
        int expected = FamilyPayConstants.FAMILY_B_BETWEEN_10_PM_AND_MIDNIGHT_PAY;
        assertEquals(expected, actual);
    }

    @Test
    public void givenTimeAfterMidnightReturnFamilyCodeBTimeAfterMidnightPay() {
        int actual = familyCodeB.computePay(TimeConstants.MIDNIGHT + 1);
        int expected = FamilyPayConstants.FAMILY_B_POST_MIDNIGHT_PAY;
        assertEquals(expected, actual);
    }
}
