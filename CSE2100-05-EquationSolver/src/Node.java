//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 05
//October 30, 2013

//NOTE: This adds doubly linked list methods
public class Node 
{
	private Object _element;
	private long _number;
	private String _tuple;
	private Node _next;
	private Node _previous;
	
	//sets up blank node with no next or previous
	public Node()
	{
		this(null, null, null, null);
	}
	//sets up data node with no next or previous
	public Node(Object element, String tuple)
	{
		this(null, element, tuple, null);
	}
	//sets up data with a next reference
	public Node(Node previous, Object element, String tuple, Node next)
	{
		_previous = previous;
		_element = element;
		_next = next;
	}
	//sets up data with a next reference
	public Node(Node previous, Object element, long number, Node next)
	{
		_previous = previous;
		_number = number; //??
		_element = element;
		_next = next;
	}
	//Sets up data
	public Node (Object element, long number)
	{
		_number = number; //??
		_element = element;	
	}
	//returns next node
	public Node getNext()
	{
		return _next;
	}
	//returns previous node
	public Node getPrevious()
	{
		return _previous;
	}
	//returns data of node
	public Object getElement()
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
	public void setNext(Node newNext)
	{
		_next = newNext;
	}
	//lets user change node data
	public void setElement(Object element)
	{
		_element = element;
	}
	public void setPrevious(Node previous)
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
}