package test;

import code.BabysitterTimesheet;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BabysitterTest {

    @Test
    public void givenStartTimeWhenTimeIsBefore5ThenReturnFalse() {
        BabysitterTimesheet babysitterTimesheet = new BabysitterTimesheet();
        assertFalse(babysitterTimesheet.isValidStartTime(16));
    }
}
