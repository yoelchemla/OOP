# OOP-ex3
### This project deals with directional weighted graph in python, we used two interfaces to implement the graph properties in the class.

**directed graph:** is a graph that is made up of a set of vertices connected by edges, where the edges have a direction associated with them.


in this project, we worked in two parts

## part one: 
implements GraphInterface.py:
This interface represents an directional weighted graph. the class that implements the interface is DiGraph. in this class, the functions of the actions on the graph are implemented, such as adding a vertex, deleting a vertex, deleting a rib, and more.. The main data structure that we chose to use to implement the project is dictionary. 

## part two:
GraphAlgoInterface.py - this interface represents the "regular" including:

connected component(id1)- Finds the Strongly Connected Component that node id1 is a part
connected components()- Finds all the Strongly Connected Component(SCC) in the graph.

include tests for the method.

the way to find the **Shortest path** from src to destination it's a __Dijkstra's algorithm__
![](https://upload.wikimedia.org/wikipedia/commons/5/57/Dijkstra_Animation.gif)

**Save(file)-** saves this weighted (directed) graph and give a file name - in JSON 

**Load(file)-** This method load a graph to this graph algorithm.



![graph](https://user-images.githubusercontent.com/74509202/104618739-c75feb00-5695-11eb-8f64-e79b7016010f.jpeg)

# Comparisons:

we show the result of running time comparisons between Python, Java and Networkx. We made the comparisons on 6 graphs of different sizes (10, 100, 1000, 10000, 20,000, 30000 vertices):

![1](https://user-images.githubusercontent.com/74509202/104630280-44459180-56a3-11eb-8dca-eb1cb2b6d0df.jpeg)
![2](https://user-images.githubusercontent.com/74509202/104630472-748d3000-56a3-11eb-9b70-af40792e4c34.jpeg)
![3](https://user-images.githubusercontent.com/74509202/104630568-925a9500-56a3-11eb-893e-89583b9b3c39.jpeg)
![4](https://user-images.githubusercontent.com/74509202/104630692-be761600-56a3-11eb-824c-d96f11506e29.jpeg)
![5](https://user-images.githubusercontent.com/74509202/104630747-da79b780-56a3-11eb-928b-0f9df4e34b37.jpeg)
![6](https://user-images.githubusercontent.com/74509202/104630801-f2513b80-56a3-11eb-9c73-83cff97614ed.jpeg)

## link:
(https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)

(https://pypi.org/project/tarjan/)

