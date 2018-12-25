
package aab180004;

import rbk.Graph.Vertex;
import rbk.Graph;
import rbk.Graph.Edge;
import rbk.Graph.GraphAlgorithm;
import rbk.Graph.Factory;

import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The class represents the implementation of Depth first Search 
 * @author Mythri Thippareddy, Achyut Bhandiwad
 *
 */
public class DFS extends GraphAlgorithm<DFS.DFSVertex> {

	private LinkedList<Vertex> finishList; // Output of dfs() will be stored in finishList
	public static boolean isCyclic; // isCyclic check
	public int numberOfConnectedComponents; // The number of connected components of the graph as 0;
	private int topNum; // 	Used for dfs()

	/**
	 * @author Mythri Thippareddy, Achyut Bhandiwad
	 *
	 */
	public static class DFSVertex implements Factory {
		int cno; // component number
		public boolean seen; // check if the vertex is seen
		public Vertex parent; // storing the parent of the vertex
		int top; // storing the top number to check for back edges

		/**
		 * @param u
		 * converting vertex u to DFSVertex
		 */
		public DFSVertex(Vertex u) {
			this.seen = false; 
			this.parent = null;
			this.top = 0;
			this.cno = 0;
		}

		/* 
		 * @see rbk.Graph.Factory#make(rbk.Graph.Vertex)
		 */
		public DFSVertex make(Vertex u) {
			return new DFSVertex(u);
		}
	}

	/**
	 * @param g 
	 * Initialing g with numberOfConnectedComponents and isCyclic
	 */
	public DFS(Graph g) {
		super(g, new DFSVertex(null));
		isCyclic = false;
		numberOfConnectedComponents=0;
	}

	/**
	 * @param g
	 * @return DFS object with the objects ordered
	 */
	public static DFS depthFirstSearch(Graph g) {
		DFS d = new DFS(g);
		d.dfs(g);
		return d;
	}

	public void dfs(){
	    dfs(g);
    }

	/**
	 * Dfs method to run depth first search
	 * @param iterable
	 */
	private void dfs(Iterable<Vertex> iterable) {
		topNum = g.size();
		numberOfConnectedComponents = 0;
		for (Vertex u : g) {
			get(u).seen = false;
			get(u).parent = null;
			get(u).top = 0;
			get(u).cno = 0;
		}
		finishList = new LinkedList<Vertex>(); //initialising finishList
		for (Vertex u : iterable) { //using the iterable to iterate through the graph
			if (!get(u).seen) {
				get(u).cno = ++numberOfConnectedComponents;
				dfsVisit(u);
			}
		}
	}

	/**
	 * @param u
	 * Visiting each node adjacent to Vertex u
	 */
	private void dfsVisit(Vertex u) {
		get(u).seen = true;
		for (Edge e : g.incident(u)) {
			Vertex v = e.otherEnd(u);
			if (!get(v).seen) {
				get(v).parent = u;
				get(v).cno = get(u).cno;
				dfsVisit(v);
			} else {
				if (get(v).top == 0) {
					isCyclic = true;
				}
			}
		}
		get(u).top = topNum--;
		finishList.addFirst(u);
	}

	
	/**
	 * @return list of vertices in topological order
	 */
	public List<Vertex> topologicalOrder1() {
		dfs(g);
		if(isCyclic) {
			return null;
		}
		return finishList;
	}
	
	/**
	 * @return the number of connected components
	 */
	public int connectedComponents() {
		dfs(g);
		return numberOfConnectedComponents;
	}

	/**
	 * @param u
	 * @return the component number of the vertex u
	 */
	public int cno(Vertex u) {
		return get(u).cno;
	}

	/**
	 * @param g
	 * @return Returns null if g is not a DAG else return the topological oder of a DAG using DFS. 
	 */
	public static List<Vertex> topologicalOrder1(Graph g) {
		DFS d = new DFS(g);
		return d.topologicalOrder1();
	}

	/**
	 * Method to find strongly connected components of a directed graph
	 * @param g - Graph
	 * @return DFS
	 */
	public static DFS stronglyConnectedComponents(Graph g){
		DFS d = new DFS(g);
		d.dfs(g); //first DFS using graph
		List<Vertex> list = d.finishList;
		g.reverseGraph();
		d.dfs(list); //second DFS using the finish list of first DFS
		g.reverseGraph(); //restoring the graph
		return d;
	}

	/**
	 * Print the components of the graph
	 */
	public void printComponents(){
		System.out.println("Number of components: " + numberOfConnectedComponents + "\nu\tcno");
		for (Vertex u : finishList) {
			System.out.println(u + "\t" + cno(u));
		}
		System.out.println("");
	}

	public static void main(String[] args) throws Exception {
		//input is taken from the problem solved in class notes (LEC 22)
		String string = "11 17   1 11 0   2 7 0   2 3 0   3 10 0   4 9 0   4 1 0   5 4 0   5 8 0   5 7 0   6 3 0   7 8 0   8 2 0   9 11 0   10 6 0   11 3 0   11 6 0   11 4 0 0";
		Scanner in;
		// If there is a command line argument, use it as file from which
		// input is read, otherwise use input from string.
		in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);

		// Read graph from input
		Graph g = Graph.readGraph(in,true); //directed graph
		g.printGraph(false);

		DFS scc = DFS.stronglyConnectedComponents(g);
		scc.printComponents();

	}
}
