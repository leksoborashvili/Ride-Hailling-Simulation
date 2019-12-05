

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
        Graph graph = new Graph(10,6);
        graph.generateGraph(123);
        ArrayList<Edge> edges = graph.getEdges();
        
        ArrayDeque<Djk> q = new ArrayDeque<Djk>();
        
        
        int visited[] = new int[10];
        
        for(int i=0;i<visited.length;i++)
        {
            visited[i]=0;
        }
        Djk djk = new Djk();
        djk.node = 0;
        visited[djk.node]= 1;
        q.addLast(djk);
        int ans=1;
        while(q.size()>0)
        {
            
            Djk d = q.pollFirst();
            ArrayList<Integer> l = graph.getNeighbors(d.node);
            for(int i=0;i<l.size();i++)
            {
                if(visited[l.get(i)]==1) continue;
                Djk dj = new Djk();
                dj.node = l.get(i);
                visited[l.get(i)]=1;
                ans++;
                q.addLast(dj);
            }
                
        }
        assertEquals(10,ans);
    }
}


