// Following implementation does the complete graph traversal even if the nodes are unreachable.

import java.util.Iterator;
import java.util.LinkedList;

public class GraphDFS
{
	private int V; // no. of vertices
	private LinkedList<Integer> adj[]; // Adjacency List representation
	
	Graph(int v)
	{
		V = v;
		adj = new LinkedList[v];
		for(int i=0;i<v;i++)
			adj[i] = new LinkedList<>();
	}
	
	// add edge for u->v
	void addEdge(int u, int v)
	{
		adj[u].add(v);
	}
	
	// prints BFS traversal from a given source s
    void BFS(int s)
    {
        // Mark all the vertices as not visited(By default set as false)
        boolean visited[] = new boolean[V];
 
        // Create a queue for BFS
        Queue<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s+" ");
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
	
	public static void main(String[] args)
	{
		Graph g  = new Graph(4);
		g.addEdge(0,1);
		g.addEdge(0,2);
		g.addEdge(1,2);
		g.addEdge(2,0);
		g.addEdge(2,3);
		g.addEdge(3,3);
		
		g.BFS(2);
	}
}