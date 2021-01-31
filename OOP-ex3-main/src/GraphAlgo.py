from typing import List
import json
from GraphAlgoInterface import GraphAlgoInterface
from DiGraph import DiGraph, NodeData
from src import GraphInterface
import matplotlib.pyplot as plotAY
import random
import math
from queue import PriorityQueue
from typing import Dict
class GraphAlgo(GraphAlgoInterface):

    def __init__(self, g: DiGraph = DiGraph()):
        self.graph = g

    def get_graph(self) -> GraphInterface:
        return self.graph

    def load_from_json(self, file_name: str) -> bool:
        try:
            with open(file_name, 'r') as gr:
                json_file = json.load(gr)

            self.graph = DiGraph()
            for t in json_file.get('Nodes'):
                if t.get('pos') is not None:
                    li = t.get('pos').split(",")
                    pos = tuple(map(float, li))
                    self.graph.add_node(t.get('id'), pos)
                else:
                    self.graph.add_node(t.get('id'))

            for t in json_file.get('Edges'):
                self.graph.add_edge(t.get('src'), t.get('dest'), t.get('w'))
            return True

        except FileNotFoundError:
            return False



    def save_to_json(self, file_name: str) -> bool:
        nodes = []
        for t in self.graph.get_all_v().values():
            node:NodeData= t
            if node.pos is None:
                nodes.append({"id": node.getKey()})
            else:
                str_pos = str(node.pos[0])+","+str(node.pos[1]) + ","+str(node.pos[2])
                nodes.append({"pos": str_pos,"id": node.getKey()})

        edges = []
        for x in self.graph.get_all_v().keys():
            for d, value in self.graph.all_out_edges_of_node(x).items():
                edges.append({"src": x, "w":value, "dest": d})


        lists= {'Edges': edges,'Nodes': nodes}
        try:
            with open(file_name, 'w') as f:
                json.dump(lists,f)
            return True
        except FileNotFoundError:
            return False

    def shortest_path(self, id1: int, id2: int) -> (float, list):

        if id1 not in self.graph.get_all_v() or id2 not in self.graph.get_all_v():
            return math.inf,[]
        if id1 is id2 :
            return 0,[id1]

        # init all vertexs
        for node in self.graph.get_all_v().values():
            node.tag=-1
            node.weight=math.inf
            node.info=""

        q = PriorityQueue()
        src:NodeData=self.graph.get_all_v().get(id1)
        src.weight=0
        q.put(src)

        while not q.empty():
            v1:NodeData = q.get()
            #Dict[int,float]
            for key,w in self.graph.all_out_edges_of_node(v1.getKey()).items():
                v2:NodeData = self.graph.get_all_v().get(key)
                dest = v1.weight+w
                if dest<v2.weight:
                    v2.weight=dest
                    #parents
                    v2.tag=v1.getKey()
                    q.put(v2)

        dest: NodeData = self.graph.get_all_v().get(id2)
        if dest.weight is math.inf:
            return math.inf,[]
        paths=[]
        paths.insert(0,dest.getKey())

        parents = dest.tag
        while parents != -1:
            paths.insert(0, parents)
            parents = self.graph.get_all_v().get(parents).tag

        return dest.weight,paths

    def connected_component(self, id1: int) -> list:
        stack=[]
        onStack:Dict[int,bool]=dict()
        low: Dict[int, int] = dict()
        ids: Dict[int, int] = dict()
        id = 0
        #answer
        path=[]
        for node in self.graph.get_all_v().keys():
            low.update({node:0})
            ids.update({node: 0})
            onStack.update({node: False})

        recu=[(id1,0)]
        while recu:
            v,i=recu[-1]
            del recu[-1]
            if i==0:
                id+=1
                low.update({v:id})
                ids.update({v: id})
                stack.append(v)
                onStack.update({v:True})
            recurse=False
            k=0
            for w in self.graph.all_out_edges_of_node(v).keys():
                if ids.get(w) == 0:
                    recu.append((v,k+1))
                    recu.append((w, 0))
                    recurse=True
                    k+=1
                    break
                elif onStack.get(w):
                    k+=1
                    low.update({v:min(low.get(v),low.get(w))})
            if recurse: continue
            if ids.get(v) == low.get(v):
                path=[]
                while stack:
                    node = stack.pop()
                    low.update({node:ids.get(v)})
                    onStack.update({node:False})
                    path.insert(0,node)
                    if node == v : break
            if recu:
                w=v
                v,_=recu[-1]
                low.update({v:min(low.get(v),low.get(w))})
        return path

    def connected_components(self) -> List[list]:
        stack = []
        onStack: Dict[int, bool] = dict()
        low: Dict[int, int] = dict()
        ids: Dict[int, int] = dict()
        id = 0

        path = []
        lists_path=[]
        for node in self.graph.get_all_v().keys():
            low.update({node: 0})
            ids.update({node: 0})
            onStack.update({node: False})
        for x in self.graph.get_all_v():
            if ids.get(x) == 0:
                id1=x
                recu = [(id1, 0)]
                while recu:
                    v, i = recu[-1]
                    del recu[-1]
                    if i == 0:
                        id += 1
                        low.update({v: id})
                        ids.update({v: id})
                        stack.append(v)
                        onStack.update({v: True})
                    recurse = False
                    k = 0
                    for w in self.graph.all_out_edges_of_node(v).keys():
                        if ids.get(w) == 0:
                            recu.append((v, k + 1))
                            recu.append((w, 0))
                            recurse = True
                            k += 1
                            break
                        elif onStack.get(w):
                            k += 1
                            low.update({v: min(low.get(v), low.get(w))})
                    if recurse: continue
                    if ids.get(v) == low.get(v):
                        path = []
                        while stack:
                            node = stack.pop()
                            low.update({node: ids.get(v)})
                            onStack.update({node: False})
                            path.insert(0, node)
                            if node == v: break
                        lists_path.insert(0,path)
                    if recu:
                        w = v
                        v, _ = recu[-1]
                        low.update({v: min(low.get(v), low.get(w))})

        return lists_path

    def plot_graph(self) -> None:
        x = []
        y = []
        z = []
        for id, value in self.graph.get_all_v().items():
            node:NodeData = value
            if node.pos is None:
                node.pos= (random.uniform(35.18, 35.2),random.uniform(2.1, 32.2))
            x.append(node.pos[0])
            y.append(node.pos[1])
            z.append(id)

        fig, ax = plotAY.subplots()
        for p, txt in enumerate(z):
            ax.annotate(z[p], (x[p], y[p]))

        plotAY.plot(x, y, "*", color ="red")

        for s in self.graph.get_all_v().keys():
            for d,value in self.graph.all_out_edges_of_node(s).items():
                node_s:NodeData = self.graph.get_all_v().get(s)
                node_d:NodeData = self.graph.get_all_v().get(d)

                plotAY.arrow(node_s.pos[0], node_s.pos[1], (node_d.pos[0] - node_s.pos[0]),
                             (node_d.pos[1] - node_s.pos[1]), length_includes_head = True, width=0.000005,
                             head_width = 0.0002, color = 'grey')

        plotAY.show()