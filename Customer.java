
/**
 * Customer class that contains all the methods that the customer object needs
 *
 * @author Lekso Borashvili
 * @version (a version number or a date)
 */
import java.util.*;
public class Customer
{
    private int ID;
    private int pickup;
    private int dropoff;
    private int expectedTravelTime;
    private int timeOrdered;
    private int timePickedUp;
    private int waitTime;
    private int rideTime;
    /**
     * Constructor adds the custoemer on the random node of the graph.
     * @param graph the given graph
     * @param id ID of the customer
     * @param bfs max dropoff distance from the spawn point
     */
    public Customer(Graph graph,int id,int bfs)
    {
        ID=id;
        Random rand = new Random();
        pickup = rand.nextInt(graph.getNodes());
        bfs = 1 + rand.nextInt(bfs);
        int k=0;
        dropoff=-1;
        while(dropoff==-1 && k<10){
            dropoff = graph.dropLocation(pickup,bfs);
            k++;
        }
        waitTime=0;
        rideTime=0;
        if(dropoff == -1) dropoff = graph.dropLocation(pickup,1);
    }
    /**
     * return ID
     * @return ID
     */
    public int getID()
    {
        return ID;
    }
    /**
     * returns pick up point
     * @return pick up point
     */
    public int getPickup()
    {
        return pickup;
    }
    /**
     * returns drop off point
     * @return drop off point
     */
    public int getDropoff()
    {
        return dropoff;
    }
    /**
     * sets the expected travel time
     * @param t expected travel time
     */
    public void setExpectedTravelTime(int t)
    {
        expectedTravelTime = t;
    }
    /**
     * returns expected travel time
     * @return expected travel time
     */
    public int getExpectedTravelTime()
    {
        return expectedTravelTime;
    }
    /**
     * sets the time customer was picked up
     * @param t the time customer was picked up
     */
    public void setTimePickedUp(int t)
    {
        timePickedUp=t;
    }
    /**
     * returns the time customer was picked up
     * @return the time customer was picked up
     */
    public int getTimePickedUp()
    {
        return timePickedUp;
    }
    /**
     * returns the wait time 
     * @return wait time
     */
    public int getWaitTime()
    {
        return waitTime;
    }
    /**
     * sets the wait time
     * @param w wait time
     */
    public void setWaitTime(int w)
    {
        waitTime = w;
    }
    /**
     * returns ride time
     * @reutnr ride time
     */
    public int getRideTime()
    {
        return rideTime;
    }
    /**
     * sets the ride time
     * @param r the ride time
     */
    public void setRideTime(int r)
    {
        rideTime=r;
    }
}
