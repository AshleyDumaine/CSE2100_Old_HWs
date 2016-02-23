//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 04
//October 20, 2013
public class Queue
{
	private Object[] _circularQueue;
	private int _front;
	private int _rear;
	private int _size;
	public Queue(int length)
	{
		_circularQueue = new Object[length];
		_size = 0;
		_front = 0;
		_rear = 0;
	}
	public int size()
	{
		return _size;
	}
	public Object[] front()
	{
		return (Object[]) _circularQueue[_front];
	}
	public boolean isEmpty()
	{
		if (_size == 0)
		{
			return true;
		}
		return false;
	}
	public void enqueue(Object[] data) throws FullQueueException
	{
		if (_size == _circularQueue.length - 1)
		{
			throw new FullQueueException("Queue is full");
		}
		_circularQueue[_rear] = data;
		_rear = (_rear + 1) % _circularQueue.length; //make queue circular
		_size++;
	}
	public Object[] dequeue() throws QueueEmptyException
	{
		if (this.isEmpty())
		{
			throw new QueueEmptyException("Queue is empty");
		}
		Object[] temp = (Object[]) _circularQueue[_front];
		_circularQueue[_front] = null;
		_front = (_front + 1) % _circularQueue.length; //make queue circular
		_size--;
		return temp;
	}
}
