package api;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

//import com.sun.tools.javac.util.List;

public class directedWeightedGraph implements directed_weighted_graph {

	private HashMap <Integer, node_data> hashNodes;
	private HashMap <String, edge_data> hashEdge;
	private static int countMC;

	public directedWeightedGraph() {
		hashNodes = new HashMap<>();
		hashEdge = new HashMap<>();
		countMC = 0;
	}
	public void setCount(int countMC) {
		this.countMC = countMC;
	}

	@Override
	public node_data getNode(int key) {
		return hashNodes.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		return hashEdge.get(getEdgeKey(src,dest));
	}

	@Override
	public void addNode(node_data n) {
		if(hashNodes.containsKey(n.getKey())) return;

		NodeData newNode = new NodeData();
		hashNodes.put(newNode.getKey(), newNode);
		countMC++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		if(hashEdge.get(getEdgeKey(src, dest)) == null) {
			edgeData newEdge = new edgeData(getEdgeKey(src, dest),w ,hashNodes.get(src),hashNodes.get(dest));
			hashEdge.put(getEdgeKey(src, dest), newEdge);
		}
	}

	@Override
	public Collection<node_data> getV() {
		return hashNodes.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
	        LinkedList<edge_data> list = new LinkedList<>();
	        for(node_data vertex: hashNodes.values())
	        {
	            if(vertex.getKey() != node_id) {
	                if (hashEdge.get(getEdgeKey(node_id,vertex.getKey()))!=null)
	                {
	                    list.add(hashEdge.get(getEdgeKey(node_id,vertex.getKey())));
	                }
	            }
	        }
	            return list;
	   
	}

	@Override
	public node_data removeNode(int key) { /// pass it
		if (!hashNodes.containsKey(key)) 
		{
			return null;
		}
		for (Integer neighbor : hashNodes.keySet()){
			hashNodes.remove(key);
		}
		hashNodes.remove(key);
		countMC++;
		node_data RemovedNode = hashNodes.remove(key);
		return RemovedNode;

	}

	@Override
	public edge_data removeEdge(int src, int dest) {

		if (!hasEdge(src,dest)) {
			return null;
		}
		hashEdge.remove(getEdgeKey(src, dest));
		countMC++;
		return hashEdge.get(getEdgeKey(src, dest));}

	@Override
	public int nodeSize() {
		return hashNodes.size();
	}

	@Override
	public int edgeSize() {
		return hashEdge.size();
	}

	@Override
	public int getMC() {
		return countMC;
	}
	private String getEdgeKey(int src, int dest){
		return String.valueOf(src) + "-" + String.valueOf(dest);
	}
	public boolean hasEdge(int node1, int node2) {
		if (!this.hashNodes.containsKey(node1) || !this.hashNodes.containsKey(node2)) return false;
		if (hashEdge.containsKey(getEdgeKey(node1, node2)) || hashEdge.containsKey(getEdgeKey(node2, node1))){
			return true;
		}
		return false;
	}
}
