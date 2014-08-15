//==============================================
//User.java by Tony Matts
//
//Creates a user name and number
//==============================================
public class User 
{

	//attributes
	private int userNum;
	private String fName, lName;
	
	//constructor
	public User(int u, String f, String l)
	{
		userNum = u;
		fName = f;
		lName = l;
	}
	
	//getters and setters
	public String getFirst()
	{
		return fName;
	}
	
	public String getLast()
	{
		return lName;
	}
	
	public String getName()
	{
		return fName + " " + lName;
	}
	
	//prints a display of the user and their number
	public String toString()
	{
		return userNum + " " + fName + " " + lName + "\n";
	}
}
