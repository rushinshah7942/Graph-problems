/*
Given a sorted dictionary (array of words) of an alien language, find order of characters in the language.

Examples:

Input:  words[] = {"baa", "abcd", "abca", "cab", "cad"}
Output: Order of characters is 'b', 'd', 'a', 'c'
Note that words are sorted and in the given language "baa" 
comes before "abcd", therefore 'b' is before 'a' in output.
Similarly we can find other orders.

Input:  words[] = {"caa", "aaa", "aab"}
Output: Order of characters is 'c', 'a', 'b'
*/



/*
Explanation: 
------------
The idea is to create a graph of characters and then find topological sorting of the created graph. Following are the detailed steps.

1) Create a graph g with number of vertices equal to the size of alphabet in the given alien language. For example, if the alphabet size is 5, then there can be 5 characters in words. Initially there are no edges in graph.

2) Do following for every pair of adjacent words in given sorted array.
…..a) Let the current pair of words be word1 and word2. One by one compare characters of both words and find the first mismatching characters.
…..b) Create an edge in g from mismatching character of word1 to that of word2.

3) Print topological sorting of the above created graph.


Reference: 	http://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
*/


public String alienOrder(String[] words) 
{
    Map<Character, Set<Character>> map=new HashMap<Character, Set<Character>>();
    Map<Character, Integer> degree=new HashMap<Character, Integer>();
    String result="";
    
	if(words==null || words.length==0) 
		return result;
    for(String s: words)
	{
        for(char c: s.toCharArray())
		{
            degree.put(c,0);
        }
    }
    for(int i=0; i<words.length-1; i++)
	{
        String cur=words[i];
        String next=words[i+1];
		int length=Math.min(cur.length(), next.length());
        
		for(int j=0; j<length; j++)
		{
            char c1=cur.charAt(j);
            char c2=next.charAt(j);
            if(c1!=c2)
			{
                Set<Character> set=new HashSet<Character>();
                if(map.containsKey(c1)) set=map.get(c1);
                if(!set.contains(c2)){
                    set.add(c2);
                    map.put(c1, set);
                    degree.put(c2, degree.get(c2)+1);
                }
                break;
            }
        }
    }
	
    Queue<Character> q=new LinkedList<Character>();
    
	// add starting points in the graph (there can be multiple sources with indegree 0)
	for(char c: degree.keySet())
	{
        if(degree.get(c)==0) q.add(c);
    }
	
    while(!q.isEmpty())
	{
        char c=q.remove();
        result+=c;
        if(map.containsKey(c))
		{
            for(char c2: map.get(c))
			{
                degree.put(c2,degree.get(c2)-1);
                if(degree.get(c2)==0) 
					q.add(c2);
            }
        }
    }
	
    if(result.length()!=degree.size())  // can not determine from given input of words
		return "";
    
	return result;
}