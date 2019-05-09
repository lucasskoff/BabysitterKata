package test;

import code.BabysitterTimesheet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BabysitterTest {
    private BabysitterTimesheet babysitterTimesheet;
    @Before
    public void setup() {
       babysitterTimesheet = new BabysitterTimesheet();
    }

    @Test
    public void givenStartTimeWhenTimeIsBefore5PmThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidStartTime(16));
    }

    @Test
    public void givenStartTimeWhenTimeEquals5PmThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidStartTime(17));
    }

    @Test
    public void givenStartTimeWhenTimeIsAfter5PmThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidStartTime(18));
    }

    @Test
    public void givenStartTimeWhenTimeIsBefore4AMThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidStartTime(3));
    }

    @Test
    public void givenStartTimeWhenTimeIsNotValidNumberThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidStartTime(25));
    }

    @Test
    public void givenEndTimeWhenTimeIsAfter4AmThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidEndTime(5));
    }

    @Test
    public void givenEndTimeWhenTimeIs4AmThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidEndTime(4));
    }

    @Test
    public void givenEndTimeWhenTimeIsBefore4AmThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidEndTime(3));
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
    public void givenEndTimeWhenTimeIs4PmThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidEndTime(16));
    }

    @Test
    public void givenEndTimeWhenTimeIs5PMThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidEndTime(17));
    }

    @Test
    public void givenTimesWhenEndTimeBeforeStartTimeThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidTime(17, 16));
    }

    @Test
    public void givenTimeWhenStartTimeAndEndTimeAreValidReturnTrue() {
        assertTrue(babysitterTimesheet.isValidTime(17, 4));
    }
}
