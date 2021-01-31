package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms{

			//created a new graph (a)
	graph a = new Graph_DS ();


	@Override
	public void init(graph g) {
		this.a = g ;
	}

	@Override
	/**
	 * by the Collection(all the neighbors) run on the graph, 
	 * deep copy.
	 */
	public graph copy() {
		Graph_DS graphCopy = new Graph_DS();   // new graph
		Collection <node_data> copy = a.getV();
		for (node_data Node : copy) {			// for each
			Node t = new Node(Node);			//new node
			graphCopy.addNode(t);				//added
		}
		Collection <node_data> copy2 = a.getV();
		for (node_data Node : copy2) {
			for(node_data Ni : Node.getNi()){
				graphCopy.getNode(Node.getKey()).addNi(graphCopy.getNode(Ni.getKey()));
			}
		}
		graphCopy.setcounterEdge(this.a.edgeSize());
		graphCopy.setcounterMC(this.a.getMC());
		return graphCopy;
	}


	@Override
	/**
	 * mikre katze: graph is not null and he have max 1 node(or less) -> return true, because he's connect to himself .
	 * run on the graph a on all the nodes and check if i didn't visited-> i started him tag -1,
	 * if i visit, the level tag will be +1
	 * the counter ++ ,
	 * number of the nodes = counter( we pass on all the nodes) and the graph a is connected.
	 */
	public boolean isConnected() {
		int g = a.nodeSize();
		boolean condition = true;

		if (a==null ||a.nodeSize() <= 1) // the graph is empty or nom of nodes<=1
			return condition;
		Collection<node_data> reset = a.getV(); 
		for (node_data n : reset) {
			n.setTag(-1); 	//start node's tag
		}
		Queue<Integer> queue = new LinkedList<>();  	 // new q
		int counter = 0;
		int key = a.getV().iterator().next().getKey();	//point to the next node
		queue.add(key);   							   //enqueue

		while (!queue.isEmpty()) {					  //q isn't empty
			key= queue.poll(); 	   					 //dequeue
			Collection<node_data> visited = a.getV(key); 

			if (visited != null) {					     //have a more nodes
				for (node_data n : visited) {   	   	// for each
					if (n.getTag() == -1) {			   // not yet visited
					     queue.add(n.getKey());		  // add
						n.setTag(0);				 //level+
						counter ++ ;			    // action

					}
				}
			}
		}
		if (g != counter) { // check if i visited on all the nodes
			condition = false;
		}else {
			condition = true; 
		}
		return condition;
	}

	@Override
	/**
	 * if destinition = source is say that we have 1 node and the path is 0
	 * else return the shortet path
	 */
	public int shortestPathDist(int src, int dest) {
		if (dest == src){
			return 0;
		}
		
		List <node_data> distance = shortestPath(src,dest);
		if (distance == null){
			return -1;
		}
		else{
			return (distance.size() - 1);
		}
	}

	@Override
	/**
	 * i created a list for to save the path.
	 *  returns the the shortest path between src to dest - as an ordered List of nodes
	 *  mikre  katze:  null
	 *  by bfs, go until arrived to the dst, then i comeback backwards and save all the node
	 *  after i return by bfs the shorter path and the nodes
	 */
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> path = new ArrayList<>();   			   // new list
		Queue <Integer> queue = new LinkedList<>();			   	  //new q
		HashMap <Integer, Boolean> prev= new HashMap <>();		 // the parent of the node
		Collection <node_data> reset = a.getV();
		
		for (node_data node : reset){						    //for each
			prev.put(node.getKey(), false);
		}
		
		int Destintion = dest;
		int tmp = src;                       			      //save the src 
		queue.add(tmp);									    //add to the q

	
		while (!queue.isEmpty() && !prev.get(dest)) {	 // the q isn't empty *and* didn't arrived to the dest
			tmp = queue.poll();							// dequeue
			
			Collection <node_data> nodes = a.getV(tmp);
			for (node_data node : nodes) {								//for each
				
				if (!prev.get(node.getKey())) {
					node.setTag(tmp);								   //reset
					prev.replace(node.getKey(), true);                //visit
					queue.add(node.getKey());                        //added
				}
			}
		}
		if (!prev.get(dest))
			return null;
		while (Destintion != src) {									//didn't arrived to the source backwards
			path.add(0,a.getNode(Destintion));
			Destintion = a.getNode(Destintion).getTag();
		}
		path.add(0,a.getNode(src));
		return path;											  // return all the path and the nodes
	}
}




