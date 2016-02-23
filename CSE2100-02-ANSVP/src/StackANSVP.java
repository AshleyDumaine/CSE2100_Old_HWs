//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 02
//September 15, 2013
import java.util.Scanner;

public class StackANSVP 
{
	private String[] _sequenceString;
	private Stack _stack;
	//The sequence from the user is formatted into a String array after removing commas
	//and spaces. The String type is then converted into an int type while processing
	//each index and comparing it to the top of the stack. If there is an object on the
	//stack to be popped that is also greater than the current value at the specific String
	//index, it will be popped and discarded. If there is nothing on the stack, there is no
	//smaller preceding value. Otherwise, the top of the stack is the current near smallest
	//value. The value at the specified String index will then be pushed onto the top of the
	//stack.
	public void stackSolve(String sequence)
	{
		_stack = new Stack();
		_sequenceString = sequence.split("[\\,]"); //split for any operand
		for(int i = 0; i < _sequenceString.length; i++)
		{
			_sequenceString[i] = _sequenceString[i].trim(); //get rid of whitespace
		}
		System.out.println("The nearest previous smaller values for this sequence:");
		for (int j = 0; j < _sequenceString.length; j++)
		{
			while (!_stack.isEmpty() && _stack.top()
					>= Integer.parseInt(_sequenceString[j])) //convert top value and the
			{												 //String to integers to compare
				_stack.pop(); //remove larger value and discard
			}
			if (_stack.isEmpty())
			{
				System.out.print("-"); //no smaller preceding value
			}
			else
			{
				System.out.print(", " + _stack.top()); //existing preceding smaller value
			}
			_stack.push(_sequenceString[j]); //add current String value to top and re-evaluate
		}
	}
	public static void main(String[] args) 
	{
		StackANSVP ansvp = new StackANSVP();
		System.out.println("Enter sequence: ");
		Scanner sc = new Scanner(System.in);
		String sequence = sc.nextLine();
		ansvp.stackSolve(sequence);
	}
}