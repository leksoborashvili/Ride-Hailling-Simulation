

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
/**
 * The test class GraphTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GraphTest
{
    /**
     * Default constructor for test class GraphTest
     */
    public GraphTest()
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
    class Djk {
        int node;
        int prev;
        Integer dist;
    }
    @Test
    public void isConnected()
    {
        Graph graph           = new Graph(10,6);
        graph                 . generateGraph(123);
        ArrayList<Edge> edges = graph.getEdges();
        
        ArrayDeque<Djk> q     = new ArrayDeque<Djk>();
        
        
        int visited[]         = new int[10];
        
        for(int i=0;i<visited.length;i++)
        {
            visited[i]=0;
        }
        Djk djk           = new Djk();
        djk.node          = 0;
        visited[djk.node] = 1;
        q                 . addLast(djk);
        int ans           = 1;
        while(q.size()>0)
        {
            
            Djk d                 = q.pollFirst();
            ArrayList<Integer> l  = graph.getNeighbors(d.node);
            for(int i=0;i<l.size();i++)
            {
                if(visited[l.get(i)]==1) continue;
                Djk dj            = new Djk();
                dj.node           = l.get(i);
                visited[l.get(i)] = 1;
                ans               ++;
                q.addLast(dj);
            }
                
        }
        assertEquals(10,ans);
    }

    @Test
    public void GetNeighbors()
    {
        Graph graph1            = new Graph(10, 6);
        graph1                  . generateGraph(123);
        ArrayList<Integer> list = graph1.getNeighbors(1);
        int x                   = list.get(0);
        assertEquals(x,0);
        

    }
    
    

    @Test
    public void dijkstraTest1()
    {
        Graph graph1      = new Graph(10, 40);
        graph1            . generateGraph(123);
        int[] list        = graph1.listDijkstra(0);
        assertEquals(list[9], graph1.timeDijkstra(0, 9));
    }
    
    @Test
    public void graph1()
    {
        Graph graph1  = new Graph(10,40);
        graph1.generateGraph(123);
        
        Edge edge     = graph1.getEdge(0,0);
        assertEquals(null,edge);
    }
    
    @Test
    public void graph2()
    {
        Graph graph1  = new Graph(10,40);
        graph1.generateGraph(123);
        
        assertEquals(1,graph1.addCar(0,3,1));
        assertEquals(0,graph1.removeCar(0,3,1));
    }
    
    @Test
    public void graph3()
    {
        Graph graph1           = new Graph(10,40);
        graph1.generateGraph(123);
        ArrayList<Integer> nei = graph1.getNeighbors(0);
        assertEquals(nei.contains(graph1.generateNeighborForFreeMove(0)),true);
        
    }
}




