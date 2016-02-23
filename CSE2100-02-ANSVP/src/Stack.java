//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 02
//September 15, 2013
public class Stack 
{
	//delegates many functions to the SinglyLinkedList class
	protected SinglyLinkedList _stack;
	//create new blank stack;
	public Stack()
	{
		_stack = new SinglyLinkedList();
	}
	//add object to the top of the stack
	public void push(Object pushedObject)
	{
		Node data = new Node(pushedObject);
		_stack.addFirst(data);
	}
	//remove object from the top of the stack and return for use
	public Object pop() throws EmptyStackException
	{
		if (this.isEmpty())
		{
			throw new EmptyStackException("Stack is empty");
		}
		Node tempNode = _stack.removeFirst();
		return tempNode;
	}
	//return but don't remove the top of the stack
	public Integer top() throws EmptyStackException
	{
		if (this.isEmpty())
		{
			throw new EmptyStackException("Stack is empty");
		}
		return Integer.parseInt((String) _stack.getFirst().getElement());
	}
	//tells if there are any more objects left to pop in the stack
	public boolean isEmpty()
	{
		return _stack.isEmpty();
	}
}
