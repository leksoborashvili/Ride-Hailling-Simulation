
/**
 * Edge class is necessary to keep track of all the cars on the node and adjust the time necessary to cover the edge randomly
 *
 * @author Lekso Borashvili
 * @version (a version number or a date)
 */
import java.util.*;

public class Edge
{
    private int node1;
    
    private int node2;
    
    private String type;
    
    private int curWeight;
    
    private int freeWeight;
    
    private int normalWeight;
    
    private int busyWeight;
    /**
     * tree that contains all the cars on this edge
     */
    TreeMap<Integer,Integer> cars = new TreeMap<Integer,Integer>();
    
    /**
     * builds an object with two end points and the type provided. Randomly sets the weights of different states of the edge.
     * @param n1 one end point
     * @param n2 second end point
     * @param t type of the edge
     */
    public Edge(int n1,int n2,String t)
    {
        node1=n1;
        node2=n2;
        type=t;
        Random rand = new Random();
        normalWeight = 5 + rand.nextInt(10);
        freeWeight = normalWeight - rand.nextInt(4);
        busyWeight = normalWeight + rand.nextInt(4);
        if(t=="highway") 
        {
            normalWeight*=2;
            freeWeight*=2;
            busyWeight*=2;
        }
        randomBusyness();
    }
    
    /**
     * randomizes the bussiness of the edge. Changes the time necessary to cover randomly
     */
    public void randomBusyness()
    {
        Random rand = new Random();
        int k=rand.nextInt(4);
        if(k==0) curWeight = freeWeight;
        else 
        if(k==1 || k==2) curWeight = normalWeight;
        else 
            if(k==3) curWeight = busyWeight;
    }
    
    /**
     * sets first node
     * @param n first node
     */
    public void setNode1(int n)
    {
        node1=n;
    }
    /**
     * returns first node
     * @return first node
     */
    public int getNode1()
    {
        return node1;
    }
    /**
     * sets second node
     * @param n second node
     */
    public void setNode2(int n)
    {
        node2=n;
    }
    /**
     * returns second node
     * @return second node
     */
    public int getNode2()
    {
        return node2;
    }
    /**
     * returns the current weight of the node
     * @return current weight of the node
     */
    public int getCurWeight()
    {
        return curWeight;
    }
    /**
     * returns weight when the road is free
     * @return free weight of the node
     */
    public int getFreeWeight()
    {
        return freeWeight;
    }
    /**
     * returns the weight when road is in normal state
     * @return the weight when road is in normal state
     */
    public int getNormalWeight()
    {
        return normalWeight;
    }
    /**
     * returns weight of the road in a busy state
     * @return weight of the road in a busy state
     */
    public int getBusyWeight()
    {
        return busyWeight;
    }
    /**
     * returns type of the road
     * @return type of the road
     */
    public String getType()
    {
        return type;
    }
    /**
     * removes the car from the edge
     * @param ID the id of the car removed
     * @return number of cars on the road after car is removed
     */
    public int removeCar(int ID)
    {
        cars.remove(ID);
        return cars.size();
    }
    /**
     * adds car to the edge
     * @param ID id of the car
     * @return number of cars on the road after car is added
     */
    public int addCar(int ID)
    {
        cars.put(ID,ID);
        return cars.size();
    }
}

