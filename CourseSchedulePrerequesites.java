/*
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

click to show more hints.

Hints:
This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
Topological sort could also be done via BFS.
*/

public class Solution 
{
    public boolean canFinish(int numCourses, int[][] prerequisites) 
    {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        
        Queue queue = new LinkedList();
        int count=0;
        
        // Created adjacency list for all nodes in graph
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();
        
        // filled each slot in adjacency list for each pair u->v    
        for(int i=0; i<prerequisites.length;i++)
		{
			// prerequisites[i][1] -> prerequisite
			// prerequisites[i][0] -> course to be completed
            indegree[prerequisites[i][0]]++;
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        // find multiple roots in the graph for which indegree is 0
        for(int i=0; i<indegree.length;i++)
			{
            if(indegree[i] == 0){
                queue.add(i);
                count++;
            }
        }
		
		// check level-wise
        while(queue.size() != 0){
            int course = (int)queue.poll();
            for(int i=0; i<graph[course].size();i++){
                int pointer = (int)graph[course].get(i);
                indegree[pointer]--;
                if(indegree[pointer] == 0){
                    queue.add(pointer);
                    count++;
                }
            }
        }
        if(count == numCourses)
            return true;
        else    
            return false;
    }     
}