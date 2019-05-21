package test;

import code.BabysitterTimesheet;
import code.FamilyCodeA;
import code.FamilyInterface;
import constants.TimeConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EndToEndTests {
    private FamilyInterface familyCodeA;
    private BabysitterTimesheet babysitterTimesheet;

    @Before
    public void setup() {
        babysitterTimesheet = new BabysitterTimesheet();
        familyCodeA = new FamilyCodeA();
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestForFamilyAReturn190() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, familyCodeA);
        int expected = 190;
        assertEquals(expected, actual);
    }
}
