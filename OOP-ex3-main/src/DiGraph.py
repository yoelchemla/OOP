from GraphInterface import GraphInterface
from typing import Dict

class DiGraph(GraphInterface):

    def __init__(self):
        self.__nodesDict:Dict[int, NodeData]=dict()
        self.__edgesOutFromNode:Dict[int, Dict[int, float]] = dict()
        self.__edgesInToNode:Dict[int, Dict[int, float]]=dict()
        self.__counterMC = 0
        self.__edgeSize=0

    def v_size(self) -> int:
        return len(self.__nodesDict)

    def e_size(self) -> int:

        return self.__edgeSize

    def get_mc(self) -> int:
        return self.__counterMC

    def get_all_v(self) -> dict:
        return self.__nodesDict

    def all_in_edges_of_node(self, id1: int) -> dict:
        return self.__edgesInToNode.get(id1)

    def all_out_edges_of_node(self, id1: int) -> dict:
        return self.__edgesOutFromNode.get(id1)

    def add_edge(self, id1: int, id2: int, weight: float) -> bool:
        if id1 is not id2 and id1 in self.__nodesDict and id2 in self.__nodesDict and id2 not in self.__edgesOutFromNode.get(id1):
            self.__edgesOutFromNode.get(id1).update({id2:weight})
            self.__edgesInToNode.get(id2).update({id1:weight})
            self.__counterMC += 1
            self.__edgeSize += 1
            return True
        else:
            return False

    def add_node(self, node_id: int, geoLocation: tuple = None) -> bool:
        node = NodeData(node_id, geoLocation)
        if node_id in self.__nodesDict:
            return False
        else:
            self.__nodesDict.update({node_id: node})
            self.__edgesOutFromNode.update({node_id: dict()})
            self.__edgesInToNode.update({node_id: dict()})
            self.__counterMC += 1
            return True

    def remove_node(self, node_id: int) -> bool:
        if node_id not in self.__nodesDict:
            return False

        else:
            for t in self.all_out_edges_of_node(node_id).keys():
                self.__edgesInToNode.get(t).pop(node_id)
                
                self.__edgeSize -= 1

            for t in self.all_in_edges_of_node(node_id):
                self.__edgesOutFromNode.get(t).pop(node_id)
               
                self.__edgeSize -= 1

            self.__edgesInToNode.pop(node_id)
            self.__edgesOutFromNode.pop(node_id)
            self.__nodesDict.pop(node_id)
            self.__counterMC += 1
            return True

    def remove_edge(self, node_id1: int, node_id2: int) -> bool:
        if node_id1 is node_id2 or node_id1 not in self.__nodesDict or node_id2 not in self.__nodesDict or node_id2 not in self.__edgesOutFromNode.get(
                node_id1):
            return False
        else:
            self.__edgesOutFromNode.get(node_id1).pop(node_id2)
            self.__edgesInToNode.get(node_id2).pop(node_id1)
            self.__counterMC += 1
            self.__edgeSize -= 1
            return True


    def __repr__(self):
        return "Graph %s"%(self.__nodesDict)


class NodeData(object):
    def __init__(self, key :int, tup:tuple):
        self.__key =key
        self.pos= tup
        self.tag = 0
        self.info = ""
        self.weight = 0


    def getKey(self) -> int:
        return self.__key

    def __lt__(self, other):
        return (self.weight,self.__key)<(other.weight,other.getKey())

    def __repr__(self):
        return "NodeData %s"%(self.__key)
