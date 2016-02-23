import java.util.ArrayList;

//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 05
//November 18, 2013
public class Vertex 
{
	private boolean visited;
	private int _x;
	private int _y;
	private int _vertexValue; //ID number
	private ArrayList<Edge> incEdge;
	private ArrayList<Vertex> incVert;
	private Edge _parentEdge;
	private int _distance;
	
	public Vertex()
	{
		incEdge = new ArrayList<Edge>();
		incVert = new ArrayList<Vertex>();
		_vertexValue = 0;	
	}
	public Vertex(ArrayList<Edge> incidentE, ArrayList<Vertex> incidentV, int vertexValue, int x, int y)
	{
		incEdge = new ArrayList<Edge>();
		incVert = new ArrayList<Vertex>();
		incEdge.addAll(incidentE);
		incVert.addAll(incidentV);
		_x = x;
		_y = y;
		_vertexValue = vertexValue;
	}
	public Vertex(int vertexValue, int x, int y)
	{
		incEdge = new ArrayList<Edge>();
		incVert = new ArrayList<Vertex>();
		_x = x;
		_y = y;
		_vertexValue = vertexValue;
	}
	//coordinates of vertex
	public int getX()
	{
		return _x;
	}
	public int getY()
	{
		return _y;
	}
	
	public void markVisited()
	{
		visited = true;
	}
	public boolean isVisited()
	{
		return visited;
	}
	
	public void setDistance(int distance)
	{
		_distance = distance;
	}
	public int getDistance()
	{
		return _distance;
	}
	public void setParent(Edge parent)
	{
		_parentEdge = parent;
	}
	public Edge getParent()
	{
		return _parentEdge;
	}
	
	public int getVertexValue()
	{
		return _vertexValue;
	}
	public void setVertexValue(int vertexValue)
	{
		_vertexValue = vertexValue;
	}
	
	public ArrayList<Vertex> getAdjacentVertices()
	{
		return incVert;
	}
	public void setAdjacentVerts(Vertex vert)
	{
		incVert.add(vert);
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