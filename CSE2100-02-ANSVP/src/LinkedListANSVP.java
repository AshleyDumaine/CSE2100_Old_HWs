//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 02
//September 15, 2013
import java.util.Scanner;

public class LinkedListANSVP 
{
	private String[] _sequenceString;
	//NOTE: This linked list is actually a doubly linked list, I got an okay to use this
	private DoublyLinkedList _linkedList;
	//The sequence from the user is formatted into a String array after removing commas
	//and spaces. The String type is then converted into an int type while processing
	//each index and comparing it to the previous values in the linked list. If there is a
	//value greater than the current value at the specific String index in the linked list, 
	//it will be ignored. If there is nothing in the linked list, there is no smaller preceding 
	//value. Otherwise, the linked list node is the current near smallest value. The value at 
	//the specified String index will then be added to the front of the linked list.
	public void linkedListSolve(String sequence)
	{
		_linkedList = new DoublyLinkedList();
		_sequenceString = sequence.split("[\\,]"); //split for any operand
		for(int i = 0; i < _sequenceString.length; i++)
		{
			_sequenceString[i] = _sequenceString[i].trim(); //get rid of whitespace
		}
		//fill linked list with sequence
		for (int j = 0; j < _sequenceString.length; j++)
		{
			Node data = new Node(_sequenceString[j]);
			_linkedList.addLast(data);
		}
		System.out.println("The nearest previous smaller values for this sequence:");
		//compare nodes
		System.out.print("-"); //nothing to compare for first value
		for (Node node = _linkedList._head; ; )
		{
			for (Node rest = node.getNext(); rest != null; rest = rest.getNext()) 
			{					
				if (Integer.parseInt((String) node.getElement()) < 
						Integer.parseInt((String) rest.getElement()))
				{
					//print smaller value
					System.out.print(", " + Integer.parseInt((String) node.getElement()));
				}
				else if (Integer.parseInt((String) node.getElement()) > 
						Integer.parseInt((String) rest.getElement()))
				{	
					//use crazy doubly linked list trick
					//print smaller value
					Node target = rest;
					for (Node previous = node.getPrevious(); previous != null; previous = previous.getPrevious())
					{
						if (Integer.parseInt((String) previous.getElement()) <
								Integer.parseInt((String) target.getElement())) //aha!
						{
							System.out.print(", " + Integer.parseInt((String) previous.getElement()));
							break;
						}
						else
							target = previous; //grr
						if (previous.getPrevious() == null)
						{
							System.out.print("-");
						}

					}
				}
				else
				{
					System.out.print("-"); //no preceding smaller value
				}
				node = node.getNext();
			}
			if (node == _linkedList._tail)
				break; //no more incoming numbers to compare, so stop
		}
	}
	public static void main(String[] args) 
	{
		LinkedListANSVP ansvp = new LinkedListANSVP();
		System.out.println("Enter sequence: ");
		Scanner sc = new Scanner(System.in);
		String sequence = sc.nextLine();
		ansvp.linkedListSolve(sequence);
	}
}
