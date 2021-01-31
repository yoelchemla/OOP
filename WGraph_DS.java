package src;

import java.util.Collection;
import java.util.HashMap;
import java.util.*;

public class WGraph_DS implements weighted_graph {

	public HashMap <Integer,HashMap<Integer,Double>> map;
	private HashMap<Integer, node_info> map2;
	private static int countMC ; // count action
	private static int count_edge ; // count edge
	private static double weight ; // weight

	// consructor

	public WGraph_DS() {
		map = new HashMap<>();
		map2 = new HashMap<>();
		countMC = 0;
		count_edge = 0;
		weight = 0;
	}
	/**
	 * return the node_data by the node_id,
	 */
	@Override
	public node_info getNode(int key) { // hashmap of the nodes ->map2
		if (map2.get(key)== null) {
			return null;
		}
		return map2.get(key);
	}
	/**
	 * return true iff (if and only if) there is an
	 *  edge between node1 and node2 
	 * Note: this method should run in O(1) time.
	 */
	@Override 
	public boolean hasEdge(int node1, int node2) {
		if(map.get(node1).containsKey(node2) && map.get(node2).containsKey(node1)) { 
			//if the nodes contains
			//the second and the opposite--> 
			//has edge between the nodes
			return true;
		}
		return false;
	}
	/**
	 * return the weight if the edge (node1, node1).
	 *  In case there is no such edge - should return -1 
	 * Note: this method should run in O(1) time.
	 */
	@Override
	public double getEdge(int node1, int node2) {
		if(!hasEdge(node1, node2)|| node1 == node2) { //if don't have a edge between or is a same node
			return -1;
		}

		return map.get(node1).get(node2) ;
	}
	/**
	 * add a new node to the graph with the given key.
	 *  Note: this method should run in O(1) time.
	 *  Note2: if there is already a node with such a key -> no action should 
	 *  be performed.
	 */
	@Override
	public void addNode(int key) {
		if(map.containsKey(key))return;   //if the specific node exist -> break
		else {
			node_info n = new NodeInfo(key); // new node 
			map2.put(key, n); // add to hashmap
			HashMap<Integer, Double> p = new HashMap<>();// new hashmap
			map.put(key, p);// add the key and the new hashmap to the old hashmao
			countMC++; // counter +
		}
	}

	/**
	 * Connect an edge between node1 and node2, with an edge with weight >=0. 
	 * Note: this method should run in O(1) time. 
	 * Note2: if the edge node1-node2 already exists - the method
	 *  simply updates the weight of the edge.
	 */
	@Override
	public void connect(int node1, int node2, double w) {
		if (node1 == node2)return;

		else if (!hasEdge(node1, node2)) { //if haven't has edge between the nodes
			map.get(node1).put(node2, w);
			map.get(node2).put(node1, w);
			countMC++;
			count_edge++;
		}
		else {								// has edge -> w = wehight
			if(map.get(node1).get(node2) == w){
				return;
			}
			else {							//has edge -> w != wehight, update w
				map.get(node1).put(node2, w);
				map.get(node2).put(node1, w);
				countMC++;
			}
		}			

	}
	/**
	 * This method return a pointer (shallow copy) for a Collection
	 *  representing all the nodes in the graph.
	 *  Note: this method should run in O(1) time
	 */
	@Override
	public Collection<node_info> getV() { // collection of all the nodes( neighbors)
		return  map2.values();
	}
	/**
	 * This method returns a Collection containing all the nodes 
	 * connected to node_id
	 * Note: this method can run in O(k) time, k - being the degree of node_id.
	 */
	@Override
	public Collection<node_info> getV(int node_id) {
		Collection<node_info> t = new LinkedList<>(); // a new linkedList collection
		for( int i: map.get(node_id).keySet()) {  	 // for each
			node_info n = map2.get(i);				// new node- index run 
			t.add(n);							   //add the new node to the linkedList
		}
		return t;    							 // return the collection linkedList
	}
	/**
	 * Delete the node (with the given ID) from the graph - and 
	 * removes all edges which starts or ends at this node. 
	 * This method should run in O(n), |
	 * V|=n, as all the edges should be removed.
	 */
	@Override
	public node_info removeNode(int key) {
		if (map2.containsKey(key)) {
			Iterator<node_info> iterator = this.getV(key).iterator();// new iteartor
			while (iterator.hasNext()) {         					// has a more objects
				node_info tmp = iterator.next();				   // a new node-> the next in the iterator
				this.removeEdge(tmp.getKey(), key);               //remove edge
			}
			node_info en = map2.get(key);
			count_edge = count_edge - map.get(key).size();
			countMC = countMC + map.get(key).size();
			map.remove(en);
			return map2.remove(key);
		} 

		return null;
	}
	/**
	 * Delete the edge from the graph, Note: this
	 *  method should run in O(1) time.
	 */
	@Override
	public void removeEdge(int node1, int node2) {
		if(!hasEdge(node1, node2)) {  // hasn't edge brake
			return;
		}
		else {           // has edge-> remove
			map.get(node1).remove(node2);
			map.get(node2).remove(node1);
		}
		countMC++;
		count_edge--;
	}
	/**
	 * return the number of vertices (nodes) in the graph.
	 *  Note: this method should run in O(1) time.
	 */
	@Override 
	public int nodeSize() {   //size of the hashmap
		return map.size();
	}
	/**
	 * return the number of edges (undirectional graph).
	 *  Note: this method should run in O(1) time.
	 */
	@Override
	public int edgeSize() { // counter of the edges
		return count_edge;
	}
	/**
	 * return the Mode Count - for testing changes in the graph. 
	 * Any change in the inner state of the graph 
	 * should cause an increment in the ModeCount
	 */
	@Override
	public int getMC() {// counter of actions
		return countMC;
	}

	private class NodeInfo implements node_info{

		private int key;
		private double tag;
		private String info;

		//constructor
		public NodeInfo(int key) {  
			this.key = key;
			this.tag = 0;
			this.info = " ";
		}
		/**
		 * Return the key (id) associated with this node.
		 *  Note: each node_data should have a unique key.
		 */
		@Override
		public int getKey() { // getter
			return this.key;
		}
		/**
		 * return the remark (meta data) associated with this node.
		 */
		@Override
		public String getInfo() { // getter
			return this.info;
		}
		/**
		 * Allows changing the remark (meta data) associated with this node.
		 */
		@Override
		public void setInfo(String s) { //setter
			s = this.info;
		}
		/**
		 * Temporal data (aka distance, color, or state) which can be used be algorithms
		 */
		@Override
		public double getTag() {    // getter
			return this.tag;
		}
		/**
		 * Allow setting the "tag" value for temporal marking an node
		 *  - common practice for marking by algorithms.
		 */
		@Override
		public void setTag(double t) {   //setter
			t = this.tag;
		}

	}

}
