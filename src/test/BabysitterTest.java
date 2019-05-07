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
    public void givenStartTimeWhenTimeIsBefore5ThenReturnFalse() {
        assertFalse(babysitterTimesheet.isValidStartTime(16));
    }

    @Test
    public void givenStartTimeWhenTimeEquals5ThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidStartTime(17));
    }

    @Test
    public void givenStartTimeWhenTimeIsAfter5ThenReturnTrue() {
        assertTrue(babysitterTimesheet.isValidStartTime(18));
    }
}
