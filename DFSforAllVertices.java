// Following implementation does the complete graph traversal even if the nodes are unreachable.

import java.util.Iterator;
import java.util.LinkedList;

public class GraphDFS
{
	private int V; // no. of vertices
	private LinkedList<Integer> adj[]; // Adjacency List representation
	
	GraphDFS(int v)
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
	
	// helper function for DFSUtil
	void DFSUtil(int v, boolean[] visited) 
	{
		visited[v] = true;
		System.out.println(v + " ");
		
		Iterator<Integer> it = adj[v].listIterator();
		while(it.hasNext())
		{
			int next = it.next();
			if(!visited[next])
				DFSUtil(next, visited);
		}
	}
	
	/*
	void DFS(int v)
	{
		boolean[] visited = new boolean[V];
		DFSUtil(v,visited);
	}*/
	
	void DFS()
    {
        boolean visited[] = new boolean[V];
 
        // Call the recursive helper function to print DFS traversal
        // starting from all vertices one by one
        for (int i=0; i<V; ++i)
            if (visited[i] == false)
                DFSUtil(i, visited);
    }
  
	
	public static void main(String[] args)
	{
		GraphDFS g  = new GraphDFS(4);
		g.addEdge(0,1);
		g.addEdge(0,2);
		g.addEdge(1,2);
		g.addEdge(2,0);
		g.addEdge(2,3);
		g.addEdge(3,3);
		
		g.DFS();
	}
}