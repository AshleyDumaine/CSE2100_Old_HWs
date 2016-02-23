//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 05
//October 30, 2013
import java.util.Scanner;
public class EquationSolver 
{
	//there's an included timer that starts after input is entered and stops when a solution is
	//or isn't found at the end
	private SinglyLinkedList possibleNumbers, equationLeftSide, equationRightSide;
	private Heap PQ;
	private static long START_TIME = 0;
	public static void main(String[] args)
	{
		EquationSolver equationSolver = new EquationSolver();
		Scanner sc = new Scanner(System.in);
		int result = sc.nextInt();
		START_TIME = System.nanoTime();
		equationSolver.solver(result);
		final long END_TIME = System.nanoTime() - START_TIME;
		System.out.println("Total execution time: " + (double) END_TIME/1000000000.0 + " seconds");
	}

	public void solver(int result)
	{
		//create a list of all integers less than result to the fifth power
		possibleNumbers = new SinglyLinkedList();
		equationLeftSide = new SinglyLinkedList();
		equationRightSide = new SinglyLinkedList();
		PQ = new Heap(result * (result + 1) * (result + 2) / 6); //exact size, no redundant data
		for (int i = 0; i < result; i++)
		{
			Node data = new Node();
			data.setElement((long) Math.pow(i + 1, 5));
			data.setNumber(i + 1);
			possibleNumbers.addLast(data);
		}
		// make DEF summation list
		for (Node tempPointer_1 = possibleNumbers._head; tempPointer_1 != null; tempPointer_1 = tempPointer_1.getNext())
		{
			for (Node tempPointer_2 = tempPointer_1; tempPointer_2 != null; tempPointer_2 = tempPointer_2.getNext())
			{
				for (Node tempPointer_3 = tempPointer_2; tempPointer_3 != null; tempPointer_3 = tempPointer_3.getNext())
				{
					long summation = (long) tempPointer_3.getElement() - 
							((long) tempPointer_1.getElement() + (long) tempPointer_2.getElement());
					Node data = new Node();
					data.setElement(summation);
					String tuple = "" + tempPointer_1.getNumber() + " " + tempPointer_2.getNumber() + " " + tempPointer_3.getNumber() + " ";
					data.setTuple(tuple);
					equationRightSide.addLast(data);
				}
			}
		}
		//create list of all possible summations for left side, each for loop corresponds to a, b, and c
		for (Node tempPointer_1 = possibleNumbers._head; tempPointer_1 != null; tempPointer_1 = tempPointer_1.getNext())
		{
			for (Node tempPointer_2 = tempPointer_1; tempPointer_2 != null; tempPointer_2 = tempPointer_2.getNext())
			{
				for (Node tempPointer_3 = tempPointer_2; tempPointer_3 != null; tempPointer_3 = tempPointer_3.getNext())
				{
					long summation = (long) tempPointer_1.getElement() + 
							(long) tempPointer_2.getElement() + (long) tempPointer_3.getElement();
					Node data = new Node();
					if (summation >= (long) equationRightSide._head.getElement())
					{
						data.setElement(summation);
						String tuple = "" + tempPointer_1.getNumber() + " " + tempPointer_2.getNumber() + " " + tempPointer_3.getNumber() + " ";
						data.setTuple(tuple);
						equationLeftSide.addLast(data);
					}
				}
			}
		}
		//sort left side list
		SinglyLinkedList left = heapSort(equationLeftSide);
		//sort right side list
		SinglyLinkedList right = heapSort(equationRightSide);
		if (!equalityTester(left, right))
		{
			System.out.println("No solution found.");
			final long END_TIME = System.nanoTime() - START_TIME;
			System.out.println("Total execution time: " + (double) END_TIME/1000000000.0 + " seconds");
			System.exit(0);
		}
	}

	public boolean equalityTester(SinglyLinkedList left, SinglyLinkedList right)
	{
		while ((long) right.getFirst().getElement() < 0)
		{
			//get rid of negatives because they can never be a result
			right.removeFirst();
		}
		int result = 0;
		while (!left.isEmpty() && !right.isEmpty())
		{
			String[] arrayTuple;
			if ((long) left.getFirst().getElement() - (long) right.getFirst().getElement() == 0)
			{
				String doubleTuple = left.getFirst().getTuple() +  right.getFirst().getTuple();
				arrayTuple = doubleTuple.split("\\s");
				//because there's issues comparing in the triple for loops, incrementing order is 
				//tested here before printing the A through F values
				if (Integer.parseInt(arrayTuple[0]) < Integer.parseInt(arrayTuple[1]) &&
						Integer.parseInt(arrayTuple[1])	< Integer.parseInt(arrayTuple[2]) && 
						Integer.parseInt(arrayTuple[2]) < Integer.parseInt(arrayTuple[4]) && 
						Integer.parseInt(arrayTuple[4]) < Integer.parseInt(arrayTuple[5]) &&
						Integer.parseInt(arrayTuple[1])	< Integer.parseInt(arrayTuple[3]))
				{
					//indices 2 and 3 must be swapped because of the issue comparing C and D
					//if the swapped indices are in increasing order, print the solution
					String temp = arrayTuple[2];
					String temp2 = arrayTuple[3];
					arrayTuple[3] = temp;
					arrayTuple[2] = temp2;
					if (Integer.parseInt(arrayTuple[2])	< Integer.parseInt(arrayTuple[3]))
					{
						for (int i = 0; i < arrayTuple.length; i++)
						{
							System.out.print(arrayTuple[i] + " ");
						}
						System.out.println();
					}
				}
				//move pointers
				left.removeFirst();
				right.removeFirst();
				result = 1;
			}
			else if ((long) left.getFirst().getElement() < (long) right.getFirst().getElement())
			{
				//remove smaller node
				left.removeFirst();
			}
			else
			{
				//remove smaller node
				right.removeFirst();
			}
		}
		if (result != 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public SinglyLinkedList heapSort(SinglyLinkedList sequence)
	{
		while (!sequence.isEmpty())
		{
			String tuple = sequence.getFirst().getTuple(); 
			long index =  (long) sequence.removeFirst().getElement();
			PQ.insert(new Entry(index, tuple)); //use values to store tuples
		}										//otherwise the data is lost
		while (!PQ.isEmpty())
		{
			String tuple = (String) PQ.min().getValue();
			long index = PQ.removeMin().getKey();
			Node data = new Node();
			data.setElement(index);
			data.setTuple(tuple);
			sequence.addLast(data);			
		}
		return sequence;
	}
}