

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class CarTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CarTest
{
    /**
     * Default constructor for test class CarTest
     */
    public CarTest()
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
    public void car1()
    {
        Graph g = new Graph(10,40);
        g.generateGraph(123);
        
        Car c = new Car(g,1);
        c.move(g,1);
        assertEquals(g.getEdge(c.getStart(),c.getDest()).cars.containsKey(1),true);
    }
    
    @Test
    public void car2()
    {
        Graph g = new Graph(10,40);
        g.generateGraph(123);
        
        Car c = new Car(g,1);
        for(int i=0;i<10;i++)
        {
            c.move(g,1);
        }
        assertEquals(g.getEdge(c.getStart(),c.getDest()).cars.containsKey(1),true);
    }
    
    @Test
    public void car3()
    {
        Graph g = new Graph(10,40);
        g.generateGraph(123);
        Customer customer = new Customer(g,1,1);
        Car c = new Car(g,1);
        c.assignCustomer(g,customer);
        c.move(g,1);
        assertEquals(customer,c.getCustomer());
        
    }
    
    @Test
    public void car4()
    {
        Graph g = new Graph(10,40);
        g.generateGraph(123);
        Customer customer = new Customer(g,1,1);
        Car c = new Car(g,1);
        c.assignCustomer(g,customer);
        c.move(g,1);
        assertEquals(customer,c.getCustomer());
        for(int i=0;i<10;i++)
        {
            c.move(g,1);
        }
        assertEquals(g.getEdge(c.getStart(),c.getDest()).cars.containsKey(1),true);
    }
    
    @Test
    public void car5()
    {
        Graph g = new Graph(10,40);
        g.generateGraph(123);
        Customer customer = new Customer(g,1,1);
        Car c = new Car(g,1);
        c.assignCustomer(g,customer);
        c.move(g,1);
        assertEquals(customer,c.getCustomer());
        c.move(g,1);
        assertEquals(g.getEdge(c.getStart(),c.getDest()).cars.containsKey(1),true);
    }
}

