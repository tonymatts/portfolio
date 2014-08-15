//========================================
//Sales.java by Tony Matts
//
//Takes in the user number and sale number
//========================================
public class Sales 
{

	//attributes
	private int userNum, sale;
	
	
	//constructor
	public Sales(int u, int s)
	{
		userNum = u;
		sale = s;
	}
	
	
	//getters and setters
	public int getNum()
	{
		return userNum;
	}
	
	public int getSale()
	{
		return sale;
	}
	
	//prints the user number followed by the sales figure
	public String inMonths()
	{
		return userNum + " " + " " + sale + "\n";
	}
	
	
}
