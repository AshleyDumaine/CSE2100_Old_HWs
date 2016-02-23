//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 01
//September 12, 2013
public class SinglyLinkedList 
{
	protected Node _tail;
	protected Node _head;
	protected int _size;
	public SinglyLinkedList()
	{
		_tail = null;
		_head = null;
		_size = 0;
	}

	public int getSize()
	{
		return _size;
	}

	@Override
	public String toString()
	{
		String result = "";
		Node tempNode = this._head;
		while (tempNode != null)
		{
			result = result + tempNode.getElement();
			tempNode = tempNode.getNext();
		}
		return result;
	}

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
			_tail = data;
			data.setNext(null);
		}
		_size++;
	}

	public void addFirst(Node data)
	{
		if (_head == null)
		{
			_head = _tail =  data;
			data.setNext(null);
		}
		else
		{
			data.setNext(_head);
			_head = data;	
		}
	}

	public Node removeFirst()
	{
		Node tempNode = _head;
		_head = _head.getNext();
		_size--;
		return tempNode;
	}
}
