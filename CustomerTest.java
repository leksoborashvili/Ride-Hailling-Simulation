

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * The test class CustomerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerTest
{
    /**
     * Default constructor for test class CustomerTest
     */
    public CustomerTest()
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
    public void customer1()
    {
        Graph g = new Graph(10,40);
        g.generateGraph(123);
        Customer c = new Customer(g,1,1);
        assertEquals(c.getID(),0);
        assertEquals(c.getTimeOrdered(),1);
        c.reset();
    }
    
    @Test
    public void customer2()
    {
        Graph g = new Graph(10,40);
        g.generateGraph(123);
        Customer c = new Customer(g,1,1);
        int pickup = c.getPickup();
        ArrayList<Integer> list = g.getNeighbors(pickup);
        assertEquals(list.contains(c.getDropoff()),true);
        c.reset();
    }
}

