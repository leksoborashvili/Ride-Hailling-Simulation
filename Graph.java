
/**
 * This class wraps up the graph and contains all the methods with operations on the graph
 *
 * @author Lekso Borashvili
 * @version (a version number or a date)
 */
import java.util.*;
public class Graph
{
    private int nodes;
    
    private TreeMap<Integer,ArrayList<Integer>> listAdj = new TreeMap<Integer,ArrayList<Integer>>();
    
    private int density;
    
    /**
     * all the edges in the graph
     */
    ArrayList<Edge> edges = new ArrayList<Edge>();
    
    //this class is implemented by dijkstra algorithm to keep track of node,prev Node and distance to the current node.
    private class Djk implements Comparable<Djk>
    {
        int node;
        int prev;
        Integer dist;
        public int compareTo(Djk a)
        {
            return this.dist.compareTo(a.dist);
        }
    }
    
    /**
     * constructor builds graph with n nodes and given density 
     * @param n number of nodes
     * @param d the probability of nodes being connected
     */
    public Graph(int n,int d)
    {
        nodes=n;
        density=d;
        for(int i=0;i<n;i++)
        {
            ArrayList<Integer> list = new ArrayList<Integer>();
            listAdj.put(i,list);
        }
    }
    /**
     * connects two nodes "Creates Street"
     * @param node1 one end of the edge
     * @param node2 second end of the edge
     * @param type type of the street Regular/highway
     * @return true if the nodes are connected, false if they are already connected or impossible to connect
     */
    public boolean connect(int node1,int node2,String type)
    {
        ArrayList list1 = listAdj.get(node1);
        ArrayList list2 = listAdj.get(node2);
        //if already connected return false
        if(list1.contains(node2)) return false;
        if(node1==node2) return false;
        //if one of these nodes have more than 5 edges then they cannot be connected
        if(list1.size()>=5 || list2.size()>=5) return false;
        list1.add(node2);
        list2.add(node1);
        Edge edge = new Edge(node1,node2,type);
        edges.add(edge);
        listAdj.replace(node1,list1);
        listAdj.replace(node2,list2);
        return true;
    }
    /**
     * generates graph, creates edges between the given number of nodes
     * @param seed seed helps us to control the variations since the streets are random
     */
    public void generateGraph(int seed)
    {
        Random rand = new Random(seed);
        for(int i=0;i<nodes;i++)
        {
            ArrayList<Integer> list = listAdj.get(i);
            
            for(int j=0;j<5;j++) 
            {
                int neighborNode = rand.nextInt(nodes);
                int dNumber = rand.nextInt(30);
                //dNumber is the random number from 1 to 30 that is compared to density to see if the nodes are connected
                if(dNumber<density) connect(i,neighborNode,"regular");
                list = listAdj.get(i);
            }
        }
        
        // we need to run kruskal to make sure the graph is connected
        ArrayList<TreeSet<Integer>> sets = new ArrayList<TreeSet<Integer>>();
        
        for(int i=0;i<nodes;i++)
        {
            TreeSet<Integer> set = new TreeSet<Integer>();
            set.add(i);
            sets.add(set);
        }
        int j=0;
        //this is kruskal algorithm
        while(j<edges.size() && sets.size()>1)
        {
            Edge edge = edges.get(j);
            int loc1 = -1;
            int loc2 = -1;
            
            for(int i=0;i<sets.size();i++)
            {
                if(sets.get(i).contains(edge.getNode1())) loc1 = i;
                if(sets.get(i).contains(edge.getNode2())) loc2 = i;
                //System.out.println(i);
            }
            
            if(loc1==loc2) {j++;continue;}
            
            for(Integer k: sets.get(loc2))
            {
                sets.get(loc1).add(k);
                
            }
            sets.remove(loc2);
           
            j++;
        }
        //if after running kruskals algorithm we get just one set of nodes this means the graph is connected
        if(sets.size()==1) {return;}
        //if we have more than one sets we need to merge them by connecting nodesbetween them.
        while(sets.size()>1)
        {
           connect(sets.get(0).first(),sets.get(1).first(),"highway");
           sets.remove(1);
        }
        
        if(sets.size()==1) {return;}
    }
    
    /**
     * calculates minimum time that car can reach from one point to another
     * @param nodeA first point
     * @param nodeB second point
     */
    public int timeDijkstra(int nodeA,int nodeB)
    {
        if(nodeA>=nodes || nodeB>=nodes) return -1;
        PriorityQueue<Djk> q = new PriorityQueue<Djk>();
        Djk[] ans = new Djk[nodes];
        for(int i=0;i<nodes;i++)
        {
            Djk djk1 = new Djk();
            djk1.node=i;
            djk1.prev = -1;
            djk1.dist=999999;
            ans[i]=djk1;
            q.offer(djk1);
        }
        Djk djk = new Djk();
        djk.node = nodeA;
        djk.prev = -1;
        djk.dist=0;
        ans[nodeA]=djk;
        q.offer(djk);
        
        while(q.size()>0)
        {
            Djk d = q.poll();
            ArrayList list = listAdj.get(d.node);
            for(int i=0;i<list.size();i++)
            {
                Edge edge = getEdge(d.node,(Integer)list.get(i));
                if(edge.equals(null)) continue;
                
                int dest = (Integer)list.get(i);
                
                int altDistance = ans[d.node].dist + edge.getCurWeight();
                
                if(altDistance >= ans[dest].dist) continue;
                
                ans[dest].dist=altDistance;
                ans[dest].prev=d.node;
                q.offer(ans[dest]);
                
            }
            
        }
        return ans[nodeB].dist;
    }
    /**
     * calculates the shortest path from point to every other point and returns the array
     * @param nodeA starting point
     * @return array of shortest ditances from certain point
     */
    public int[] listDijkstra(int nodeA)
    {
        if(nodeA>=nodes) return null;
        PriorityQueue<Djk> q = new PriorityQueue<Djk>();
        Djk[] ans = new Djk[nodes];
        for(int i=0;i<nodes;i++)
        {
            Djk djk1 = new Djk();
            djk1.node=i;
            djk1.prev = -1;
            djk1.dist=999999;
            ans[i]=djk1;
            q.offer(djk1);
        }
        Djk djk = new Djk();
        djk.node = nodeA;
        djk.prev = -1;
        djk.dist=0;
        ans[nodeA]=djk;
        q.offer(djk);
        
        while(q.size()>0)
        {
            Djk d = q.poll();
            ArrayList list = listAdj.get(d.node);
            for(int i=0;i<list.size();i++)
            {
                Edge edge = getEdge(d.node,(Integer)list.get(i));
                if(edge.equals(null)) continue;
                
                int dest = (Integer)list.get(i);
                
                int altDistance = ans[d.node].dist + edge.getCurWeight();
                
                if(altDistance >= ans[dest].dist) continue;
                
                ans[dest].dist=altDistance;
                ans[dest].prev=d.node;
                q.offer(ans[dest]);
                
            }
            
        }
        int[] ans1 = new int[nodes];
        for(int i=0;i<nodes;i++)
        {
            ans1[i]=ans[i].dist;
        }
        return ans1;
    }
    /**
     * caluclates and returns the shortest path from one point to another
     * @param nodeA first point
     * @param nodeB second point
     * @return shortest path from nodeA to nodeB
     */
    public ArrayDeque<Integer> pathDijkstra(int nodeA,int nodeB)
    {
        if(nodeA>=nodes || nodeB>=nodes) return null;
        PriorityQueue<Djk> q = new PriorityQueue<Djk>();
        Djk[] ans = new Djk[nodes];
        for(int i=0;i<nodes;i++)
        {
            Djk djk1 = new Djk();
            djk1.node=i;
            djk1.prev = -1;
            djk1.dist=999999;
            ans[i]=djk1;
            q.offer(djk1);
        }
        Djk djk = new Djk();
        djk.node = nodeA;
        djk.prev = -1;
        djk.dist=0;
        ans[nodeA]=djk;
        q.offer(djk);
        
        while(q.size()>0)
        {
            Djk d = q.poll();
            ArrayList list = listAdj.get(d.node);
            for(int i=0;i<list.size();i++)
            {
                Edge edge = getEdge(d.node,(Integer)list.get(i));
                if(edge.equals(null)) continue;
                
                int dest = (Integer)list.get(i);
                
                int altDistance = ans[d.node].dist + edge.getCurWeight();
                
                if(altDistance >= ans[dest].dist) continue;
                
                ans[dest].dist=altDistance;
                ans[dest].prev=d.node;
                q.offer(ans[dest]);
                
            }
            
        }
        ArrayDeque<Integer> path = new ArrayDeque<Integer>();
        path.addLast(nodeB);
        int cur = nodeB;
        while(true)
        {
            if(ans[cur].prev==-1) break;
            path.addLast(ans[cur].prev);
            cur=ans[cur].prev;
        }
        return path;
    }
    /**
     * return the number of nodes
     * @return number of nodes
     */
    public int getNodes()
    {
        return nodes;
    }
    /**
     * returns the edge between two nodes
     * @param node1 first node
     * @param node2 second node
     * @return Edge if there is one or else null
     */
    public Edge getEdge(int node1,int node2)
    {
        
        for(int i=0;i<edges.size();i++)
        {
            if(edges.get(i).getNode1()==node1 && edges.get(i).getNode2()==node2) return edges.get(i);
            if(edges.get(i).getNode1()==node2 && edges.get(i).getNode2()==node1) return edges.get(i);
        }
        return null;
    }
    
    /**
     * removes the car from the street with ends given
     * @param start one end of the street
     * @param end the other end of the street
     * @param ID id of the car to be removed
     * @return the number of cars on the street after removal
     */
    public int removeCar(int start,int end,int ID)
    {
        if(start==end) return -1;
        Edge edge = getEdge(start,end);
        if(edges.indexOf(edge)==-1) return -1;
        return edges.get(edges.indexOf(edge)).removeCar(ID);
    }
    
    /**
     * adds car on the street with given ends 
     * @param start one end of the street
     * @param end the other end of the street
     * @param ID of the car
     * @return number of cars on the street after insserting the car
     */
    public int addCar(int start,int end,int ID)
    {
        if(start==end) return -1;
        return edges.get(edges.indexOf(getEdge(start,end))).addCar(ID);
        
    }
    
    /**
     * selects random neighbor of the node
     * @param node the starting node
     * @return random neighboring nod
     */
    public int generateNeighborForFreeMove(int node)
    {
        ArrayList<Integer> list = listAdj.get(node);
        Random rand = new Random();
        int d = list.get(rand.nextInt(list.size()));
        return d;
        
    }
    /**
     * generates drop location. This can not be more than provided number of nodes away from the start
     * @param start the starting node
     * @param bfs how far dropping location should be
     */
    public int dropLocation(int start, int bfs)
    {
        ArrayDeque<Djk> q = new ArrayDeque<Djk>();
        ArrayList<Integer> l = new ArrayList<Integer>();
        //keeps track of visited nodes
        int visited[] = new int[nodes];
        
        for(int i=0;i<visited.length;i++)
        {
            visited[i]=0;
        }
        
        Djk djk = new Djk();
        djk.node=start;
        djk.dist = 0;
        q.addLast(djk);
        visited[start]= 1;
        
        
        
        while(q.size()>0)
        {
            Djk d = q.pollFirst();
            
            ArrayList<Integer> list = listAdj.get(d.node);
            
            for(int i=0;i<list.size();i++)
            {
                //System.out.println(list.get(i) + " " + visited[list.get(i)]);
                if(visited[list.get(i)] == 0) 
                {
                    visited[list.get(i)] = 1;
                    
                    Djk newD = new Djk();
                    newD.node = list.get(i);
                    newD.dist = d.dist+1;
                    q.addLast(newD); 
                    //all the nodes that are bfs th node from the start are kept in an array
                    if(newD.dist == bfs) {l.add(list.get(i)); }
                    
                    
                }
            }
            
        }
        Random rand = new Random();
        if(l.size()==0) return -1;
        //random node from all the possible nodes is returned
        return l.get(rand.nextInt(l.size()));
        
    }
    /**
     * return arrayList of edges
     * @return edges
     */
    public ArrayList<Edge> getEdges()
    {
        return edges;
    }
    
    /**
     * returns neighbors of the node
     * @param a starting node
     * @return arraylist of neighbors to the starting poing
     */
    public ArrayList<Integer> getNeighbors(int a)
    {
        return listAdj.get(a);
    }
    
    /**
     * prints the graph edges
     */
    public void printGraph()
    {
        for(int i=0;i<edges.size();i++)
        {
            System.out.println(edges.get(i).getNode1() + " " + edges.get(i).getNode2() + " " + 
                edges.get(i).getType() + " " + edges.get(i).getCurWeight());
        }
    }
}