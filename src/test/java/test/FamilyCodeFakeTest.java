package test;

import code.FamilyInterface;
import constants.TimeConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FamilyCodeFakeTest {

    private FamilyInterface familyCodeFake;

    @Before
    public void setup() {
        familyCodeFake = new FamilyCodeFake();
    }

    @Test
    public void givenEarliestHourReturnFamilyCodeABasePay() {
        int actual = familyCodeFake.computePay(TimeConstants.EARLIEST_START_TIME);
        int expected = 1;
        assertEquals(expected, actual);
    }
}
