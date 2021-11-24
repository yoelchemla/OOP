# oop - ex2

![image](https://github.com/ShiraAnaki130/OOP_ex2/blob/master/data/pokemonsStart.jpg?raw=true)

## READEME - Pokemon Game :
### Authors: Yoel Chemla and Afik Peretz
This assignment is based on previous assignments in an object-oriented programming course and on the implementation of a directed and weighted graph.
The assignment is made up of two parts: 

*Part 1* - Implementation of a weighted & directed graph and algorithms which operate on the graph.

*Part 2* - Frontend design of the game.

# The implements : Part A - 


-   *directed_weighted_graph*  (implemented by  *DWGraph_DS*) - an object which represents a directed weighted graph.
    
    -   *node_data*  (implemented by  *NodeData*) - An object that represents the vertices of the graph and the actions that are performed on them. The vertex receives a key, tag, and data.
    -    *edge_data*  (implemented by  *EdgeData*) - An object that represents the edges of the graph and the actions that are performed on them. The edges receives a src, dest, and wheight.
-   *dw_graph_algorithms*  (implemented by  *DWGraph_Algo*) - An object that implements some basic graph algorithms with Dijkstra's and BFS algorithms.
    
- HashMap is used for its efficient complexity, for example the methods - add ,size,values,remove,getV (and more) that run on O(1).
   
    



#  Methods
### DWGraph_DS
This class implements the interfaces - directed_weighted_graph and  Serializable.

*node_data*

| Name |  Description|
|--|--|
| NodeInfo () |  Constructor|
| NodeInfo(int k) | Constructor that got a key   |
| getKey() | Returns the nodes key |
| getInfo() | Returns the nodes String |
| setInfo(String s) | Set the info nodes |
| getTag() | Returns the nodes tag |
| setTag(double t) |Set the tag nodes  |
| compareTo(node_info n) | comper between two nodes tag |

*edge_data*
| Name |  Description|
|--|--|
| edge_data()|  Constructor|
| setWeight(int dest, double w)| Set the weight of the edge
| connectEdge(int n, double w) | Connect between to nodes |
| hasNi(int n) |  Check if to nodes key have a neibers|
| getNi() | Returns a collection of the neibers |
|getWeight(int dest) | Returns the whight of edge |
| removeSrc()  |  Remove all the nodes in the Hashmap|
|  removeEdge(int n)| Remove a edge |


*DWGraph_DS*

| Name |  Description|
|--|--|
| DWGraph_DS ()|  Constructor|
| getNode(int key)| Returns a node with a key
| hasEdge(int node1, int node2) |Check if is an edge is between two nodes |
| getEdge(int node1, int node2) |  Returns the weight of the edge|
| addNode(int key) | Add node to the graph |
|connect(int node1, int node2, double w) | Connect two nodes and init the weight of the edge |
| getV()  |  Returns a Collection that represe all the nodes in the graph|
|  getV(int node_id)| Returns all the neighbors of node id |
| removeNode(int key)| Remove a node from the graph |
| nodeSize()| Returns the number of nodes in the graph |
| edgeSize()| Returns the number of edges in the graph |
|getMC()  | Returns the number of actions performed on the graph |


### DWGraph_Algo

Implementation of the interfaces -  dw_graph_algorithms.

| Name |  Description|
|--|--|
|DWGraph_Algo() | Constructors|
| init(weighted_graph g)|  Initialization the graph|
|getGraph() | Returns a pointer to the initialized graph
| copy()| Copy constructor - deep copy |
| isConnected()|  Checking connectivity of the graph|
| shortestPathDist(int src, int dest)| Returns the sort distance between src to dest whit Dijkstra's algorithm |
|shortestPath(int src, int dest)| Returns the way from src to dist whit Dijkstra's algorithm and whit a list |
| save(String file)  | Saves a graph to a file whit Json |
| load(String file) | Load a graph from a file whit Jason |



#  Part B - "The Pokemon Game".
-Implementation of the user interface. (GUI, JPanel, JFarme).
 
-For the purpose of creating the GUI we implemented the myPanel and myFarme classes which represent and create a drawing of a graph, agent and Pokemon.

## option to tun the game:

# Option 1:

Open you command line and type the following command:
java -jar EX2.jar ID Level_Number

# Option 2:

Open the EX2.jar and fill the following:

Enter your ID (you can type other number).
Enter the level [0-23].
# Enjoy!
