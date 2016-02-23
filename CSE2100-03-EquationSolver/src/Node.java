//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 02
//September 15, 2013

//NOTE: This adds doubly linked list methods
public class Node<Marker> 
{
	private Marker _element;
	private long _number;
	private String _tuple;
	private Node<Marker> _next;
	private Node<Marker> _previous;
	
	//sets up blank node with no next or previous
	public Node()
	{
		this(null, null, null, null);
	}
	//sets up data node with no next or previous
	public Node(Marker element, String tuple)
	{
		this(null, element, tuple, null);
	}
	//sets up data node with no next or previous
	public Node(Marker element, long number)
	{
		this(null, element, number, null);
	}
	//sets up data with a next reference
	public Node(Node<Marker> previous, Marker element, String tuple, Node<Marker> next)
	{
		_previous = previous;
		_element = element;
		_next = next;
	}
	//sets up data with a next reference
	public Node(Node<Marker> previous, Marker element, long number, Node<Marker> next)
	{
		_previous = previous;
		_element = element;
		_next = next;
	}
	//returns next node
	public Node<Marker> getNext()
	{
		return _next;
	}
	//returns previous node
	public Node<Marker> getPrevious()
	{
		return _previous;
	}
	//returns data of node
	public Marker getElement()
	{
		return _element;
	}
	public String getTuple()
	{
		return _tuple;
	}
	public long getNumber()
	{
		return _number;
	}
	//lets user change next reference
	public void setNext(Node<Marker> newNext)
	{
		_next = newNext;
	}
	//lets user change node data
	public void setElement(Marker element)
	{
		_element = element;
	}
	public void setPrevious(Node<Marker> previous)
	{
		_previous = previous;
	}
	public void setTuple(String tuple)
	{
		_tuple = tuple;
	}
	public void setNumber(long number)
	{
		_number = number;
	}
//	public String tupleToString()
//	{
//		String result = "";
//		for (int i = 0; i < _tuple.length; i++)
//		{
//			result = result + _tuple[i] + " ";
//		}
//		return result;
//	}
}