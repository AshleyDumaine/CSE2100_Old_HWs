//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 02
//September 15, 2013

//NOTE: This adds doubly linked list methods
public class Node 
{
	private Object _element;
	private Node _next;
	private Node _previous;
	
	//sets up blank node with no next or previous
	public Node()
	{
		this(null, null, null);
	}
	//sets up data node with no next or previous
	public Node(Object element)
	{
		this(null, element, null);
	}
	//sets up data with a next reference
	public Node(Node previous, Object element, Node next)
	{
		_previous = previous;
		_element = element;
		_next = next;
	}
	//returns next node
	public Node getNext()
	{
		return _next;
	}
	//returns precious node
	public Node getPrevious()
	{
		return _previous;
	}
	//returns data of node
	public Object getElement()
	{
		return _element;
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
}