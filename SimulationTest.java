

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
/**
 * The test class SimulationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SimulationTest
{
    /**
     * Default constructor for test class SimulationTest
     */
    public SimulationTest()
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
    public void simulationTest() throws Exception
    {
        Simulation s = new Simulation();
        Writer.setFileWriter("simulation.csv",
            "Number of Nodes,Graph Density,Number of Cars,CustomerSpawnChance,Number of Customers,Wait Time,Ride Time,Expected Ride Time,Idle Cars");
        
        s.simulation(10,10,40,50);
        //ensures that the simulation is finished and data printed
        Writer.close();
        BufferedReader reader = new BufferedReader(new FileReader("simulation.csv"));
        assertEquals(true,!(reader.readLine()==null));
        assertEquals(true,!(reader.readLine()==null));
    }
}
