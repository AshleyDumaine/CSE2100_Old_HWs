//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 06
//November 18, 2013
public class Heap //ADAPTABLE Priority queue shoehorned into a heap?
{
	private Node[] heapArray; //array instead of linked list because I gave up on that
	private int maxSize;
	private int currentSize; //uses index as location
	public Heap(int max)
	{
		maxSize = max;
		currentSize = 0;
		heapArray = new Node[maxSize];
	}
	public boolean isEmpty()
	{ 
		return currentSize == 0; 
	}
	public boolean insert(Entry entry)
	{
		if (currentSize == maxSize)
		{
			return false;
		}
		Node newNode = new Node();
		newNode.setElement(entry);
		heapArray[currentSize] = newNode;
		heapifyUp(currentSize++);
		return true;
	}  
	public void heapifyUp (int index)
	{
		int parent = (index - 1) / 2;
		Node bottom = heapArray[index];
		while (index > 0 && ((long) ((Entry) (heapArray[parent].getElement())).getKey()) >  
				(long) ((Entry) bottom.getElement()).getKey())
		{
			heapArray[index] = heapArray[parent];// move it down
			index = parent;
			parent = (parent - 1) / 2;
		}
		heapArray[index] = bottom;
		((Entry) heapArray[index].getElement()).setPosition(index); //????????
	}
	public Entry removeMin() // (assumes non-empty list)    
	{
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		heapifyDown(0);
		Entry eRoot = (Entry) root.getElement();
		return eRoot;
	}
	public Entry remove(Entry entry)
	{
		int position = entry.getPosition();
		Node tempNode = heapArray[position];
		heapArray[position] = heapArray[--currentSize];
		heapifyDown(0);
		Entry temp = (Entry) tempNode.getElement();
		return temp;
	}
	public long replaceKey(Entry entry, int key)
	{
		int position = entry.getPosition();
		long tempKey = ((Entry) heapArray[position].getElement()).getKey();
		((Entry) heapArray[position].getElement()).setKey(key);
		heapifyDown(0);
		return tempKey;
	}
	public Object replaceValue(Entry entry, int value)
	{
		int position = entry.getPosition();
		Object tempValue = ((Entry) heapArray[position].getElement()).getValue();
		((Entry) heapArray[position].getElement()).setValue(value);
		return tempValue;
	}
	public Entry min()
	{
		Node root = heapArray[0];
		return (Entry) root.getElement();
	}
	public void heapifyDown(int index)
	{
		int largerChild;
		Node top = heapArray[index];       // save root
		while (index < currentSize / 2)     // while node has at
		{                                  // least one child,
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1; // find larger child
			if (rightChild < currentSize &&  // (rightChild exists?)
					((long) ((Entry) heapArray[leftChild].getElement()).getKey()) > 
					(long) (((Entry) heapArray[rightChild].getElement()).getKey()))
			{
				largerChild = rightChild;
			}
			else
			{
				largerChild = leftChild; // top <= largerChild?
			}
			if (((Entry) top.getElement()).getKey() <= ((Entry) heapArray[largerChild].getElement()).getKey())
			{
				break;
			} // shift child up
			heapArray[index] = heapArray[largerChild];
			index = largerChild; //go down root to index
		}
		heapArray[index] = top;
		((Entry) heapArray[index].getElement()).setPosition(index);
	} 
	public boolean swapValues(int index, int newValue)
	{
		if (index < 0 || index >= currentSize)
		{
			return false;
		}
		long oldValue = ((Entry) heapArray[index].getElement()).getKey(); // remember old
		((Entry) heapArray[index].getElement()).setKey(newValue);  // change to new

		if (oldValue > newValue)
		{                      //if lowered,
			heapifyUp(index);
		}                      //heapify it up
		else                   //if raised,
		{
			heapifyDown(index); //heapify it down
		}
		return true;
	}
}