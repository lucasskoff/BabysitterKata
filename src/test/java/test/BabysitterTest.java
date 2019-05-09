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
        assertEquals(15, babysitterTimesheet.computePay(17, 16, 'A'));
    }
}
