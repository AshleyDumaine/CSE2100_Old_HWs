//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 05
//November 18, 2013
//NOTE: This adds doubly linked list methods
public class Node 
{
	private Object _element;
	private int _number;
	private String _tuple;
	private Node _next;
	private Node _rightChild;
	private Node _leftChild;
	private Node _parent;
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
	//sets up data node with no next or previous
	public Node(Object element, int number)
	{
		this(null, element, number, null);
	}
	//sets up data with a next reference
	public Node(Node previous, Object element, String tuple, Node next)
	{
		_previous = previous;
		_element = element;
		_next = next;
	}
	//sets up data with a next reference
	public Node(Node previous, Object element, int number, Node next)
	{
		_previous = previous;
		_number = number; //??
		_element = element;
		_next = next;
	}
	//Sets up data with parent
	public Node (Node parent, Object element, int number, Node right, Node left)
	{
		_parent = parent;
		_number = number; //??
		_element = element;
		_rightChild = right;
		_leftChild = left;		
	}
	public Node getParent()
	{
		return _parent;
	}
	public void setParent(Node parent)
	{
		_parent = parent;
	}
	public Node getRight()
	{
		return _rightChild;
	}
	public Node getLeft()
	{
		return _leftChild;
	}
	public void setRight(Node right)
	{
		_rightChild = right;
	}
	public void setLeft(Node left)
	{
		_leftChild = left;
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
	public int getNumber()
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
	public void setNumber(int number)
	{
		_number = number;
	}
}