//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 01
//September 12, 2013
public class Node 
{
	private Object _element;
	private Node _next;
	
	public Node()
	{
		this(null, null);
	}
	public Node(Object element)
	{
		this(element, null);
	}
	public Node(Object element, Node next)
	{
		_element = element;
		_next = next;
	}
	public Node getNext()
	{
		return _next;
	}
	public Object getElement()
	{
		return _element;
	}
	public void setNext(Node newNext)
	{
		_next = newNext;
	}
	public void setElement(Object element)
	{
		_element = element;
	}
}
