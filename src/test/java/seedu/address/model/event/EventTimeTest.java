package seedu.address.model.event;
// @@author HuWanqing
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EventTimeTest {
    @Test
    public void isValidEventTime() {
        // invalid event time
        assertFalse(EventTime.isValidEventTime("32/10/2017")); // there is no 32th in a month
        assertFalse(EventTime.isValidEventTime("10/13/1992")); // there isn't a 13th month
        assertFalse(EventTime.isValidEventTime("0/1/1994")); // day cannot be 0
        assertFalse(EventTime.isValidEventTime("1/0/1995")); // month cannot be 0
        assertFalse(EventTime.isValidEventTime("31/4/1990")); // april does not have a 31st day
        assertFalse(EventTime.isValidEventTime("30/02/1996")); // february does not have a 30th day
        assertFalse(EventTime.isValidEventTime("29/02/1997")); // 1997 is not a leap year
        assertFalse(EventTime.isValidEventTime("1993/11/21")); // does not follow 'DD/MM/YYYY' format
        assertFalse(EventTime.isValidEventTime("09/1994/30")); // does not follow 'DD/MM/YYYY' format
        assertFalse(EventTime.isValidEventTime("04.04.2010")); // does not user '/' as splitter

        // valid birthdays
        assertTrue(EventTime.isValidEventTime("01/01/1990"));
        assertTrue(EventTime.isValidEventTime("13/05/1991"));
        assertTrue(EventTime.isValidEventTime("24/06/1992"));
        assertTrue(EventTime.isValidEventTime("17/02/1993"));
    }
}
