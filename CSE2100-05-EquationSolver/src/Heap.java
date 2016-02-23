//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 05
//October 30, 2013
public class Heap 
{
	private Node[] heapArray; 
	private int maxSize;
	private int currentSize;
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
	}
	public Entry removeMin() // (assumes non-empty list)    
	{
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		heapifyDown(0);
		Entry eRoot = (Entry) root.getElement();
		return eRoot;
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