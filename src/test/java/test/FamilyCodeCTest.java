package test;

import code.FamilyCodeB;
import code.FamilyCodeC;
import code.FamilyInterface;
import constants.FamilyPayConstants;
import constants.TimeConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FamilyCodeCTest {
    private FamilyInterface familyCodeC;

    @Before
    public void setup() {
        familyCodeC = new FamilyCodeC();
    }

    @Test
    public void givenEarliestHourReturnFamilyCodeBBasePay() {
        int actual = familyCodeC.computePay(TimeConstants.EARLIEST_START_TIME);
        int expected = FamilyPayConstants.FAMILY_C_BASE_PAY;
        assertEquals(expected, actual);
    }

    @Test
    public void givenTimeAfter10ReturnFamilyCodeBTimeBetween10And11Pay() {
        int actual = familyCodeC.computePay(TimeConstants.NINE_PM + 1);
        int expected = FamilyPayConstants.FAMILY_C_POST_9_PM_PAY;
        assertEquals(expected, actual);
    }
}
