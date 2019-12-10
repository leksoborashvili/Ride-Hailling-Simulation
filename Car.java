
/**
 * wraps up all the methods in connecting witht the car object. each car moves independently from each other serving customers
 *
 * @author Lekso Borashvili
 * @version (a version number or a date)
 */
import java.util.*;
public class Car
{
    private int      ID;
    
    private Customer customer;
    
    private int      start;
    
    private int      dest;
    
    private int      timeUntilNextPoint;
    
    private ArrayDeque<Integer> path;
    
    //if the customer has been picked up
    private int       picked=0;
    /**
     * constructor inserts the car on random point with given ID on the given graph
     * @param graph given graph
     * @param i given ID
     */
    public Car(Graph graph,int i)
    {
        ID          = i;
        Random rand = new Random();
        customer    = null;
        dest        = rand.nextInt(graph.getNodes());
    }
    /**
     * returns ID
     * @return ID
     */
    public int getID()
    {
        return ID;
    }
    /**
     * returns the assigned customer
     * @return the assigned customer
     */
    public Customer getCustomer()
    {
        return customer;
    }
    /**
     * returns the time until next point
     * @return time until next point
     */
    public int getTimeUntilNextPoint()
    {
        return timeUntilNextPoint;
    }
    /**
     * returns the destination
     * @return the destination
     */
    public int getDest()
    {
        return dest;
    }
    /**
     * returns the start
     * @return the start
     */
    public int getStart()
    {
        return start;
    }
    /**
     * moves the car according to its current state
     * @param the graph car is moving on
     * @param time time
     */
    public void move(Graph graph,int time)
    {
        if(customer ==null) freeMove(graph);
            else busyMove(graph,time);
    }
    //free move is when the car does not have a customer asssigned and just roams arround the map
    private void freeMove(Graph graph)
    {
        //if this is true car is assigned random 
        if(timeUntilNextPoint == 0) 
        {
            if(graph.getEdge(start,dest) != null){
            
            graph.removeCar(start,dest,ID);
            }   else graph.removeCar(start,dest,ID);
            
            start              = dest;
            //assigned new destination point
            dest               = graph.generateNeighborForFreeMove(dest);
            timeUntilNextPoint = graph.getEdge(start,dest).getCurWeight();
            
            graph.addCar(start,dest,ID);
                
            return;
        }
        //just reduces the time until next point if the car has not reached a node yet
        timeUntilNextPoint--;
    }
    //busy move is when car has customer assigned
    private void busyMove(Graph graph,int time)
    {
        //has not yet picked the customer
        if(picked==0)
        {
            
            if(timeUntilNextPoint == 0)
            {
                //this means the car reached destination and picks up the customer
                if(path.size()==0) {picked(graph,time); return;}
                start              = dest;
                dest               = path.removeLast();
                timeUntilNextPoint = graph.getEdge(start,dest).getCurWeight();
                
                graph.addCar(start,dest,ID);
                
                return;
            }
            
            
            timeUntilNextPoint--;
        } 
        //has already picked up customer
        else 
            {
                if(timeUntilNextPoint == 0)
                {
                    //reached the destination drops off the customer
                    if(path.size()==0) {drop(graph,time); return;}
                    start              = dest;
                    dest               = path.removeLast();
                    timeUntilNextPoint = graph.getEdge(start,dest).getCurWeight();
                    timeUntilNextPoint--;
                    graph.addCar(start,dest,ID);
                    
                    return;
                }
                
                
                timeUntilNextPoint--;
            }
    }
    
    //assigned new path for the car to the destination of the customer
    private void picked(Graph graph,int time)
    {
        picked      = 1;
        path        = graph.pathDijkstra(dest,customer.getDropoff());
        path        .removeLast();
        int expTime = graph.timeDijkstra(dest,customer.getDropoff());
        customer    .setExpectedTravelTime(expTime);
        customer    .setTimePickedUp(time);
        Result      .addExpectedRideTime(expTime);
        Result      .addWaitTime(time - customer.getTimeOrdered());
        /* System.out.println(customer.getID() + " Customer picked up at " + dest + " after " + (time-customer.getTimeOrdered()));
         * 
         */
    }
   
    //drops off the customer changes all the variables back to normal so car starts roaming 
    private void drop(Graph graph,int time)
    {
        picked =0;
        Result .addRideTime(time - customer.getTimePickedUp() - 1);
        
        /*System.out.println(customer.getID() + " Customer Dropped at " + dest + " after " + customer.getRideTime() + 
            " excpected wait time = " + customer.getExpectedTravelTime());
            */
        customer = null;
    }
    /**
     * assignes customer to a car
     * @param graph the graph car is moving on
     * @param c cusotmer assigned to the car
     */
    public void assignCustomer(Graph graph,Customer c)
    {
        customer = c;
        path     = graph.pathDijkstra(dest,c.getPickup());
        path     . removeLast();
        picked   = 0;
    }
    /**
     * returns the car from given arrayList that has the least value in the given array
     * @param cars arraylist of cars that needs to be checked in the array
     * @param distances the array of distances of the cars
     * @return the car from arraylist with the least distance in distances array
     */
    public static Car getNearestCar(ArrayList<Car> cars, int[] distances)
    {
        Car car   = cars.get(0);
        int minID = 0;
        for(int i=0;i<cars.size();i++)
        {
            if(distances[cars.get(i).getDest()] + cars.get(i).getTimeUntilNextPoint() < 
               distances[car.getDest()] + car.getTimeUntilNextPoint() ) {car =cars.get(i); minID = i;}
            
        }
        return car;
    }
}
