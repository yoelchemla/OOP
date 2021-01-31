//package ex0;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//
//
//public class Graph_DS implements graph{
//	private HashMap<Integer, node_data> nodes = new HashMap<Integer, node_data>();
//	private  int edge_size=0;
//	private int mc=0;
//
//	@Override
//	public node_data getNode(int key) {
//		if (nodes.get(key)== null) return null;
//		return	 nodes.get(key);	
//
//	}
//	@Override
//	public boolean hasEdge(int node1, int node2) {   
//		if (nodes.get(node1).hasNi(node2)) {
//			return true;
//		}
//		else
//			return false;
//	}
//
//	@Override
//	public void addNode(node_data n) {
//		if (nodes.containsKey(n.getKey()))return;
//		else 
//			nodes.put(n.getKey(), n);
//		mc++;
//	}
//
//	@Override
//	public void connect(int node1, int node2) {
//		nodes.get(node1).addNi(nodes.get(node2));
//		nodes.get(node2).addNi(nodes.get(node1));
//
//		edge_size++;
//		mc++;
//	}
//
//	@Override
//	public Collection<node_data> getV() {
//		return nodes.values();
//	}
//
//	@Override
//	public Collection<node_data> getV(int node_id) {
//		return nodes.get(node_id).getNi();
//	}
//
//	@Override
//	public node_data removeNode(int key) {     
//		if(nodes.get(key).hasNi(key)) {
//
//			Iterator<node_data> it = getNode(key).getNi().iterator();
//			while (it.hasNext()) {
//				it.next().removeNode(getNode(key));
//
//				edge_size --;
//				mc++;
//			}
//
//			node_data a=getNode(key);
//			nodes.remove(key);
//			return a;}
//		return null; }
//
//	@Override
//	public void removeEdge(int node1, int node2) {
//		if(nodes.get(node1).hasNi(node2)) {
//
//			nodes.get(node1).removeNode(nodes.get(node2));
//			nodes.get(node2).removeNode(nodes.get(node1));
//
//			mc++;
//			edge_size--;
//		}
//	}
//	@Override
//	public int nodeSize() {
//		return nodes.size();
//	}
//
//	@Override
//	public int edgeSize() {
//		return edge_size;
//	}
//
//	@Override
//	public int getMC() {
//		return 	mc;
//	}
//
//}

package ex0;

import java.util.Collection;
import java.util.HashMap;
import java.util.*;




public class Graph_DS implements graph {
						//*************** fields***************
	private HashMap<Integer,node_data> hash1 =  new HashMap<Integer,node_data> ();
	private static int countEdge = 0 ;
	private static int countMC= 0;


	
								//contructor
	public Graph_DS(){               
		hash1 = new HashMap<>();
		countEdge = 0;
		countMC = 0;
	}

	@Override
	/**
	 * mikre katze, and return the nodes
	 */
	public node_data getNode(int key) {
		if (hash1.get(key)== null) 
			return null;

		return hash1.get(key);

	}

	@Override
	/** 
	 * return true if there is an edge between the nodes
	 */
	public boolean hasEdge(int node1, int node2) {
		if (hash1.get(node1).hasNi(node2))
			return true;
		else 
			return false;
	}

	@Override
	/** 
	 * add a new node to the graph with the given node_data by the method "put" in hashmap.
	 * mc++
	 */
	public void addNode(node_data n) {
		if (hash1.containsKey(n.getKey()))
			return;
		else 
			hash1.put(n.getKey(), n);
		countMC ++ ;
	}

	@Override
	/** 
	 * Connect an edge between node1 and node2. 
	 * if the edge node1-node2 already exists - the method does nothing.   
	 *edge && mc++
	 */
	public void connect(int node1, int node2) {
		if ((!hash1.containsKey(node1)) || (!hash1.containsKey(node2)) || hasEdge(node1, node2))
			return;
		if((node1 == node2)&&(!hasEdge(node1,node2)))
			return;
		if((hash1.get(node1) ==null) || (hash1.get(node2) ==null) )
			return;

		if ((!hash1.containsKey(node1)) || (!hash1.containsKey(node2)) || hasEdge(node1, node2)) {
			return;

		}
		else {
			hash1.get(node1).addNi(hash1.get(node2));
			hash1.get(node2).addNi(hash1.get(node1));
			countEdge++;
			countMC ++;		    }
	}

	@Override
	/**
	   return a collection values method;
	 */
	public Collection<node_data> getV() {
		return hash1.values();
	}

	/**
	 * This method returns a collection containing all the nodes connected to keys
	 */
	@Override
	public Collection<node_data> getV(int node_id) {

		return hash1.get(node_id).getNi();
	}
/**
 * mikre katze, create a new object, for each, remove, counter+, remove edge, 
 */
	@Override
	public node_data removeNode(int key) {
		if (!hash1.containsKey(key)) {
			return null;
		}
		else {
			node_data tmp= hash1.get(key);
			for (node_data node : getV(key)) {
				node.removeNode(tmp);
				countEdge--;
				countMC++;
			}
			hash1.remove(key);
			countMC++;
			countEdge--;
			return tmp;
		}
	}

	@Override
	/**
	 * check if the nodes is neigbros and general if there exists ,
	 * remove
	 * mc++
	 */
	public void removeEdge(int node1, int node2) {
		if (hash1.containsKey(node2) && hash1.containsKey(node1) && hash1.get(node2).hasNi(node1) ) {
			hash1.get(node2).removeNode(hash1.get(node1));
			
			countMC++;

		}
	}


	@Override
	/**
	 *  size of hashmap
	 */
	public int nodeSize() {

		return hash1.size();
	}

	@Override
	/**
	 * counter of edge
	 */
	public int edgeSize() {

		return countEdge;
	}

	@Override
	/**
	 * counter of actions
	 */
	public int getMC() {

		return countMC;
	}


	public void setcounterEdge(int countEdge) {// setter to the edge
		this.countEdge = countEdge;
	}

	public void setcounterMC(int countMC) { //setter to the count
		countMC = countMC;
	}

}
