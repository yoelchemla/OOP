package api;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonWriter;
import com.sun.jdi.Type;
import java.util.*;

public class dwGraphAlgorithms implements dw_graph_algorithms 
{

	private directed_weighted_graph directedGraph = new directedWeightedGraph();


	@Override
	public void init(directed_weighted_graph g)
	{
		this.directedGraph = g;
	}

	@Override
	public directed_weighted_graph getGraph() 
	{
		return this.directedGraph;
	}

	@Override
	public directed_weighted_graph copy() 
	{
		directed_weighted_graph DirectedGraphCopy = new directedWeightedGraph();
		if (this.directedGraph == null) 
		{
			return null;
		}
		for (node_data node : this.directedGraph.getV())
		{
			DirectedGraphCopy.addNode(node);
		}
		for (node_data node : this.directedGraph.getV()) 
		{
			for (edge_data Kishke : this.directedGraph.getE(node.getKey())) 
			{
				DirectedGraphCopy.connect(node.getKey() ,Kishke.getDest(),Kishke.getWeight());
			}
		}
		((directedWeightedGraph) DirectedGraphCopy).setCount(this.directedGraph.getMC());
		return DirectedGraphCopy;
	}


	@Override
	public boolean isConnected() 
	{
		boolean ans = true;
		int nodeSize = directedGraph.nodeSize();
		if (this.directedGraph.nodeSize() <= 1 || this.directedGraph == null) 
		{
			return ans;
		}
		for (node_data n : directedGraph.getV()) 
		{
			n.setTag(0);
		}
		Queue<Integer> queue = new LinkedList<>();
		int key = this.directedGraph.getV().iterator().next().getKey();
		int counter = 0;
		queue.add(key);

		while (!queue.isEmpty()) 
		{
			key = queue.poll();
			Collection<edge_data> get = directedGraph.getE(key);
			if (get != null) {
				for (edge_data edge : get) 
				{
					if (counter == 0) {
						counter++;
						queue.add(edge.getDest());
						directedGraph.getNode(edge.getSrc()).setTag(1);
						directedGraph.getNode(edge.getDest()).setTag(1);
					}
					else 
					{
						if (directedGraph.getNode(edge.getDest()).getTag() == 0 && directedGraph.getNode(edge.getSrc()).getTag() == 1) {
							counter++;
							queue.add(edge.getDest());
							directedGraph.getNode(edge.getSrc()).setTag(1);
							directedGraph.getNode(edge.getDest()).setTag(1);
						} 
						else 
						{
							if (directedGraph.getNode(edge.getSrc()).getTag() == 1 && counter == nodeSize - 1 && directedGraph.getNode(edge.getDest()).getTag() == 1)
							{
								counter++;
								queue.add(edge.getDest());
								directedGraph.getNode(edge.getSrc()).setTag(1);
								directedGraph.getNode(edge.getDest()).setTag(1);
							}
						}

					}
				}
			}
		}
		if (nodeSize == counter) 
		{
			ans = true;
		} 
		else 
		{
			ans = false;
		}
		return ans;
	}

	@Override
	public double shortestPathDist(int src, int dest) {

			if(directedGraph.getNode(src)==null || directedGraph.getNode(dest)==null)
			{
				return -1;
			}
			
				for (node_data nd : directedGraph.getV())
				{
					nd.setWeight(Double.MAX_VALUE);
					nd.setInfo("");
					nd.setTag(0);
				}
				
				this.directedGraph.getNode(src).setWeight(0);
			
			
				PriorityQueue<node_data> pQueue = new PriorityQueue<node_data>(new Comparator<node_data>() {
					@Override
					public int compare(node_data o1, node_data o2) {
						return -Double.compare(o2.getWeight(),o1.getWeight());
					}
				});
		
				pQueue.add(this.directedGraph.getNode(src));

		
				while(pQueue.size()!=0)
				{
					

					node_data first=pQueue.poll();//0
					if(this.directedGraph.getE(first.getKey())!=null) {
					for (edge_data ed : this.directedGraph.getE(first.getKey()))
					{
						double sum = ( first.getWeight() + ed.getWeight() );
						node_data second = this.directedGraph.getNode(ed.getDest());

						if( sum <= second.getWeight())
						{
							second.setInfo(first.getKey()+"");

							pQueue.remove(second);
							pQueue.add(second);
							second.setWeight(sum);
						}

					}
					}
				}
				if(this.directedGraph.getNode(dest).getWeight()!=Double.MIN_VALUE) {
				return this.directedGraph.getNode(dest).getWeight();
			}
			else
			{
				return -1;
			}
		}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
	    List<node_data> theWayYouGo = new ArrayList<node_data>();
       
	    double probe = shortestPathDist(src, dest);
       
	    if(probe == Double.MAX_VALUE) {
            return null;
        }
        

        theWayYouGo.add(directedGraph.getNode(dest));
		String str = directedGraph.getNode(dest).getInfo();
		while(!str.equals("")) {		
			theWayYouGo.add(directedGraph.getNode(Integer.parseInt(str)));
			str = directedGraph.getNode(Integer.parseInt(str)).getInfo();
		}
		Collections.reverse(theWayYouGo);
       
            return theWayYouGo;
	}

	@Override
	public boolean save(String file) {
		        try
		        {
		            JsonWriter r = new CreateJsonGraph().graphToJson(this.directedGraph ,file);
		        }
		        catch (IOException e)
		        {
		            e.printStackTrace();
		            return false;
		        }

		        return true;
		    }


	@Override
	public boolean load(String file) {
	     try {
	            GsonBuilder builder= new GsonBuilder();
	            builder.registerTypeAdapter(directedWeightedGraph.class,new CreateGraph());
	            Gson gson= builder.create();

	            FileReader reader= new FileReader(file);
	            directed_weighted_graph graph= gson.fromJson(reader, directedWeightedGraph.class);
	            System.out.println(graph);
	            init(graph);

	        }
	        catch (FileNotFoundException e) {
	            e.printStackTrace();
	            return false;
	        }
	        return true; 
 		}

    private static class CreateJsonGraph {
        //Serialize
        public JsonWriter graphToJson(directed_weighted_graph g,String file) throws IOException {
            FileWriter fileWriter= new FileWriter(file);
            JsonWriter writer = new JsonWriter(fileWriter);
            writer.beginObject();
            writer.name("Edges");
            writer.beginArray();
            for (node_data node : g.getV()) {
                for (edge_data edge: g.getE(node.getKey())) {
                    writer.beginObject();
                    writer.name("src").value(edge.getSrc());
                    writer.name("w").value(edge.getWeight());
                    writer.name("dest").value(edge.getDest());
                    writer.endObject();
                }
            }
            writer.endArray();
            writer.name("Nodes");
            writer.beginArray();
            for (node_data node : g.getV()) {
                writer.beginObject();
                writer.name("pos").value(node.getLocation().toString());
                writer.name("id").value(node.getKey());
                writer.endObject();
            }
            writer.endArray();
            writer.endObject();
            writer.close();
            return writer;
        }
    }

    private  class CreateGraph implements JsonDeserializer<directed_weighted_graph> {
        public directed_weighted_graph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            directed_weighted_graph g=new directedWeightedGraph();

            JsonObject jasonO= jsonElement.getAsJsonObject();
            JsonArray nodes= jasonO.get("Nodes").getAsJsonArray();
            JsonArray edges= jasonO.get("Edges").getAsJsonArray();
            for (JsonElement node:nodes) {
                int key=((JsonObject)node).get("id").getAsInt();
                String pos=((JsonObject)node).get("pos").getAsString();
                g.addNode(new NodeData(key));
                g.getNode(key).setLocation(new geoLocation(pos));
            }
            for (JsonElement edge:edges) {
                int key=((JsonObject)edge).get("src").getAsInt();
                int dest=((JsonObject)edge).get("dest").getAsInt();
                double w=((JsonObject)edge).get("w").getAsDouble();
                g.connect(key,dest,w);
            }
            return g;
        }

		@Override								//??????????????????????
		public directed_weighted_graph deserialize(JsonElement arg0, java.lang.reflect.Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			// TODO Auto-generated method stub
			return null;
		}
    }



	
	public String toString() 
	{
		return " " + this.directedGraph;
	}
}