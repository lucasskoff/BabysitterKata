package test;

import code.BabysitterTimesheet;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BabysitterTest {

    @Test
    public void givenStartTimeWhenTimeIsBefore5ThenReturnFalse() {
        BabysitterTimesheet babysitterTimesheet = new BabysitterTimesheet();
        assertFalse(babysitterTimesheet.isValidStartTime(16));
    }

    @Test
    public void givenStartTimeWhenTimeEquals5ThenReturnTrue() {
        BabysitterTimesheet babysitterTimesheet = new BabysitterTimesheet();
        assertTrue(babysitterTimesheet.isValidStartTime(17));
    }

    @Test
    public void givenStartTimeWhenTimeIsAfter5ThenReturnTrue() {
        BabysitterTimesheet babysitterTimesheet = new BabysitterTimesheet();
        assertTrue(babysitterTimesheet.isValidStartTime(18));
    }
}
