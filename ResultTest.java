

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;
/**
 * The test class ResultTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ResultTest
{
    /**
     * Default constructor for test class ResultTest
     */
    public ResultTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void result1() throws Exception
    {
        Result.addWaitTime(100);
        Result.addRideTime(200);
        Result.addExpectedRideTime(200);
        Result.addIdleCars(500);
        Result.setTime(10);
        Writer.setFileWriter("test.csv","sdfg");
        Result.printData(10,10,10,10,10);
        Writer.close();
        BufferedReader reader = new BufferedReader(new FileReader("test.csv"));
        reader.readLine();
        StringTokenizer str = new StringTokenizer(reader.readLine(), ",");
        assertEquals("10",str.nextElement());
        assertEquals("10",str.nextElement());
        assertEquals("10",str.nextElement());
        assertEquals("10",str.nextElement());
        assertEquals("10",str.nextElement());
        assertEquals("10",str.nextElement());
        assertEquals("20",str.nextElement());
        assertEquals("20",str.nextElement());
        assertEquals("50",str.nextElement());
    }
}

