import java.util.Iterator;
import java.util.LinkedList;

public class Graph
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
	void DFS(int v)
	{
		boolean[] visited = new boolean[V];
		DFSUtil(v,visited);
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
		
		System.out.println("DFS starting from vertex 2");
		g.DFS(2);
	}
}