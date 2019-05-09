package test;

import code.BabysitterTimesheet;
import constants.FamilyCodeConstant;
import constants.FamilyPayConstants;
import constants.PaymentErrorCodes;
import constants.TimeConstants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BabysitterTest {
    private static final int ONE_HOUR = 1;
    private BabysitterTimesheet babysitterTimesheet;
    @Before
    public void setup() {
       babysitterTimesheet = new BabysitterTimesheet();
    }

    @Test
    public void givenStartTimeWhenTimeIsBeforeEarliestStartTimeThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidStartTime(TimeConstants.EARLIEST_START_TIME - ONE_HOUR));
    }

    @Test
    public void givenStartTimeWhenTimeEqualsEarliestStartTimeThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidStartTime(TimeConstants.EARLIEST_START_TIME));
    }

    @Test
    public void givenStartTimeWhenTimeIsAfterEarliestStartTimeThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidStartTime(TimeConstants.EARLIEST_START_TIME + ONE_HOUR));
    }

    @Test
    public void givenStartTimeWhenTimeIsBeforeLatestEndTimeThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidStartTime(TimeConstants.LATEST_END_TIME - ONE_HOUR));
    }

    @Test
    public void givenStartTimeWhenTimeIsNotValidNumberThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidStartTime(25));
    }

    @Test
    public void givenEndTimeWhenTimeIsAfterLatestEndTimeThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidEndTime(TimeConstants.LATEST_END_TIME + ONE_HOUR));
    }

    @Test
    public void givenEndTimeWhenTimeIsLatestEndTimeThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidEndTime(TimeConstants.LATEST_END_TIME));
    }

    @Test
    public void givenEndTimeWhenTimeIsBeforeLatestEndTimeThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidEndTime(TimeConstants.LATEST_END_TIME - ONE_HOUR));
    }

    @Test
    public void givenEndTimeWhenTimeIs11PmThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidEndTime(23));
    }

    @Test
    public void givenEndTimeWhenTimeIs6PmThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidEndTime(18));
    }

    @Test
    public void givenEndTimeWhenTimeIsEarlierThanEarliestStartTimeThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidEndTime(TimeConstants.EARLIEST_START_TIME - ONE_HOUR));
    }

    @Test
    public void givenEndTimeWhenTimeIsEarliestStartTimeThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidEndTime(TimeConstants.EARLIEST_START_TIME));
    }

    @Test
    public void givenTimesWhenEndTimeBeforeStartTimeThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidTimePeriod(TimeConstants.EARLIEST_START_TIME, TimeConstants.EARLIEST_START_TIME - ONE_HOUR));
    }

    @Test
    public void givenTimeWhenStartTimeAndEndTimeAreValidReturnTrue() {
        assertTrue(babysitterTimesheet.isValidTimePeriod(TimeConstants.EARLIEST_START_TIME, 19));
    }

    @Test
    public void givenValidStartTimeEndTimeWhenEndTimeIsBeforeStartTimeReturnFalse() {
        assertFalse(babysitterTimesheet.isValidTimePeriod(19, 18));
    }

    @Test
    public void givenValidStartTimeEndTimeWhenEndTimeIsAfterMidnightReturnTrue() {
        assertTrue(babysitterTimesheet.isValidTimePeriod(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME));
    }

    @Test
    public void givenValidStartTimeEndTimeWhenStartTimeEndTimeIsSameTimeReturnFalse() {
        assertFalse(babysitterTimesheet.isValidTimePeriod(18, 18));
    }

    @Test
    public void givenInvalidStartTimeEndTimeForFamilyAReturnError() {
        assertEquals(PaymentErrorCodes.INVALID_HOURS_ERROR_CODE, babysitterTimesheet.computePay(16, 16, FamilyCodeConstant.FAMILY_CODE_A));
    }

    @Test
    public void givenValidStartTimeAndEndTimeForInvalidFamilyCodeReturnError() {
        assertEquals(PaymentErrorCodes.INVALID_FAMILY_CODE_ERROR_CODE, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, 'D'));
    }

    @Test
    public void givenValidStartTimeEndTime1HourIntervalBefore11ForFamilyAReturn15() {
        assertEquals(FamilyPayConstants.FAMILY_A_BASE_PAY, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.EARLIEST_START_TIME + ONE_HOUR, FamilyCodeConstant.FAMILY_CODE_A));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf11ForFamilyAReturn90() {
        assertEquals(90, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, 23, FamilyCodeConstant.FAMILY_CODE_A));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfMidnightForFamilyAReturn110() {
        assertEquals(110, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, 0, FamilyCodeConstant.FAMILY_CODE_A));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestForFamilyAReturn190() {
        assertEquals(190, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, FamilyCodeConstant.FAMILY_CODE_A));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTime1HourLaterForFamilyBReturn12() {
        assertEquals(FamilyPayConstants.FAMILY_B_BASE_PAY, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.EARLIEST_START_TIME + ONE_HOUR, FamilyCodeConstant.FAMILY_CODE_B));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf11ForFamilyBReturn68() {
        assertEquals(68, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, 23, FamilyCodeConstant.FAMILY_CODE_B));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf1AMForFamilyBReturn92() {
        assertEquals(92, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, 1, FamilyCodeConstant.FAMILY_CODE_B));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestPossibleForFamilyBReturn140() {
        assertEquals(140, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, FamilyCodeConstant.FAMILY_CODE_B));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf1HourLaterForFamilyCReturn21() {
        assertEquals(FamilyPayConstants.FAMILY_C_BASE_PAY, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.EARLIEST_START_TIME + ONE_HOUR, FamilyCodeConstant.FAMILY_CODE_C));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf10PMForFamilyCReturn99() {
        assertEquals(99, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, 22, FamilyCodeConstant.FAMILY_CODE_C));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestPossibleForFamilyCReturn189() {
        assertEquals(189, babysitterTimesheet.computePay(TimeConstants.EARLIEST_START_TIME, TimeConstants.LATEST_END_TIME, FamilyCodeConstant.FAMILY_CODE_C));
    }
}
