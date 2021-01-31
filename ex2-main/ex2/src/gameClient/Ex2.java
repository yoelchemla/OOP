package gameClient;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;
import java.util.List;

import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;



import Server.Game_Server_Ex2;
import api.directed_weighted_graph;
import api.dwGraphAlgorithms;
import api.dw_graph_algorithms;
import api.edge_data;
import api.game_service;
import api.node_data;

public class Ex2 implements Runnable{ 

	private static MyFrame window;
	private static Arena manageGame;
	private int id=0;
	private int level=0;

	public static void main(String[] args)
	{
		//java -jar Ex2.jar <parmter 1> <parmter 2>
		if(args.length==0) {
		LoginGui gui=new LoginGui();
		gui.start();
		}else {
			Ex2 ex=new Ex2(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
			Thread client = new Thread(ex);
			client.start();
		}
	}

	public Ex2(int _id,int _level) {
		this.id=_id;
		this.level=_level;
	}
	
	private void init(game_service game)
	{

		String viewPokemon = game.getPokemons();
		directed_weighted_graph graph = game.getJava_Graph_Not_to_be_used();
		//gg.init(g);

		manageGame = new Arena();
		manageGame.setGraph(graph);
		manageGame.setPokemons(Arena.json2Pokemons(viewPokemon));

		window = new MyFrame("Ex2");
		window.setSize(1000, 700);
		window.update(manageGame);
		window.show();
		String info = game.toString();
		JSONObject line;
		try 
		{
			line = new JSONObject(info);
			JSONObject ttt = line.getJSONObject("GameServer");
			int numOfAgents = ttt.getInt("agents");

			System.out.println(info);
			System.out.println(game.getPokemons());


			ArrayList<CL_Pokemon> cl_fs = Arena.json2Pokemons(game.getPokemons());

			for(int a = 0; a < cl_fs.size(); a++) 
			{
				Arena.updateEdge(cl_fs.get(a),graph);
			}



			for(int a = 0; a < numOfAgents; a++) 
			{
				int ind = a % cl_fs.size();
				CL_Pokemon c = cl_fs.get(ind);

				int nn = c.get_edge().getDest();

				if(c.getType() < 0 ) 
				{
					nn = c.get_edge().getSrc();
				}

				game.addAgent(nn);
			}
		}
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
	}


	 
	private static void moveAgants(game_service game, directed_weighted_graph graph_G) 
	{  
		String moveOfGraph = game.move();

		List<CL_Agent> log = Arena.getAgents(moveOfGraph, graph_G);
		manageGame.setAgents(log);

		String fs =  game.getPokemons(); 
		List<CL_Pokemon> ffs = Arena.json2Pokemons(fs);

		manageGame.setPokemons(ffs);

		for(int i =0; i <log.size(); i++) 
		{
			CL_Agent ag = log.get(i);


			int dest = ag.getNextNode();
			int src = ag.getSrcNode();


			if(dest == -1)
			{
				dw_graph_algorithms algo =new dwGraphAlgorithms();
				algo.init(graph_G);
				double shorts =-1;
				double min_dest=Double.MAX_VALUE;
				edge_data pok=null;
				for(CL_Pokemon P:ffs) {
					System.out.println(P.get_edge());
					shorts = algo.shortestPathDist(src, P.get_edge().getSrc()); 
					
					if(shorts< min_dest) {
						min_dest=shorts;
						pok=P.get_edge();
					}
					
				}
				
				
				List<node_data> list= algo.shortestPath(src, pok.getSrc()); 
				
				
				if(list==null) {
					dest = nextNode(graph_G, src);
					game.chooseNextEdge(ag.getID(), pok.getDest());
				}else {
					
					for(node_data n:list) {
						System.out.print(n.getKey());
						game.chooseNextEdge(ag.getID(), n.getKey());
						
					}
					game.chooseNextEdge(ag.getID(), pok.getDest());
				}
			}
		}
		
	}


	private static int nextNode(directed_weighted_graph g, int src)
	{
		int ans = -1;
		Collection<edge_data> ee = g.getE(src);
		Iterator<edge_data> itr = ee.iterator();

		int s = ee.size(); 
		int r = (int)(Math.random()*s);
		int i=0;
		while(i < r)
		{
			itr.next();i++;
		}
		ans = itr.next().getDest();

		return ans;
	}

	@Override
	public void run()
	{


		game_service game = Game_Server_Ex2.getServer(this.level); // you have [0,23] games

		game.login(this.id);
		directed_weighted_graph gg = game.getJava_Graph_Not_to_be_used();

		init(game);

		game.startGame();
		window.setTitle("Ex2 - OOP: (NONE trivial Solution) "+game.toString());
		int ind=0;
		long dt=100;

		while(game.isRunning()) 
		{
			long seconds = TimeUnit.MILLISECONDS.toSeconds(game.timeToEnd());
			window.setTime(seconds);
			moveAgants(game, gg);
			try
			{
				if(ind %1 == 0) 
				{
					window.repaint();
				}
				Thread.sleep(dt);
				ind++;
			}
			catch(Exception e) 
			{
				e.printStackTrace();
			}
		}
		String res = game.toString();

		System.out.println(res);
		System.exit(0);
	}
}


