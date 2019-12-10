
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
    private static int objCounter;
    /**
     * Constructor adds the custoemer on the random node of the graph.
     * @param graph the given graph
     * @param id ID of the customer
     * @param bfs max dropoff distance from the spawn point
     */
    public Customer(Graph graph,int time,int bfs)
    {
        ID          = objCounter;
        objCounter  ++;
        Random rand = new Random();
        pickup      = rand.nextInt(graph.getNodes());
        bfs         = 1 + rand.nextInt(bfs);
        int k       = 0;
        timeOrdered = time;
        dropoff     = -1;
        while(dropoff==-1 && k<10){
            dropoff = graph.dropLocation(pickup,bfs);
            k++;
            bfs--;
        }

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
     * sets time ordered
     * @param or time ordered
     */
    public void setTimeOrdered(int or)
    {
        timeOrdered = or;
    }
    /**
     * returns timeOrdered
     * @return time ordered
     */
    public int getTimeOrdered()
    {
        return timeOrdered;
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
     * returns number of customers
     * @return number of customers
     */
    public static int getNumberofCustomer()
    {
        return objCounter;
    }
    /**
     * resets static values
     */
    public static void reset()
    {
        objCounter = 0;
    }
}
