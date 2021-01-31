package ex0;

import java.util.*;
import java.util.HashMap;


public class Node implements node_data {
	/**
	  hashmap for the node: O(1)  
	 */
	private HashMap<Integer,node_data> hash1 =  new HashMap<Integer,node_data> ();
	private static int count = 0 ;   
	private	int key; 
	private int tag;
	private String info;


	//consructor
	public Node () {
		this.key = count;  
		count ++ ; 
		info = " ";
		tag = 0;
		hash1 =  new HashMap<Integer,node_data> (); 

	}
	/** 
	 * copy constructor
	 */
	public Node (node_data p){
		hash1 = new HashMap<>();
		this.key = p.getKey();
		this.tag = p.getTag();
		this.info = p.getInfo();


	}

	public  Node (int key ) {
		this.key = key ;
	}
	@Override
	public int getKey() {

		return this.key;
	}

	/**
	 * This method returns a collection with all the Neighbor nodes 
	 */
	@Override
	public Collection<node_data> getNi() {

		return hash1.values();
	}
	/**
	 * return true if <==> key are adjacent, as an edge between them.
	 */
	@Override
	public boolean hasNi(int key) {
		if (hash1.containsKey(key)) return true;
		return false;
	}
	/**
	 * This method adds the node_data (t) to this node_data
	 */
	@Override
	public void addNi(node_data t) {
		if (t == null || t.getKey() == this.getKey()) return; 
		else {
			hash1.put(t.getKey(),t) ;
		}
	}
	/**
	 * Removes the edge this-node
	 */
	@Override
	public void removeNode(node_data node) {
		if(node != null) {
			hash1.remove(node.getKey());
		}
	}

	@Override
	public String getInfo() {  //getter

		return this.info;
	}

	@Override
	public void setInfo(String s) {   //setter
		this.info = s;
	}

	@Override
	public int getTag() {     	//getter

		return this.tag;
	}

	@Override
	public void setTag(int t) {   	//setter
		this.tag =t;
	}

}
