package tests;


import src.WGraph_DS;
import src.weighted_graph;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class WGraph_DSTest {

    public WGraph_DS genNode(int nodesize ){
        WGraph_DS graph = new WGraph_DS();
        for ( int i = 0 ; i < nodesize ; i++) {
            graph.addNode(i);
        }
        return graph;
    }public WGraph_DS genNodebyrange(int start , int end ){
        if ( start > end ){
            int t = start;
            start = end;
            end = t;
        }
        WGraph_DS graph = new WGraph_DS();
        for (  ; start < end ; start++) {
            graph.addNode(start);
        }
        return graph;
    }
    public HashMap genEdgeMap(weighted_graph graph, int edgesize){
        HashMap<Integer,HashMap<Integer,Double>> temp = new HashMap<>();
        int count = graph.edgeSize();
        while (count != edgesize){
            count = graph.edgeSize();
            int random_Number1 = (int) Math.floor((Math.random() *graph.nodeSize()));
            int random_Number2 = (int) Math.floor((Math.random() *graph.nodeSize()));
            double weight = Math.floor(Math.random() * 10);
            if( graph.getNode(random_Number1) != null && graph.getNode(random_Number2) != null ) {
                if (random_Number1 != random_Number2) {
                    if (temp.get(random_Number1) == null) {
                        temp.put(random_Number1, new HashMap<>());
                        temp.get(random_Number1).put(random_Number2, weight);
                    } else {
                        temp.get(random_Number1).put(random_Number2, weight);
                    }
                    if (temp.get(random_Number2) == null) {
                        temp.put(random_Number2, new HashMap<>());
                        temp.get(random_Number2).put(random_Number1, weight);
                    } else {
                        temp.get(random_Number2).put(random_Number1, weight);
                    }
                    graph.connect(random_Number1, random_Number2, weight);
                }
            }
        }
        return temp;
    }
    public void genEdge(weighted_graph graph, int edgesize){
        int count = graph.edgeSize();
        while (count != edgesize) {
            count = graph.edgeSize();
            Random randy = new Random();
            int random_N1 = randy.nextInt(graph.nodeSize());
            int random_N2 = randy.nextInt(graph.nodeSize());
            double weight = Math.floor(Math.random() * 10);
            if (graph.getNode(random_N1) != null && graph.getNode(random_N2) != null) {
                if (random_N1 != random_N2){
                    graph.connect(random_N1,random_N2,weight);
                }
            }
        }
    }

    @Test
    void getNode() {
        WGraph_DS graph = genNode(32);
        for( int i =1 ; i < graph.nodeSize(); i++){
            assertEquals(i,graph.getNode(i).getKey());
        }
        assertNull(graph.getNode(33));
    }

    @Test
    void hasEdge() {
        int edgesize =30;int nodesize = 99;
        WGraph_DS graph = genNode(nodesize);
        HashMap<Integer,HashMap<Integer,Double>> temp = genEdgeMap(graph, edgesize );
        for( Integer e : temp.keySet()){
            for (Integer o : temp.get(e).keySet()){
                boolean flag = graph.hasEdge(e,o);
                assertTrue(flag);
            }
        }
    }

    @Test
    void getEdge() {
        int edgesize =60;int nodesize = 40;
        WGraph_DS graph = genNode(nodesize);
        HashMap<Integer,HashMap<Integer,Double>> temp = genEdgeMap(graph, edgesize );
        for( Integer e : temp.keySet()){
            for (Integer m : temp.get(e).keySet()){
                double test = graph.getEdge(e,m);
                assertEquals(temp.get(e).get(m),test);
            }
        }
    }

    @Test
    void getV() {
        int start =503; int end = 472;int sum = start-end;
        WGraph_DS graph = genNodebyrange(end,start);
        assertEquals(sum, graph.getV().size());
        graph.removeNode(502);
        graph.removeNode(472);
        assertEquals(sum-2,graph.getV().size());
        graph.addNode(503);
        assertEquals(sum-1, graph.getV().size());
    }
    @Test
    void theTest(){

        WGraph_DS graph = new WGraph_DS();
        graph = genNode(15);
        graph.connect(0,1,4);
        graph.connect(0,2,3);
        graph.connect(0,3,8);
        graph.connect(3,8,10);
        graph.connect(2,7,2);
        graph.connect(1,6,1);
        graph.connect(4,5,2);
        graph.connect(4,6,3);
        graph.connect(5,6,9);
        graph.connect(7,14,15);
        graph.connect(8,13,4);
        graph.connect(10,9,2);
        graph.connect(9,11,4);
        graph.connect(9,12,3);
        graph.connect(11,13,6);
        graph.connect(0,1,4);
        assertEquals(15,graph.nodeSize());
        assertTrue(graph.hasEdge(0,3));
        assertFalse(graph.hasEdge(0,10));
        assertEquals(15,graph.edgeSize());
        graph.removeNode(0);
        assertEquals(12,graph.edgeSize());
        graph.connect(7,14,15);
        assertEquals(12,graph.edgeSize());
        graph.connect(7,14,3);
        assertEquals(3,graph.getEdge(7,14));
        graph.addNode(0);
        assertEquals(12,graph.edgeSize());
        graph.connect(4,4,8);
        assertFalse(graph.hasEdge(4,4));

    }

}

