/*
Topological sort
*/

// Time Complexity: The above algorithm is simply DFS with an extra stack. So time complexity is same as DFS which is O(V+E).

import java.util.Iterator;
import java.util.LinkedList;

//Time Complexity: O(V+E) where V is number of vertices in the graph and E is number of edges in the graph.

public class TopologicalSort
{
	private int V; // no. of vertices
	private LinkedList<Integer> adj[]; // Adjacency List representation
	
	TopologicalSort(int v)
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
	// A recursive function used by topologicalSort
    void topologicalSortUtil(int v, boolean visited[], Stack stack)
    {
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;
 
        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext())
        {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }
 
        // Push current vertex to stack which stores result
        stack.push(new Integer(v));
    }
 
    // The function to do Topological Sort. It uses
    // recursive topologicalSortUtil()
    void topologicalSort()
    {
		// stack will store all the nodes which are visited and their children are also visited
        Stack stack = new Stack();
 
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;
 
        // Call the recursive helper function to store
        // Topological Sort starting from all vertices
        // one by one
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack);
 
        // Print contents of stack
        while (stack.empty()==false)
            System.out.print(stack.pop() + " ");
    }
		
   }
	
	public static void main(String[] args)
	{
		TopologicalSort g  = new TopologicalSort(6);
		g.addEdge(5,2);
		g.addEdge(5,0);
		g.addEdge(4,0);
		g.addEdge(4,1);
		g.addEdge(2,3);
		g.addEdge(3,1);
		
		g.topologicalSort();
	}
}