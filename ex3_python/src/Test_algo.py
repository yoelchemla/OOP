from DiGraph import DiGraph
from GraphAlgo import GraphAlgo
import unittest
import math
class mytest(unittest.TestCase):

    def setUp(self) -> None:
        self.graph = DiGraph()



    def test_algo(self):
        for x in range(10):
            self.assertTrue(self.graph.add_node(x, (2, 3, 4)))
        for x in range(0,10,2):
            self.assertTrue(self.graph.add_edge(x, x+1,50))
        algo=GraphAlgo(self.graph)


        self.assertTrue(algo.save_to_json("../data/Test.json"))


        self.assertTrue(algo.load_from_json("../data/A5"))

        print(algo.get_graph())
        algo.plot_graph()



    def test_shortestpath(self):
        for x in range(3):
            self.assertTrue(self.graph.add_node(x, (2, 3, 4)))
        self.assertTrue(self.graph.add_edge(0,1,1))
        self.assertTrue(self.graph.add_edge(0, 2,3))
        self.assertTrue(self.graph.add_edge(1, 2, 1.5))
        algo=GraphAlgo(self.graph)
        path = algo.shortest_path(0,2)
        self.assertEqual(path[0],2.5)
        self.assertEqual(path[1], [0,1,2])



    def test_shortestpath1(self):
        for x in range(4):
            self.assertTrue(self.graph.add_node(x, (2, 3, 4)))
        self.assertTrue(self.graph.add_edge(0,1,1))
        self.assertTrue(self.graph.add_edge(0, 2,3))
        self.assertTrue(self.graph.add_edge(1, 2, 1.5))
        self.assertTrue(self.graph.add_edge(2, 3, 0.5))
        algo=GraphAlgo(self.graph)
        path = algo.shortest_path(0,3)

        self.assertEqual(path[0],3)
        self.assertEqual(path[1], [0,1,2,3])

        path = algo.shortest_path(0, 500)
        self.assertEqual(path[0], math.inf)
        self.assertEqual(path[1], [])

        path = algo.shortest_path(0, 0)
        self.assertEqual(path[0], 0)
        self.assertEqual(path[1], [0])



    def test_component(self):
        for x in range(4):
            self.assertTrue(self.graph.add_node(x, (2, 3, 4)))
        self.assertTrue(self.graph.add_edge(0,1,1))
        self.assertTrue(self.graph.add_edge(1, 2, 1.5))
        self.assertTrue(self.graph.add_edge(2, 0,3))
        self.assertTrue(self.graph.add_edge(2, 3, 0.5))

        algo = GraphAlgo(self.graph)
        print(algo.connected_component(3))
        print(algo.connected_components())



    def test_plot_graph(self):
        algo_g = GraphAlgo()
        algo_g.load_from_json("../data/G_10_80_0.json")
        algo_g.plot_graph()



    def test_save(self):
        for t in range(20):
            self.assertTrue(self.graph.add_node(t, (2, 3, 5)))
        for t in range(0, 20, 4):
            self.assertTrue(self.graph.add_edge(t, t + 1, 50))
        algo = GraphAlgo(self.graph)
        self.assertTrue(algo.save_to_json("../data/test_save.json"))
        for z in range(50):
            self.graph.add_node(z, (2, 3, 5))
        for z in range(0, 50, 5):
            self.graph.add_edge(z, z + 1, 34)
        algo2 = GraphAlgo(self.graph)
        self.assertTrue(algo2.save_to_json("../data/test_save_50_.json"))



    def test_load(self):
        algo = GraphAlgo(self.graph)
        self.assertTrue(algo.load_from_json("../data/A0"))
        algo.plot_graph()
        self.assertTrue(algo.load_from_json("../data/A1"))
        self.assertTrue(algo.load_from_json("../data/A2"))
        self.assertTrue(algo.load_from_json("../data/A3"))
        self.assertTrue(algo.load_from_json("../data/A4"))
        self.assertTrue(algo.load_from_json("../data/A5"))
        self.assertTrue(algo.load_from_json("../data/A5_edited"))








