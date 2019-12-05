
/**
 * Write a description of class Runner here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class Runner
{
    public static void main() throws Exception
    {
        Graph graph = new Graph(8,6);
        graph.generateGraph(1230);
        graph.printGraph();
        //System.out.println(graph.timeDijkstra(0,4));
        //ArrayDeque<Integer> path = graph.pathDijkstra(0,4);
        //System.out.println(path.pollLast());
        
        Car car1 = new Car(graph,1);
        Car car2 = new Car(graph,2);
        Car car3 = new Car(graph,3);
        ArrayList<Car> cars = new ArrayList<Car>();
        
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        
        Customer cust1 = new Customer(graph,1,3);
        Customer cust2 = new Customer(graph,2,2);
        
        System.out.println(cust1.getPickup() + " to " + cust1.getDropoff());
        
        //System.out.println(cust2.getPickup() + " to " + cust2.getDropoff());
        int[] distances = graph.listDijkstra(cust1.getPickup());
        Car car = cars.get(0);
        int minID = 0;
        for(int i=0;i<cars.size();i++)
        {
            if(distances[cars.get(i).getDest()] + cars.get(i).getTimeUntilNextPoint() < 
               distances[car.getDest()] + car.getTimeUntilNextPoint() ) {car =cars.get(i); minID = i;}
              
            System.out.println("car " + cars.get(i).getID() + " on "+ cars.get(i).getStart() + " to " + 
                cars.get(i).getDest() + " is " + (distances[cars.get(i).getDest()] 
                + cars.get(i).getTimeUntilNextPoint()) + " away ");
        }
        car.assignCustomer(graph,cust1);
        for(int i=0;i<100;i++)
        {
            
            
            cars.get(0).move(graph);
            cars.get(1).move(graph);
            cars.get(2).move(graph);
            //Thread.sleep(500);
        }
        
        distances = graph.listDijkstra(cust1.getPickup());
        
        for(int i=0;i<cars.size();i++)
        {
            System.out.println("car " + cars.get(i).getID() + " on "+ cars.get(i).getStart() + " to " + 
                cars.get(i).getDest() + " is " + (distances[cars.get(i).getDest()] 
                + cars.get(i).getTimeUntilNextPoint()) + " away ");
        }
        
    }
    
}
