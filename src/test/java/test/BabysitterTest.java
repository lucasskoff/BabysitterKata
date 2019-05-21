package test;

import code.*;
import constants.FamilyPayConstants;
import constants.PaymentErrorCodes;
import constants.TimeConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BabysitterTest {
    private static final int ONE_HOUR = 1;
    private BabysitterTimesheet babysitterTimesheet;
    private FamilyCodeA familyCodeA;
    private FamilyCodeB familyCodeB;
    private FamilyCodeC familyCodeC;
    private FamilyCodeFake familyCodeFake;

    @Before
    public void setup() {
       babysitterTimesheet = new BabysitterTimesheet();
       familyCodeA = new FamilyCodeA();
       familyCodeB = new FamilyCodeB();
       familyCodeC = new FamilyCodeC();
       familyCodeFake = new FamilyCodeFake();
    }

    @Test
    public void givenStartTimeWhenTimeIsBeforeEarliestStartTimeThenReturnFalse() {
        boolean actual = babysitterTimesheet.isValidStartTime(TimeConstants.EARLIEST_START_TIME - ONE_HOUR);
        assertFalse(actual);
    }

    @Test
    public void givenStartTimeWhenTimeEqualsEarliestStartTimeThenReturnTrue() {
        boolean actual = babysitterTimesheet.isValidStartTime(TimeConstants.EARLIEST_START_TIME);
        assertTrue(actual);
    }

    @Test
    public void givenStartTimeWhenTimeIsAfterEarliestStartTimeThenReturnTrue() {
        boolean actual = babysitterTimesheet.isValidStartTime(TimeConstants.EARLIEST_START_TIME + ONE_HOUR);
        assertTrue(actual);
    }

    @Test
    public void givenStartTimeWhenTimeIsBeforeLatestEndTimeThenReturnTrue() {
        boolean actual = babysitterTimesheet.isValidStartTime(TimeConstants.LATEST_END_TIME - ONE_HOUR);
        assertTrue(actual);
    }

    @Test
    public void givenStartTimeWhenTimeIsNotValidNumberThenReturnFalse() {
        boolean actual = babysitterTimesheet.isValidStartTime(25);
        assertFalse(actual);
    }

    @Test
    public void givenEndTimeWhenTimeIsAfterLatestEndTimeThenReturnFalse() {
        boolean actual = babysitterTimesheet.isValidEndTime(TimeConstants.LATEST_END_TIME + ONE_HOUR);
        assertFalse(actual);
    }

    @Test
    public void givenEndTimeWhenTimeIsLatestEndTimeThenReturnTrue() {
        boolean actual = babysitterTimesheet.isValidEndTime(TimeConstants.LATEST_END_TIME);
        assertTrue(actual);
    }

    @Test
    public void givenEndTimeWhenTimeIsBeforeLatestEndTimeThenReturnTrue() {
        boolean actual = babysitterTimesheet.isValidEndTime(TimeConstants.LATEST_END_TIME - ONE_HOUR);
        assertTrue(actual);
    }

    @Test
    public void givenEndTimeWhenTimeIs11PmThenReturnTrue() {
        boolean actual = babysitterTimesheet.isValidEndTime(23);
        assertTrue(actual);
    }

    @Test
    public void givenEndTimeWhenTimeIs6PmThenReturnTrue() {
        boolean actual = babysitterTimesheet.isValidEndTime(18);
        assertTrue(actual);
    }

    @Test
    public void givenEndTimeWhenTimeIsEarlierThanEarliestStartTimeThenReturnFalse() {
        boolean actual = babysitterTimesheet.isValidEndTime(TimeConstants.EARLIEST_START_TIME - ONE_HOUR);
        assertFalse(actual);
    }

    @Test
    public void givenEndTimeWhenTimeIsEarliestStartTimeThenReturnFalse() {
        boolean actual = babysitterTimesheet.isValidEndTime(TimeConstants.EARLIEST_START_TIME);
        assertFalse(actual);
    }

    @Test
    public void givenTimesWhenEndTimeBeforeStartTimeThenReturnFalse() {
        boolean actual = babysitterTimesheet.isValidTimePeriod(TimeConstants.EARLIEST_START_TIME, TimeConstants.EARLIEST_START_TIME - ONE_HOUR);
        assertFalse(actual);
    }

    @Test
    public void givenTimeWhenStartTimeAndEndTimeAreValidReturnTrue() {
        boolean actual = babysitterTimesheet.isValidTimePeriod(TimeConstants.EARLIEST_START_TIME, 19);
        assertTrue(actual);
    }

    @Test
    public void givenValidStartTimeEndTimeWhenEndTimeIsBeforeStartTimeReturnFalse() {
        boolean actual = babysitterTimesheet.isValidTimePeriod(19, 18);
        assertFalse(actual);
    }
	
	@Test
    public void givenValidStartTimeEndTimeWhenBothAreAfterMidnightReturnTrue() {
        boolean actual = babysitterTimesheet.isValidTimePeriod(2, 4);
        assertTrue(actual);
    }

    @Test
    public void givenValidStartTimeEndTimeWhenEndTimeIsAfterMidnightReturnTrue() {
        boolean actual = babysitterTimesheet.isValidTimePeriod(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME);
        assertTrue(actual);
    }

    @Test
    public void givenValidStartTimeEndTimeWhenStartTimeEndTimeIsSameTimeReturnFalse() {
        boolean actual = babysitterTimesheet.isValidTimePeriod(18, 18);
        assertFalse(actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestPossibleForFakeFamilyReturn11() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, familyCodeFake);
        int expected = 11;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeAndEndTimeForInvalidFamilyCodeReturnError() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, null);
        int expected = PaymentErrorCodes.INVALID_FAMILY_CODE_ERROR_CODE;
        assertEquals(expected, actual);
    }

    @Test
    public void givenInvalidStartTimeEndTimeForFamilyAReturnError() {
        int actual = babysitterTimesheet.computePay(16, 16, familyCodeFake);
        int expected = PaymentErrorCodes.INVALID_HOURS_ERROR_CODE;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeEndTime1HourIntervalBefore11ForFamilyAReturn15() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.EARLIEST_START_TIME + ONE_HOUR, familyCodeA);
        int expected = FamilyPayConstants.FAMILY_A_BASE_PAY;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf11ForFamilyAReturn90() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, 23, familyCodeA);
        int expected = 90;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfMidnightForFamilyAReturn110() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, 0, familyCodeA);
        int expected = 110;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestForFamilyAReturn190() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, familyCodeA);
        int expected = 190;
        assertEquals(expected, actual);
    }
	
	@Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestPossibleForFamilyAReturn40() {
        int actual = babysitterTimesheet.computePay(2, 4, familyCodeA);
        int expected = 40;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTime1HourLaterForFamilyBReturn12() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.EARLIEST_START_TIME + ONE_HOUR, familyCodeB);
        int expected = FamilyPayConstants.FAMILY_B_BASE_PAY;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf11ForFamilyBReturn68() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, 23, familyCodeB);
        int expected = 68;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf1AMForFamilyBReturn92() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, 1, familyCodeB);
        int expected = 92;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestPossibleForFamilyBReturn140() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, familyCodeB);
        int expected = 140;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf1HourLaterForFamilyCReturn21() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.EARLIEST_START_TIME + ONE_HOUR, familyCodeC);
        int expected = FamilyPayConstants.FAMILY_C_BASE_PAY;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf10PMForFamilyCReturn99() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, 22, familyCodeC);
        int expected = 99;
        assertEquals(expected, actual);
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestPossibleForFamilyCReturn189() {
        int actual = babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, familyCodeC);
        int expected = 189;
        assertEquals(expected, actual);
    }
}
