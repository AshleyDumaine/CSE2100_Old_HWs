//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 06
//November 18, 2013
import java.util.Scanner;
import java.util.ArrayList;
public class MazeSolve 
{
	private Graph graph;
	private Entry[][] grid; //for graphics
	private int size;
	private int eCol;
	private int eRow;
	private int _width;
	private int[] weights;
	public static void main(String[] args) 
	{
		MazeSolve ms = new MazeSolve();
		ms.graph = new Graph();
		ms.grid = new Entry[0][0]; //entries recycled to store weights and characters 
		Scanner sc = new Scanner(System.in);
		try
		{
			int n = sc.nextInt();
			ms._width = n;
			ms.weights = new int[n * (n - 1) * 2]; //enough for internal walls
			int sCol = sc.nextInt(); //start x
			int sRow = sc.nextInt(); //start y
			ms.eCol = sc.nextInt(); //end x
			ms.eRow = sc.nextInt(); //end y
			ms.grid = new Entry[n + 1][n * 2 + 1];
			for (int i = 0; i < ms.weights.length; i++) //store wall weights
			{
				ms.weights[i] = sc.nextInt();
			}
			ms.size = n * n;
			ms.gridSetup(n, sCol, sRow, ms.eCol, ms.eRow, ms.weights);
			ms.graph = ms.setup(n, sCol, sRow, ms.eCol, ms.eRow, ms.weights);
			System.out.println();
			ms.graph = ms.primJarnik(ms.graph); //prints coordinates while removing
			ms.mazePrint(n); //prints resulting maze
			Vertex startVertex = ms.graph.getVertex(0);
			ms.depthFirstSearch(ms.graph, startVertex);
			//print dfs maze path vertex coordinates
			ms.mazePrint(n); //print resulting maze with path
		}
		finally
		{
			sc.close();
		}
	}

	private void mazePrint(int width) 
	{
		for (int i = 0; i <= width; i++)
		{
			for (int j = 0; j <= width * 2; j++)
			{
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	public SinglyLinkedList depthFirstSearch(Graph graph, Vertex vertex) //I'm pretty sure the algorithm
	{															//itself is right, printing is the issue
		SinglyLinkedList discoveries = new SinglyLinkedList();
		vertex.markVisited();
		ArrayList<Edge> adjEdges = vertex.getAdjacentEdges();
		for (int i =0; i < adjEdges.size(); i++)
		{
			if(!adjEdges.get(i).isVisited())
			{
				if (!graph.opposite(vertex, adjEdges.get(i)).isVisited())
				{
					//printing only, not part of algorithm
					if ((vertex.getY()/2 != 2 || vertex.getX() != 1) && (vertex.getX() == 0 || vertex.getX() != 2))
					{
						//print out dfs vertices
						System.out.println((vertex.getY() - 1) / 2 + " " + vertex.getX());
						Entry data = new Entry();
						data.setValue("+");
						data.setKey(vertex.getDistance());
						grid[vertex.getX() + 1][vertex.getY()] = data;
						grid[eCol + 1][eRow * 2 + 1] = data; //exit point
					}
					adjEdges.get(i).markDiscoveryEdge();
					Node data2 = new Node();
					data2.setElement(adjEdges.get(i));
					discoveries.addLast(data2);
					depthFirstSearch(graph, graph.opposite(vertex, adjEdges.get(i)));
				}
				else
				{
					adjEdges.get(i).markBackEdge();
				}
			}
		}
		return discoveries;
	}
	//sets up grid before removing internal walls
	public void gridSetup(int width, int sCol, int sRow, int eCol, int eRow, int[] weights)
	{
		for (int i = 0; i <= width; i++)
		{
			for (int j = 0; j <= width * 2; j++)
			{
				Entry data = new Entry(); //start and end points
				if ((i == sCol + 1 && j == sRow * 2) || (i == eCol + 1 && j == eRow * 2 + 2))
				{
					data.setValue(" ");
					data.setKey(0);
					grid[i][j] = data;
				}
				else if (i == 0 && j % 2 == 0) //fix solid line on top row
				{
					data.setValue(" ");
					data.setKey(Integer.MAX_VALUE);
					grid[i][j] = data;
				}
				else if (i != 0 && j % 2 == 0) //vertical bars
				{						
					data.setValue("|");
					data.setCoordinates(i + " " + j + " " + (i + width) + " " + (j + width) + '\n');
					data.setKey(Integer.MAX_VALUE);
					grid[i][j] = data;
				}
				else //horizontal bars
				{
					data.setValue("_");
					data.setCoordinates(i + " " + j + " " + (i + 1) + " " + (j + 1) + '\n');
					data.setKey(Integer.MAX_VALUE);
					grid[i][j] = data;
				}
			}
		}
	}
	//makes the graph coresponding to the grid
	public Graph setup(int width, int sCol, int sRow, int eCol, int eRow, int[] weights)
	{
		//set up vertices
		int index = 0;
		int graphDimensions = width * 2;
		for (int i = 0; i <= width; i++)
		{
			for (int j = 0; j <= graphDimensions; j++)
			{
				if (j % 2 == 1)
				{
					Vertex newVertex = new Vertex(index, i, j);
					graph.insertVertex(newVertex);
					index++;
				}
			}
		}
		ArrayList<Vertex> vList = graph.getVertexList();
		Vertex[] vertexArray = new Vertex[weights.length]; //need to reference by index
		int vertexArrayIndex = 0;
		//put into array
		for (int i = 0; i < vList.size(); i++) //???
		{
			vertexArray[vertexArrayIndex] = vList.get(i);
			vertexArrayIndex++;
		}
		//set up adjacent vertices
		for (int i = 0; i < vList.size(); i++)
		{
			for (int j = 0; j < vList.size(); j++)
			{
				int index1 = vList.get(i).getVertexValue();
				int index2 = vList.get(j).getVertexValue();
				if ((index2 - index1 == 1 && index1 % width != 0) || index2 - index1 == width)
				{    //horizontal                                      //vertical
					vList.get(i).setAdjacentVerts(vList.get(j));
					vList.get(j).setAdjacentVerts(vList.get(i)); //necessary?
				}
			}
		}
		//set up edges
		int weightsIndex = 0;
		for (int j = 0; j < width - 1; j++) //leave it
		{
			for (int i = 0; i <= width - 1; i++)
			{
				Edge newEdge = new Edge(vertexArray[i * (j + 1)], 
						vertexArray[i * (j + 1) + 1], weights[weightsIndex]); //horizontal
				graph.insertEdge(newEdge);
				weightsIndex++;
			}
			for (int k = 0; k <= width - 1; k++)
			{
				Edge newEdge = new Edge(vertexArray[k * (j + 1)], 
						vertexArray[k * (j + 1) + width], weights[weightsIndex]); //vertical
				graph.insertEdge(newEdge);
				weightsIndex++;
			}
		}
		//set up incident edges ex: o-o-o
		ArrayList<Edge> edgeList = graph.getEdgeList();
		for (int i = 0; i < edgeList.size(); i++)
		{
			for (int j = 0; j < edgeList.size(); j++)
			{
				Vertex[] ends1 = edgeList.get(i).getEndVertices();
				Vertex[] ends2 = edgeList.get(j).getEndVertices();
				if (ends1[0] == ends2[0] || ends1[1] == ends2[1] || ends1[1] == ends2[0] || ends1[0] == ends2[0])
				{
					edgeList.get(i).setAdjacentEdges(edgeList.get(j));
					edgeList.get(j).setAdjacentEdges(edgeList.get(i)); //necessary?
				}
			}
		}
		//set up incident edges for each vertex
		for (int i = 0; i < edgeList.size(); i++)
		{
			Vertex[] ends1 = edgeList.get(i).getEndVertices();
			for (int j = 0; j < edgeList.size(); j++)
			{
				Vertex[] ends2 = edgeList.get(j).getEndVertices();
				if (ends1[0] == ends2[0] && i != j)
				{
					ends1[0].setAdjacentEdges(edgeList.get(i));
					ends1[0].setAdjacentEdges(edgeList.get(j));
					ends2[0].setAdjacentEdges(edgeList.get(i));
					ends2[0].setAdjacentEdges(edgeList.get(j));
				}
				else if (ends1[1] == ends2[1] && i != j)
				{
					ends1[1].setAdjacentEdges(edgeList.get(i));
					ends1[1].setAdjacentEdges(edgeList.get(j));
					ends2[1].setAdjacentEdges(edgeList.get(i));
					ends2[1].setAdjacentEdges(edgeList.get(j));
				}
				else if (ends1[0] == ends2[1] && i != j)
				{
					ends1[0].setAdjacentEdges(edgeList.get(i));
					ends1[0].setAdjacentEdges(edgeList.get(j));
					ends2[1].setAdjacentEdges(edgeList.get(i));
					ends2[1].setAdjacentEdges(edgeList.get(j));
				}
				else if (ends1[1] == ends2[0] && i != j)
				{
					ends1[1].setAdjacentEdges(edgeList.get(i));
					ends1[1].setAdjacentEdges(edgeList.get(j));
					ends2[0].setAdjacentEdges(edgeList.get(i));
					ends2[0].setAdjacentEdges(edgeList.get(j));
				}
			}
		}
		return graph;
	}
	//find MST and print resulting maze
	//I'm fairly sure the algorithm itself is correct, printing may again be an issue.
	public Graph primJarnik(Graph graph)
	{
		Heap heap = new Heap(size);
		Vertex s = graph.getVertex(0);
		for (int i = 0; i < graph.getVertexList().size(); i++)
		{
			if (graph.getVertexList().get(i) == s)
			{
				graph.getVertexList().get(i).setDistance(0);
			}
			else
			{
				graph.getVertexList().get(i).setDistance(Integer.MAX_VALUE);
			}
			graph.getVertexList().get(i).setParent(null);
			Entry data = new Entry(graph.getVertexList().get(i).getDistance(), graph.getVertexList().get(i));
			heap.insert(data);
		}
		while (!heap.isEmpty())
		{
			Entry temp = heap.removeMin();
			Vertex v = (Vertex) temp.getValue();
			Vertex vertex = new Vertex(v.getAdjacentEdges(), v.getAdjacentVertices(), temp.getKey(), v.getX(), v.getY());
			if (vertex.getX() != _width - 1)
			{
				Entry data2 = grid[vertex.getX() + 1][vertex.getY()]; //modify grid
				data2.setValue(" ");
				data2.setKey(0);
				Entry data3 = grid[1][vertex.getY() + 1]; //modify grid
				data3.setValue(" ");
				data3.setKey(0);
				Entry data = grid[1][_width * 2];
				data.setValue("|");
				data.setKey(0);
			}
			ArrayList<Edge> adjE = vertex.getAdjacentEdges();
			for (int i = 0; i < adjE.size(); i++)
			{
				Edge edge = adjE.get(i);
				Vertex opposite = graph.opposite(v, edge);
				int weight  = edge.getEdgeValue();
				if (weight < opposite.getDistance())
				{   //print coordinates
					System.out.println(vertex.getX() + " " + ((vertex.getY() - 1) / 2) + " " + opposite.getX() + " " + ((opposite.getY() - 1) / 2));
					opposite.setDistance(weight);
					opposite.setParent(edge);
					v.setParent(edge);
					heap.swapValues(opposite.getVertexValue(), weight);
				}
			}
		}
		return graph;
	}
}