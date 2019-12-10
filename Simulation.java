/**
 * Runs the whole Simulation, Controlls global clock
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class Simulation
{
    /**
     * runs the simulation prints the results in the given file
     * @param numberofNodes number of nodes
     * @param numberofCars number of cars
     * @param density density chance of two nodes being connected
     * @param customerSpawnChance custmer spawn Chance
     */
    public void simulation(int numberofNodes,int numberofCars,int density,int customerSpawnChance) throws Exception
    {
        
        Graph graph = new Graph(numberofNodes,density);
        graph       . generateGraph(213);
        
        ArrayDeque<Customer> customerQueue = new ArrayDeque<Customer>();
        ArrayList<Car> cars                = new ArrayList<Car>();
        
        for(int i=1;i<=numberofCars;i++)
        {
            Car car = new Car(graph,i);
            cars    . add(car);
        }
        int numberofCustomers = 0;
        int time              = 0;
        boolean runner        = true;
        Random customerSpawn  = new Random();
        
        //the main simulation of the global clock 180 minutes in this case
        int maxTime = 60;
        while(runner)
        {
            System.out.println(time);
            //randomize the weghts of the edges
            if(time % 5 == 0 ) {graph.randomizeEdgeWeights();}
            
            int spawner = customerSpawn.nextInt(100);
            //spawns new customer
            if(spawner < customerSpawnChance && time<maxTime) 
            {
                numberofCustomers ++;
                Customer customer = new Customer(graph,time,10);
                customerQueue     .addLast(customer);
            } 
            //sets the list of free cars that will be assigned the customer 
            ArrayList<Car> freeCars = new ArrayList<Car>();
            for(int i=0;i<cars.size();i++)
            {
                if(cars.get(i).getCustomer()==null) freeCars.add(cars.get(i));
            }
            if(time>=maxTime && 
               freeCars.size()      == cars.size() && 
               customerQueue.size() == 0) break;
            //until there are free cars and customers they will be assigned the cars
            while(freeCars.size()>0 && customerQueue.size()>0)
            {
                Customer customer = customerQueue.removeFirst();
                
                int[] distances   = graph.listDijkstra(customer.getPickup());
                
                Car car           = Car.getNearestCar(freeCars,distances);
                
                car     .assignCustomer(graph,customer);
                
                freeCars.remove(car);
                
            }
            Result.addIdleCars(freeCars.size());
            for(int i=0;i<cars.size();i++)
            {
                cars.get(i).move(graph,time);
            }
            
            time++;
        }
        
        
        Result  . setTime(time);
        Result  . printData(numberofNodes, density, numberofCars, customerSpawnChance, numberofCustomers);
        Customer. reset();
        
    }
}
