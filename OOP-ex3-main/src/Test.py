from DiGraph import DiGraph
import unittest

class mytest(unittest.TestCase):

    def setUp(self) -> None:
        #before each
        self.graph = DiGraph()


    def test_digraph(self):
        self.assertTrue(self.graph.add_node(0,(0,7,2)))
        self.assertFalse(self.graph.add_node(0, (0, 7, 2)))
        self.assertEqual(self.graph.v_size(),1)
        self.assertTrue(self.graph.add_node(1, (0, 7, 2)))
        self.assertTrue(self.graph.add_edge(0,1,500))
        self.assertFalse(self.graph.add_edge(0, 1, 500))
        self.assertTrue(self.graph.add_edge(1, 0, 500))
        self.assertFalse(self.graph.add_edge(1, 0, 500))
        self.assertFalse(self.graph.add_edge(1, 500, 500))
        self.assertFalse(self.graph.add_edge(1, 1, 500))
        self.assertFalse(self.graph.add_edge(1, 15, 500))
        self.assertFalse(self.graph.add_edge(115, 0, 500))
        self.assertEqual(self.graph.e_size(),2)
        self.assertTrue(self.graph.remove_edge(1,0))
        self.assertFalse(self.graph.remove_edge(1, 0))
        self.assertEqual(self.graph.e_size(), 1)
        self.assertEqual(self.graph.get_mc(),5)
        print(self.graph.get_all_v())
        print(self.graph.all_out_edges_of_node(0))
        print(self.graph.all_in_edges_of_node(1))



    def test_removenode(self):
        for x in range(5):
            self.assertTrue(self.graph.add_node(x,(2,3,4)))
        self.assertEqual(self.graph.v_size(),5)
        self.assertTrue(self.graph.add_edge(0,3,45))
        self.assertTrue(self.graph.add_edge(0, 4, 45))
        self.assertTrue(self.graph.add_edge(1, 0, 45))
        self.assertTrue(self.graph.add_edge(2, 0, 45))
        self.assertEqual(self.graph.e_size(),4)
        self.assertEqual(self.graph.v_size(),5)
        print(self.graph.get_all_v())
        print(self.graph.all_out_edges_of_node(0))
        print(self.graph.all_out_edges_of_node(1))
        print(self.graph.all_out_edges_of_node(2))
        print(self.graph.all_out_edges_of_node(3))
        print(self.graph.all_out_edges_of_node(4))
        print(self.graph.all_in_edges_of_node(0))
        print(self.graph.all_in_edges_of_node(1))
        print(self.graph.all_in_edges_of_node(2))
        print(self.graph.all_in_edges_of_node(3))
        print(self.graph.all_in_edges_of_node(4))
        self.assertTrue(self.graph.remove_node(0))
        print(self.graph.get_all_v())
        print(self.graph.all_out_edges_of_node(0))
        print(self.graph.all_out_edges_of_node(1))
        print(self.graph.all_out_edges_of_node(2))
        print(self.graph.all_out_edges_of_node(3))
        print(self.graph.all_out_edges_of_node(4))
        print(self.graph.all_in_edges_of_node(0))
        print(self.graph.all_in_edges_of_node(1))
        print(self.graph.all_in_edges_of_node(2))
        print(self.graph.all_in_edges_of_node(3))
        print(self.graph.all_in_edges_of_node(4))
        self.assertEqual(self.graph.e_size(), 0)
        self.assertEqual(self.graph.v_size(), 4)



    def test_add_node(self):
        for x in range(5):
            self.assertTrue(self.graph.add_node(x, (2, 3, 5)))
        self.assertEqual(self.graph.v_size(), 5)
        print(self.graph.v_size())



    def test_mc(self):
        for x in range(10):
            self.assertTrue(self.graph.add_node(x, (4, 5, 6)))
        self.assertEqual(self.graph.v_size(), 10)
        self.assertTrue(self.graph.add_edge(5, 3, 45))
        self.assertTrue(self.graph.add_edge(3, 4, 78))
        self.assertTrue(self.graph.add_edge(6, 1, 45))
        self.assertTrue(self.graph.add_edge(2, 3, 53))
        self.assertEqual(self.graph.e_size(), 4)
        self.assertEqual(self.graph.get_mc(), 14)



    def test_all_edge_in_and_out(self):
        self.assertTrue(self.graph.add_node(0, (0, 7, 2)))
        self.assertFalse(self.graph.add_node(0, (0, 5, 9)))
        self.assertEqual(self.graph.v_size(), 1)
        self.assertTrue(self.graph.add_node(1, (0, 7, 3)))
        self.assertTrue(self.graph.add_edge(0, 1, 100))
        self.assertFalse(self.graph.add_edge(0, 1, 510))
        self.assertTrue(self.graph.add_edge(1, 0, 370))
        self.assertFalse(self.graph.add_edge(1, 0, 445))
        self.assertEqual(self.graph.e_size(), 2)
        self.assertTrue(self.graph.remove_edge(1, 0))
        self.assertFalse(self.graph.remove_edge(1, 0))
        self.assertEqual(self.graph.e_size(), 1)
        self.assertEqual(self.graph.get_mc(), 5)
        print(self.graph.get_all_v())
        print(self.graph.all_out_edges_of_node(0))
        print(self.graph.all_in_edges_of_node(1))



    def test_add_edge(self):
        graph = DiGraph()
        graph.add_node(1)
        graph.add_node(3)
        graph.add_edge(1, 3, 1)
        self.assertEqual(graph.all_in_edges_of_node(3).get(1), 1, "OK")
        self.assertEqual(graph.all_out_edges_of_node(1).get(3), 1, "OK")
        graph.add_edge(1, 3, 1)
        self.assertEqual(graph.all_out_edges_of_node(1).get(3), 1, "failed")
        self.assertEqual(graph.all_in_edges_of_node(3).get(1), 1, "failed")
        self.assertEqual(graph.e_size(), 1, "failed")
        graph.add_edge(1, 1, 1)
        self.assertEqual(graph.all_out_edges_of_node(1).get(1), None, "failed")
        self.assertEqual(graph.all_in_edges_of_node(1).get(1), None, "failed")
        self.assertEqual(graph.e_size(), 1, "failed")



