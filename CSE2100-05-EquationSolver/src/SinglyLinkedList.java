//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 05
//October 30, 2013

//includes doubly linked list methods, but I copied this into the
//rightly named DoublyLinkedList class
public class SinglyLinkedList 
{
	protected Node _tail;
	protected Node _head;
	protected long _size;

	public SinglyLinkedList(Node head, Node tail, long size)
	{
		_head = head;
		_tail = tail;
		_size = size;
	}
	//sets up blank list of 0 length
	public SinglyLinkedList()
	{
		_tail = null;
		_head = null;
		_size = 0;
	}
	//lets user know list length
	public long getSize()
	{
		return _size;
	}
	//checks to see if the linked list is empty
	public boolean isEmpty()
	{
		return _size == 0;
	}
	//returns the head of the linked list
	public Node getFirst()
	{
		return _head;
	}
	//iterates over all the nodes and concatenates them into a string
	@Override
	public String toString()
	{
		String result = "";
		Node tempNode = this._head;
		while (tempNode != null)
		{
			result = result + tempNode.getElement() + " ";
			tempNode = tempNode.getNext();
		}
		return result;
	}
	//appends node to end of list
	public void addLast(Node data)
	{
		if (_tail == null)
		{
			data.setNext(null);
			_head = _tail = data;	
		}
		else
		{
			_tail.setNext(data);
			data.setPrevious(_tail);
			_tail = data;
			data.setNext(null);
		}
		_size++;
	}
	//appends node to front of list
	public void addFirst(Node data)
	{
		if (_head == null)
		{
			_head = _tail =  data;
			data.setNext(null);
			data.setPrevious(null);
		}
		else
		{
			data.setNext(_head);
			_head = data;	
			data.setPrevious(null);
		}
		_size++;
	}
	//deletes first node of list
	public Node removeFirst()
	{
		Node tempNode = _head;
		if (_size == 1) 
		{
			_head = _tail = null;
		}
		else
		{
			_head = _head.getNext();
		}
		_size--;
		return tempNode;
	}
}