package test;

import code.BabysitterTimesheet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BabysitterTest {
    private static final int EARLIEST_START_TIME = 17;
    private static final int LATEST_END_TIME = 4;
    private BabysitterTimesheet babysitterTimesheet;
    @Before
    public void setup() {
       babysitterTimesheet = new BabysitterTimesheet();
    }

    @Test
    public void givenStartTimeWhenTimeIsBeforeEarliestStartTimeThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidStartTime(EARLIEST_START_TIME - 1));
    }

    @Test
    public void givenStartTimeWhenTimeEqualsEarliestStartTimeThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidStartTime(EARLIEST_START_TIME));
    }

    @Test
    public void givenStartTimeWhenTimeIsAfterEarliestStartTimeThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidStartTime(EARLIEST_START_TIME + 1));
    }

    @Test
    public void givenStartTimeWhenTimeIsBeforeLatestEndTimeThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidStartTime(LATEST_END_TIME - 1));
    }

    @Test
    public void givenStartTimeWhenTimeIsNotValidNumberThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidStartTime(25));
    }

    @Test
    public void givenEndTimeWhenTimeIsAfterLatestEndTimeThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidEndTime(LATEST_END_TIME + 1));
    }

    @Test
    public void givenEndTimeWhenTimeIsLatestEndTimeThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidEndTime(LATEST_END_TIME));
    }

    @Test
    public void givenEndTimeWhenTimeIsBeforeLatestEndTimeThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidEndTime(LATEST_END_TIME - 1));
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
        assertFalse(babysitterTimesheet.isValidEndTime(EARLIEST_START_TIME - 1));
    }

    @Test
    public void givenEndTimeWhenTimeIsEarliestStartTimeThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidEndTime(EARLIEST_START_TIME));
    }

    @Test
    public void givenTimesWhenEndTimeBeforeStartTimeThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidTimePeriod(EARLIEST_START_TIME, EARLIEST_START_TIME - 1));
    }

    @Test
    public void givenTimeWhenStartTimeAndEndTimeAreValidReturnTrue() {
        assertTrue(babysitterTimesheet.isValidTimePeriod(EARLIEST_START_TIME, 19));
    }

    @Test
    public void givenValidStartTimeEndTimeWhenEndTimeIsBeforeStartTimeReturnFalse() {
        assertFalse(babysitterTimesheet.isValidTimePeriod(19, 18));
    }

    @Test
    public void givenValidStartTimeEndTimeWhenEndTimeIsAfterMidnightReturnTrue() {
        assertTrue(babysitterTimesheet.isValidTimePeriod(EARLIEST_START_TIME, LATEST_END_TIME));
    }

    @Test
    public void givenValidStartTimeEndTimeWhenStartTimeEndTimeIsSameTimeReturnFalse() {
        assertFalse(babysitterTimesheet.isValidTimePeriod(18, 18));
    }

    @Test
    public void givenValidStartTimeEndTime1HourIntervalBefore11ForFamilyAReturn15() {
        assertEquals(15, babysitterTimesheet.computePay(EARLIEST_START_TIME, EARLIEST_START_TIME + 1, 'A'));
    }

    @Test
    public void givenInvalidStartTimeEndTimeForFamilyAReturnError() {
        assertEquals(-1, babysitterTimesheet.computePay(16, 16, 'A'));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf11ForFamilyAReturn90() {
        assertEquals(90, babysitterTimesheet.computePay(EARLIEST_START_TIME, 23, 'A'));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfMidnightForFamilyAReturn110() {
        assertEquals(110, babysitterTimesheet.computePay(EARLIEST_START_TIME, 0, 'A'));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestForFamilyAReturn190() {
        assertEquals(190, babysitterTimesheet.computePay(EARLIEST_START_TIME, LATEST_END_TIME, 'A'));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTime1HourLaterForFamilyBReturn12() {
        assertEquals(12, babysitterTimesheet.computePay(EARLIEST_START_TIME, EARLIEST_START_TIME + 1, 'B'));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf11ForFamilyBReturn68() {
        assertEquals(68, babysitterTimesheet.computePay(EARLIEST_START_TIME, 23, 'B'));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf1AMForFamilyBReturn92() {
        assertEquals(92, babysitterTimesheet.computePay(EARLIEST_START_TIME, 1, 'B'));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestPossibleForFamilyBReturn140() {
        assertEquals(140, babysitterTimesheet.computePay(EARLIEST_START_TIME, LATEST_END_TIME, 'B'));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf1HourLaterForFamilyCReturn21() {
        assertEquals(21, babysitterTimesheet.computePay(EARLIEST_START_TIME, EARLIEST_START_TIME + 1, 'C'));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOf10PMForFamilyCReturn99() {
        assertEquals(99, babysitterTimesheet.computePay(EARLIEST_START_TIME, 22, 'C'));
    }

    @Test
    public void givenValidStartTimeOfEarliestPossibleAndEndTimeOfLatestPossibleForFamilyCReturn189() {
        assertEquals(189, babysitterTimesheet.computePay(EARLIEST_START_TIME, LATEST_END_TIME, 'C'));
    }
}
