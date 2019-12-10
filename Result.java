
/**
 * sums up wait time, average time for the ride and the number of idle cars per minute.
 *
 * @author Lekso Borashvili
 * @version (a version number or a date)
 */
public class Result
{
    private static int waitTime          = 0;
    private static int rideTime          = 0;
    private static int expectedRideTime  = 0;
    private static int idleCars          = 0;
    private static int time              = 0;
    
    /**
     * adds wait time to total wait time
     * @param wt wait time
     */
    public static void addWaitTime(int wt)
    {
        waitTime += wt;
    }
    /**
     * adds ride time to the total
     * @param rt ride time
     */
    public static void addRideTime(int rt)
    {
        rideTime += rt;
    }
    /**
     * adds expected ride time to the total
     * @ert expected ride time
     */
    public static void addExpectedRideTime(int ert)
    {
        expectedRideTime += ert;
    }
    /**
     * adds idle cars to the total
     * @param cars number of idle cars
     */
    public static void addIdleCars(int cars)
    {
        idleCars += cars;
    }
    /**
     * sets the time 
     * @param t time
     */
    public static void setTime(int t)
    {
        time = t;
    }
    /**
     * prints all the averages in by calling writer class which is already set up outside of this class
     * @param graphSize graph size
     * @param density chance of 2 nodes being connected
     * @param numberofCars number of cars
     * @param customerChance chance of customer spawn
     * @param numberofCustomers number of customers
     */
    public static void printData(int graphSize,int density, int numberofCars, int customerChance, int numberofCustomers) throws Exception
    {
        String s = Integer.toString(graphSize)                            + ",";
        s       += Integer.toString(density)                              + ",";
        s       += Integer.toString(numberofCars)                         + ",";
        s       += Integer.toString(customerChance)                       + ",";
        s       += Integer.toString(numberofCustomers)                    + ",";          
        s       += Integer.toString(waitTime / numberofCustomers)         + ",";
        s       += Integer.toString(rideTime / numberofCustomers)         + ",";
        s       += Integer.toString(expectedRideTime / numberofCustomers) + ",";
        s       += Integer.toString(idleCars / time)              ;
        waitTime          = 0;
        rideTime          = 0;
        expectedRideTime  = 0;
        idleCars          = 0;
        time              = 0;
        System.out.println(s);
        Writer.writeCSV(s);
        Writer.nextLine();
    }
}
