import java.util.PriorityQueue;
import java.util.Scanner;

public class StockTran2 {
	PriorityQueue<Share> sellPQ;
	PriorityQueue<Share> buyPQ;
	public static void main(String args[]) {
		StockTran2 self = new StockTran2();
		self.populatePQs();
	}
	
	public void populatePQs() {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String[] parameters = input.trim().split(" ");
		Share share = new Share(Integer.parseInt(parameters[1]), Double.parseDouble(parameters[3]));
		if (parameters[0].equals("Buy")) {
			buyPQ.add(share);
		}
		else {
			sellPQ.add(share);
		}
		sc.close();
	}
	
	public String transaction() {
		// check the buy PQ and modify sell PQ as necessary
		return "";
	}
}
