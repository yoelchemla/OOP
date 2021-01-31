package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Weighted_Graph_Algo implements  weighted_graph_algorithms   {

	weighted_graph a = new WGraph_DS(); // create a new garph (a).

	/**
	 * Init the graph on which this set of algorithms operates on.
	 */
	@Override
	public void init(weighted_graph g) {
		this.a = g;
	}

	/**
	 * Return the underlying graph of which this class works.
	 */
	@Override
	public weighted_graph getGraph() {
		return this.a;
	}

	/**
	 * Compute a deep copy of this weighted graph
	 */
	@Override
	public weighted_graph copy() {
		weighted_graph graph_Copy = new WGraph_DS();
		if (a == null) {
			return null;
		}
		for (node_info node : a.getV()) { //for each -> all the neighbors
			graph_Copy.addNode(node.getKey());
		}
		for (node_info node : a.getV()) {
			for (node_info obj : a.getV(node.getKey())) {
				graph_Copy.connect(node.getKey(), obj.getKey(), a.getEdge(node.getKey(), obj.getKey()));
			}
		}
		return graph_Copy;
	}

	/**
	 * Returns true if and only if (iff) there is a valid path 
	 * from EVREY node to each other node
	 */
	@Override
	public boolean isConnected() {
		if(a.nodeSize()<=1 || a == null) { 							// graph is null / nodes <= 1
			return true;
		}
		int counter = 0;	
		Queue<node_info> queue = new LinkedList<node_info>();	 // new q
		Iterator<node_info> iterator = a.getV().iterator(); 	// new iterator
		node_info cur = iterator.next(); 					   // current start with the point of the iterator
		queue.add(cur); 									  //enqueue

		while(!queue.isEmpty()) {							//q isn't empty
			cur = queue.poll();							   //remove the current from the q
			for(node_info next: a.getV(cur.getKey())){ 	  // for-each
				if(next.getTag() != 1) {				 // if i didnt visited
					queue.add(next);					// enqueue
					next.setTag(1);					   // set the tag to 1
					counter++;						  // counter of the visit ++
				}
			}
			if(counter == a.nodeSize()) return true;// if i visited at all the nodes the grapgh is connected
		}
		return false;

	}
	/**
	 * returns the length of the shortest path between src to dest 
	 * Note: if no such path --> returns -1
	 * attentions: *********line 183 the method********* 
	 */
	@Override
	public double shortestPathDist(int src, int dest) {

		// mikre katze
		if(src == dest) return -1; // 1 node
		if(a.getV() == null) return -1; // haven't a neighbors 
		if(a.getV(src) == null || a.getV(dest) == null) return -1;  // the neighbors of src/dst = null
		if(a.getNode(src) == null ||a.getNode(dest) == null) return -1;	// the src/dst = null

		//call to the method
		dijkstraAlgo(src,dest); 

		// cast the kinkedList to double type
		double res = a.getNode(dest).getTag();
		return res ;
	}
	/**
	 * returns the the shortest path between src to dest - as 
	 * an ordered List of 
	 * nodes: src--> n1-->n2-->...dest
	 */
	@Override
	public List<node_info> shortestPath(int src, int dest) {

		// mikre katze
		if(src == dest) return null;
		if(a.getV() == null) return null;
		if(a.getV(src) == null || a.getV(dest) == null) return null;
		if(a.getNode(src) == null ||a.getNode(dest) == null) return null;


		WGraph_DS new_graph = new WGraph_DS();			  			 	    // a new graph
		List<node_info> res = new LinkedList<>();            			   // a new linkedList
		List<node_info> t= dijkstraAlgo(src,dest);	   	  				  // list run on all the graph

		for(node_info node : t)  						   				 //for each
		{
			new_graph.addNode(node.getKey());                          //add a node
			new_graph.getNode(node.getKey()).setTag(node.getTag());   // getthe node and setter         
			new_graph.getNode(node.getKey()).setInfo(node.getInfo());// get the node and set his info                  
			res.add(new_graph.getNode(node.getKey()));			    // add the new graph 
		}
		return res;
	}
	/**
	 * Saves this weighted (undirected) graph to the given file name
	 */
	@Override
	public boolean save(String file) {

		try {
			FileOutputStream file_1 = new FileOutputStream(file, true);
			ObjectOutputStream my_graph = new ObjectOutputStream(file_1);
			my_graph.writeObject(a);
			file_1.close();
			my_graph.close();
			return true;
			}
		catch (FileNotFoundException ans) {
			System.out.println("The file is not found"); // any output
			return false;
			}
		catch (IOException ans) {
			ans.printStackTrace();
			return false;
		}
	}

	/**
	 * This method load a graph to this graph algorithm. 
	 * if the file was successfully loaded - the underlying graph of this class 
	 * will be changed (to the loaded one), in case the graph was not loaded the 
	 * original graph should remain "as is".
	 */
	@Override
	public boolean load(String file) {
		try {
			FileInputStream file2 = new FileInputStream(file);
			ObjectInputStream my_graph2 = new ObjectInputStream(file2);
			a = (weighted_graph) my_graph2.readObject(); // casting 
			file2.close();
			my_graph2.close();
			return true;
			}
		catch (IOException | ClassNotFoundException res) {
			res.printStackTrace();
			return false;
		}

	}



	public List<node_info> dijkstraAlgo (int src, int dest){

		PriorityQueue <node_info> queue = new PriorityQueue <node_info>(); 			 // a new PriorityQueue
		LinkedList <node_info> visit = new LinkedList <>();					    // a new linkedlist
		HashMap<node_info, node_info> route = new HashMap<node_info, node_info>(); // a new hashmap
		node_info now = a.getNode(src);											  //the current, start from the src
		now.setTag(0);			
		queue.add(now);														     // add the current to the q

		while(now.getKey() != 0 && !queue.isEmpty()) {		//q isn't empty and i didn't visited at  the current yet
			now = queue.poll();                            //dequeue
			if(!visit.contains(now)) {					  //the linkedList dont contain the current
				visit.add(now);							 // add to the list(visit) the current
				for(node_info theNext : a.getV(now.getKey())) { // for each
					if (!visit.contains(theNext)) { 			// the linkedList dont contain the next
						if(theNext.getTag() == 0) {			   //didn't visited yet
							theNext.setTag(Double.MAX_VALUE); // start him to infinity+
						}
						double path;
						path = a.getEdge(theNext.getKey(), now.getKey()) + now.getTag();
						if(path< theNext.getTag()) { //condition the path < tag of next
							theNext.setTag(path);	// set the tag of next in value of the path
							queue.add(theNext);	   //enqueu
							theNext.setTag(path); //and set the tag of next in value of the path again	
							route.put(theNext, now); // add to the hashmap
						}
					}
				}
			}
		}
		LinkedList<node_info> res = new LinkedList<>();  	// a new linkedList 
		now = a.getNode(dest);							   // current = destinition 
		while(now.getKey() != src) {					  //while i didn't arrived to the start( source)
			res.addFirst(now);	 						 // Inserts the current at the beginning of this list res


			now = route.get(now);					   // get the current from the hashmap(route)
		}
		res.addFirst(now); 							  //add the current to the hashmap(route)
		return res ;
	}
}
