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
    public void givenEaliestHourReturnFamilyCodeABasePay() {
        int actual = familyCodeA.computePay(TimeConstants.EARLIEST_START_TIME);
        int expected = FamilyPayConstants.FAMILY_A_BASE_PAY;
        assertEquals(expected, actual);
    }
}
