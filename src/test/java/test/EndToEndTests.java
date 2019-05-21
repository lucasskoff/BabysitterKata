package test;

import code.BabysitterTimesheet;
import code.FamilyCodeA;
import code.FamilyCodeB;
import code.FamilyInterface;
import constants.TimeConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EndToEndTests {
    private FamilyInterface familyCodeA;
    private FamilyInterface familyCodeB;
    private BabysitterTimesheet babysitterTimesheet;

    @Before
    public void setup() {
        babysitterTimesheet = new BabysitterTimesheet();
        familyCodeA = new FamilyCodeA();
        familyCodeB = new FamilyCodeB();
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestForFamilyAReturn190() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, familyCodeA);
        int expected = 190;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestPossibleForFamilyBReturn140() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, familyCodeB);
        int expected = 140;
        assertEquals(expected, actual);
    }
}
