import java.util.ArrayList;

//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 06
//November 18, 2013
public class Edge 
{
	private boolean backEdge;
	private boolean visited;
	private boolean discoveryEdge;
	private Vertex _vertex1; 
	private Vertex _vertex2; 
	private int _edgeValue; 
	private ArrayList<Edge> incEdge;
	public Edge ()
	{
		incEdge = new ArrayList<Edge>();
		_edgeValue = 0;
		_vertex1 = null;
		_vertex2 = null;
	}
	public Edge (int edgeValue)
	{
		incEdge = new ArrayList<Edge>();
		_edgeValue = edgeValue;
		_vertex1 = null;
		_vertex2 = null;
	}
	public Edge(Vertex vertex1, Vertex vertex2, int edgeValue)
	{
		incEdge = new ArrayList<Edge>();
		_vertex1 = vertex1;
		_vertex2 = vertex2;
		_edgeValue = edgeValue;
	}
	
	public void markVisited()
	{
		visited = true;
	}
	public boolean isVisited()
	{
		return visited;
	}
	
	public void markDiscoveryEdge()
	{
		discoveryEdge = true;
	}
	public void markBackEdge()
	{
		backEdge = false;
	}
	public boolean isDiscoveryEdge()
	{
		return discoveryEdge;
	}
	public boolean isBackEdge()
	{
		return backEdge;
	}
	public Vertex[] getEndVertices()
	{
		Vertex[] _endVertices = new Vertex[2];
		_endVertices[0] = _vertex1;
		_endVertices[1] = _vertex2;
		return _endVertices;
	}
	public void setEndVertices(Vertex vertex1, Vertex vertex2)
	{
		_vertex1 = vertex1;
		_vertex2 = vertex2; 
	}
	
	public int getEdgeValue()
	{
		return _edgeValue;
	}
	public void setEdgeValue(int edgeValue)
	{
		_edgeValue = edgeValue;
	}
	
	public ArrayList<Edge> getAdjacentEdges()
	{
		return incEdge;
	} 
	public void setAdjacentEdges(Edge edge)
	{
		incEdge.add(edge); 
	}
}