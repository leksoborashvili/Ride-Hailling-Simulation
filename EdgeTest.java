

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class EdgeTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class EdgeTest
{
    /**
     * Default constructor for test class EdgeTest
     */
    public EdgeTest()
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
    public void edge1()
    {
        Edge edge = new Edge(1,2,"regular");
        assertEquals(1,edge.getNode1());
        assertEquals(2,edge.getNode2());
        assertEquals("regular",edge.getType());
    }
    
    @Test
    public void edge2()
    {
        Edge edge = new Edge(1,2,"highway");
        assertEquals(1,edge.getNode1());
        assertEquals(2,edge.getNode2());
        assertEquals("highway",edge.getType());
    }
}

