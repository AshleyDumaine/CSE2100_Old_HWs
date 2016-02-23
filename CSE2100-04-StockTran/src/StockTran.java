//Ashley Dumaine
//CSE2100-001
//Fall 2013
//Lab 04
//October 20, 2013
import java.util.Scanner;
public class StockTran
{
	private Queue _queue;
	private int _capitalGain;
	public static void main(String[] args) 
	{
		StockTran stockTran = new StockTran();
		stockTran._capitalGain = 0;
		stockTran._queue = new Queue(32);
		Scanner sc = new Scanner(System.in);
		try
		{
			while (true)
			{
				String line = sc.nextLine();
				Object [] inputs = line.split("\\s");
				if (inputs[0].equals("q"))
				{
					break;
				}
				stockTran.stockTran(inputs);
			}
		}
		finally
		{
			sc.close(); //just to close resource leak that always gets underlined
		}
	}
	public void stockTran(Object[] inputs)
	{
		if (inputs[0].toString().equals("c") && inputs.length == 1)
		{
			this.capitalGain();
		}
		else if (inputs[0].toString().equals("b") && inputs.length == 3)
		{
			this.buy((Integer.parseInt((String) inputs[1])), (Integer.parseInt((String) inputs[2])));
		}
		else if (inputs[0].toString().equals("s") && inputs.length == 3)
		{
			this.sell((Integer.parseInt((String) inputs[1])), (Integer.parseInt((String) inputs[2])));
		}
		else
		{
			//bad input, do nothing
		}
	}
	public void buy(int shares, int price)
	{
		Object[] data = {shares, price};
		_queue.enqueue(data);
	}
	public void sell(int shares, int price)
	{
		Object[] temp = _queue.dequeue();
		if ((Integer) temp[0] - shares >= 0)
		{
			int remainingShares = (Integer) temp[0] - shares;
			int profit = shares * (price - (Integer) temp[1]);
			//find total capital gain for when capitalGain() is called
			_capitalGain = profit;
			//add remaining shares with original cost back into queue using buy method
			this.buy(remainingShares, (Integer) temp[1]);
		}
		else if ((Integer) temp[0] - shares < 0) //if selling more stocks than in dequeued node
		{
			int profit = shares * (price - (Integer) temp[1]);
			// find total capital gain for when capitalGain() is called
			_capitalGain = profit;
			sell(shares - (Integer) temp[0], price); //sell remainder (recur)
		}
	}
	public void capitalGain()
	{
		System.out.println(_capitalGain);
	}
}