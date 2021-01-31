package api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import java.util.*;

class MyTest {

	private static int limit = 1000;

    @Test
    void shortestPathDist() {

        node_data n0 = new NodeData(0);
        node_data n1 = new NodeData(1);
        node_data n2 = new NodeData(2);
        node_data n3 = new NodeData(3);

        directed_weighted_graph g = new directedWeightedGraph();
        dw_graph_algorithms ga = new dwGraphAlgorithms();

        g.addNode(n0);g.addNode(n1);g.addNode(n2);g.addNode(n3);

        g.connect(0,1,3);
        g.connect(0,2,1);
        g.connect(1,3,4);
  
        ga.init(g);
        System.out.println(ga.shortestPathDist(0, 3));
        assertEquals(ga.shortestPathDist(0,3), 7.0);
        List<node_data > path = ga.shortestPath(0, 3);
        for(node_data i:path) {
        	System.out.print(i.getKey());
        }
    }

//    @Test
//    void shortestPath() {
//
//        // create new graph
//        directed_weighted_graph g0 = new directedWeightedGraph();
//
//        // adding limit nodes
//        int i = 0;
//        while(i < limit) {
//            g0.addNode(new NodeData(i));
//            i++;
//        }
//
//      //   create graph algorithms for g0 graph
//       dw_graph_algorithms ga = new dwGraphAlgorithms();
//
//       //  connection every node in the graph to his neighbor
//       //  [n0 -> n1], [n1 -> n2], [n2 -> n3],... [n(n-2) -> n(n-1)] // let n be limit
//        i = 0;
//        int j = 1;
//        while(i < limit) {
//            g0.connect(i,j,10);
//            i++;
//            j++;
//        }
//
//        ArrayList<Integer> path = new ArrayList<>();
//        i = 0;
//        while(i < limit) {
//            path.add(i);
//            i++;
//        }
//        ga.init(g0);
//        List<node_data> shortestPath = ga.shortestPath(0,limit-1);
//
//        // checking if shortest path is equals to path array list
//        i = 0;
//        while(i < limit) {
//            assertEquals(path.get(i), shortestPath.get(i).getKey());
//            i++;
//        }
//
//    }

}
