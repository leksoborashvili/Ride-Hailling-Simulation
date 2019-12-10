
/**
 * Runs chosen configurations
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Runner
{
    /**
     * calls the simulation method number of times for each configuration to see the result in given csv file.
     */
    public static void main() throws Exception
    {
        int[] numberofNodes       = {1000,5000,10000};
        int[] numberofCars        = {100,500,900};
        int[] density             = {20,50,80};
        int[] customerSpawnChance = {20,40,60};
        Simulation s = new Simulation();
        Writer.setFileWriter("simulation.csv",
            "Number of Nodes,Graph Density,Number of Cars,CustomerSpawnChance,Number of Customers,Wait Time,Ride Time,Expected Ride Time,Idle Cars");
        for(int nodes=0;nodes<3;nodes++)
            for(int cars=0;cars<3;cars++)
                for(int dens=0;dens<3;dens++)
                    for(int spawn=0;spawn<3;spawn++)
                    s.simulation(numberofNodes[nodes],numberofCars[cars],density[dens],customerSpawnChance[spawn]);
       
        Writer.close();
        
    }
    
}
