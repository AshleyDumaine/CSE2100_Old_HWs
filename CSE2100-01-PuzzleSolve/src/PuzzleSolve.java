//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 01
//September 12, 2013
import java.util.*;
public class PuzzleSolve 
{
	private SinglyLinkedList _uniqueLetters;
	private String[] _words;
	private SinglyLinkedList _numberAssignment;
	private SinglyLinkedList _numberList;
	private String _combinedString;

	/*
	 * Takes the input from the user, determines the number of unique letters in the equation,
	 * makes sure there are no more than 10 unique letters for the 10 digits, prints out the
	 * unique letters, creates a linked list of the 10 digits and a blank list for the number
	 * assignments, then passes the number of unique letters, the digit list, and the blank list
	 * to the recursiveSolver method.
	 */	
	public void puzzleSolve(String puzzle)
	 {
		 _words = puzzle.split("[\\+=]"); //split for any operand
		 for(int i = 0; i < _words.length; i++)
		 {
			 _words[i] = _words[i].trim(); //get rid of whitespace
		 }
		 _combinedString = puzzle;

		 _uniqueLetters = new SinglyLinkedList();
		 boolean[] alphabet = new boolean[26];
		 for(int i = 0; i < alphabet.length; i++)
		 {
			 alphabet[i] = false; 
		 }
		 for(int i = 0; i < _combinedString.length(); i++)
		 {
			 if(_combinedString.charAt(i) >= 'a' && _combinedString.charAt(i) <= 'z')
			 { //ignores operands
				 int index = _combinedString.charAt(i) - 'a';
				 if(!alphabet[index])
				 {
					 alphabet[index] = true;
					 Node newLetter = new Node(_combinedString.charAt(i), null);
					 _uniqueLetters.addLast(newLetter);
				 }
			 }
		 }
		 // check if we have too many unique letters
		 if (_uniqueLetters.getSize()> 10)
		 {
			 System.out.println(_uniqueLetters);
			 System.exit(0);
		 }
		 // print the alphabet
		 System.out.println(_uniqueLetters);
		 // initial list of unused digits
		 _numberList = new SinglyLinkedList();
		 for (int k = 0; k < 10; k++)
		 {
			 Node data = new Node(k);
			 _numberList.addLast(data);
		 }		
		 //make sure _numberAssignments is an empty list
		 _numberAssignment = new SinglyLinkedList();
		 // run recursive solver
		 recursiveSolver((int) _uniqueLetters.getSize(), _numberAssignment, _numberList);
	 }
/*
 * Utilizes recursion to find if a certain order of numbers satisfies the word 
 * equation when the digits are assigned to their letters. Uses recursion down 
 * to a depth k dependent on the number of unique letters to assign an order of
 * numbers from the digits list to the initially empty list of number assignments.
*/	
	public void recursiveSolver(int k, SinglyLinkedList numberAssignment, 
		SinglyLinkedList digitList)
	 {
		 if (k == 0)
		 {
			 evaluate(numberAssignment);
			 return;
		 }
		 else
		 {
			 int numChoices = digitList.getSize();
			 for(int i = 0; i < numChoices; i++ )
			 {
				 Node tempNode = digitList.removeFirst();
				 //Node lastAssignedNode = numberAssignment._tail;
				 numberAssignment.addFirst(tempNode);
				 // recurse				
				 recursiveSolver(k-1, numberAssignment, digitList);
				 // fix the lists
				 tempNode = numberAssignment.removeFirst();
				 digitList.addLast(tempNode);
			 }
		 }
	 }
	
/*
 * Tests to see if the given order of number assignments satisfies the word equation
 * by replacing the letters in the equation with their assigned values by stepping through
 * each node and then splits the number chunks by the operands. These chunks of strings are
 * then converted into their integer values and tested to see if the sum on the left side 
 * is equal to the number chunk on the right side. If there's equality, print that solution.
*/
	public void evaluate(SinglyLinkedList assignment)
	 {		
		 //_numberAssignment/digitNode is not equalling _numberList
		 String digitString = _combinedString;
		 // replace all letters with digits
		 Node digitNode = assignment._head, letterNode = _uniqueLetters._head; //assignment's head is 1 less than it should be
		 for ( ; digitNode != null && letterNode != null; ) 
		 {
			 String letter = "" + letterNode.getElement();
			 String digit = "" + digitNode.getElement();
			 // replace all instances of leter in _combinedString with digit
			 digitString = digitString.replaceAll(letter, digit);
			 // next iteration
			 digitNode = digitNode.getNext(); //digit is 1 less than what it should be
			 letterNode = letterNode.getNext();
		 }
		 // split the equation into chunks of numbers
		 String[] numberAsStringArray = digitString.split("[\\+=]");
		 int[] numberAsIntegerArray = new int[numberAsStringArray.length];
		 int sum = 0, lastPartValue = 0;
		 for(int i = 0; i < numberAsStringArray.length; i++)
		 {
			 numberAsIntegerArray[i] = (int) Integer.parseInt(numberAsStringArray[i].trim());
			 if(i == numberAsStringArray.length - 1)
			 {
				 // the last part
				 lastPartValue = numberAsIntegerArray[i];
			 }
			 else
			 {
				 // not the last part
				 sum += numberAsIntegerArray[i]; 
			 }
		 }
		 // check if the sum equals the last part
		 if(sum == lastPartValue)
		 {
			 // System.out.println("Solution found: ");
			 System.out.println(_numberAssignment);
		 }
	 }

	// main method
	 public static void main(String[] args)
	 {
		 PuzzleSolve ps = new PuzzleSolve();
		 Scanner input = new Scanner(System.in);
		 System.out.println("Enter puzzle:");
		 String puzzle = input.nextLine();
		 ps.puzzleSolve(puzzle);
		 input.close();
	 }
}