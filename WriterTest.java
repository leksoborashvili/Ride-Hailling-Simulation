

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.*;
/**
 * The test class WriterTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WriterTest
{
    /**
     * Default constructor for test class WriterTest
     */
    public WriterTest()
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
    public void testWriter() throws Exception
    {
        Writer.setFileWriter("test.txt","test");
        Writer.writeCSV("test");
        Writer.close();
        BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
        
        StringTokenizer tok = new StringTokenizer(reader.readLine());
        assertEquals("test",tok.nextElement());
        
    }
}

