import java.util.ArrayList;

//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 06
//November 18, 2013
public class Graph //uses adjacency list
{
	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;
	public Graph()
	{
		vertexList = new ArrayList<Vertex>();
		edgeList = new ArrayList<Edge>();
	}
	public int vertexListLength()
	{
		return vertexList.size();
	}
	public int edgeListLength()
	{
		return edgeList.size();
	}
	public void insertVertex(Vertex vertex)
	{
		vertexList.add(vertex);
	}
	public ArrayList<Vertex> getVertexList()
	{
		return vertexList;
	}
	public ArrayList<Edge> getEdgeList()
	{
		return edgeList;
	}
	public void insertEdge(Edge edge)
	{
		edgeList.add(edge);
	}
	public Edge removeEdge()
	{
		return edgeList.remove(0); //remove first
	}
	public Vertex removeVertex()
	{
		return vertexList.remove(0);
	}
	public Vertex getVertex(int index)
	{
		return vertexList.get(index);
	}
	public Vertex[] endVertices(Edge edge)
	{
		return edge.getEndVertices();
	}
	public Vertex opposite(Vertex vertex, Edge edge)
	{
		//gets the vertex on the opposite end of the edge
		Vertex[] ends = edge.getEndVertices();
		if (ends[0] == vertex)
		{
			return ends[1];
		}
		else
		{
			return ends[0];
		}
	}
	public boolean areAdjacentVerts(Vertex vertex1, Vertex vertex2)
	{
		//get the adjacents of one and compare the other to the adjacents
		//if match, return true
		ArrayList<Vertex> adjV = vertex1.getAdjacentVertices();
		if (adjV.contains(vertex2))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean areAdjacentEdges(Edge edge1, Edge edge2)
	{
		//get the adjacents of one and compare the other to the adjacents
		//if match, return true
		ArrayList<Edge> adjE = edge1.getAdjacentEdges();
		if (adjE.contains(edge2))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void replace(Vertex vertex, int swap)
	{
		vertex.setVertexValue(swap);
	}
	public void replace(Edge edge, int swap)
	{
		edge.setEdgeValue(swap);
	}
}