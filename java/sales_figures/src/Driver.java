//===================================================================
//Driver.java by Tony Matts
//
//Creates arrays for 5 users and their sales figures for every
//month over 1 year.
//===================================================================
import java.util.Scanner;
import java.io.*;

public class Driver 
{

	//--------------------------------------------
	//2 object arrays are created.
	//Multiple options for displaying the arrays 
	//are commented out.
	//--------------------------------------------
	public static void main(String[] args) throws FileNotFoundException  
	{
		
		File names = new File("./src/People.txt");
		Scanner scan = new Scanner(names);
		
		//User array of 5 is created
		User[] users = new User[5];
		
		int num;
		String first, last;
		
		//5 spots in the user array are instantiated
		for (int indexU = 0; indexU < 5; indexU++)
		{
			num = scan.nextInt();
			first = scan.next();
			last = scan.next();
			users[indexU] = new User(num, first, last);
		}
		
		//--------------------------------------------------
		
		File numbers = new File("./src/SalesFigures.txt");
		Scanner scanNum = new Scanner(numbers);
		
		//Sales array of 60 is created
		Sales[] sales = new Sales[60];
		
		int uNum, figure;
		
		//Sales array is instantiated
		for (int indexS = 0; indexS < 60; indexS++)
		{
			uNum = scanNum.nextInt();
			figure = scanNum.nextInt();
			sales[indexS] = new Sales(uNum, figure);
		}
		
		/*
		//---------------------------------------------------------------------------------
		//Option for showing sales of each user for each month
		int minVal = 0;
		int maxVal = 12;
	
		for (int indexC = 0; indexC < 5; indexC++)
		{
			System.out.println(users[indexC].getName());
		
			
			
			for (int count = minVal; count < maxVal; count++)
			{
				System.out.println(sales[count].getSale());
			}
			
			minVal += 12;
			maxVal += 12;
		}
		//------------------------------------------------------------------------------------
		*/
		
		
		/*
		//------------------------------------------------------------------------------------
		//Option for showing sales of each user for the year
		int minValA = 0;
		int maxValA = 12;
	
		for (int indexD = 0; indexD < 5; indexD++)
		{
			System.out.println(users[indexD].getName());
		
			int total = 0;
			
			for (int countA = minValA; countA < maxValA; countA++)
			{	
				total += sales[countA].getSale();

			}
			System.out.println(total);
			
			minValA += 12;
			maxValA += 12;
		}
		//-------------------------------------------------------------------------------
		*/
		
		
		/*
		//-------------------------------------------------------------------------------
		//Option that show highest sales month between the 5 users and that user's name. 
		int minValB = 0;
		int maxValB = 12;
		
		
		int totalA = 0;
		int saleNumA = 0;
			
			
		for (int countB = minValB; countB < maxValB; countB++)
		{	

			if (totalA < sales[countB].getSale())
			{
				totalA = sales[countB].getSale();
				saleNumA = sales[countB].getNum();
			}
		}
		System.out.println(users[saleNumA].getName());
		System.out.println(totalA);
			
		minValB += 12;
		maxValB += 12;
		//-------------------------------------------------------------------------------
		*/
		
		
		/*
		//-------------------------------------------------------------------------------
		//Option that show lowest sales month between the 5 users and that user's name. 
		int minValC = 0;
		int maxValC = 12;
		
		
		int totalC = 1000;
		int saleNumC = 0;
			
			
		for (int countC = minValC; countC < maxValC; countC++)
		{	

			if (totalC > sales[countC].getSale())
			{
				totalC = sales[countC].getSale();
				saleNumC = sales[countC].getNum();
			}
		}
		System.out.println(users[saleNumC].getName());
		System.out.println(totalC);
			
		minValC += 12;
		maxValC += 12;
		//-------------------------------------------------------------------------------
		*/
		
		
		/*
		//-------------------------------------------------------------------------------
		//Option shows salesperson with lowest sales
		int minValE = 0;
		int maxValE = 12;
		int yTotal = 10000;
		int yLperson = 0;
	
		for (int indexE = 0; indexE < 5; indexE++)
		{
		
			int totalE = 0;
			
			for (int countE = minValE; countE < maxValE; countE++)
			{	
				totalE += sales[countE].getSale();

			}
			if (totalE < yTotal)
			{
				
				yTotal = totalE;
				yLperson = indexE;
			}
			
			minValE += 12;
			maxValE += 12;
		}
		System.out.println(users[yLperson].getName());
		System.out.println(yTotal);
		//-------------------------------------------------------------------------------
		*/
		
		
		/*
		//-------------------------------------------------------------------------------
		//Option shows salesperson with highest sales
		int minValF = 0;
		int maxValF = 12;
		int yTotalF = 0;
		int yLpersonF = 0;
	
		for (int indexF = 0; indexF < 5; indexF++)
		{
		
			int totalF = 0;
			
			for (int countF = minValF; countF < maxValF; countF++)
			{	
				totalF += sales[countF].getSale();

			}
			if (totalF > yTotalF)
			{
				
				yTotalF = totalF;
				yLpersonF = indexF;
			}
			
			minValF += 12;
			maxValF += 12;
		}
		System.out.println(users[yLpersonF].getName());
		System.out.println(yTotalF);
		//-------------------------------------------------------------------------------
		*/
		
		
		/*
		//-------------------------------------------------------------------------------
		//Option that shows the totals between the users for each month
		int minValG = 0;
		int maxValG = 12;
		
		for (int indexG = 0; indexG < 12; indexG++)
		{
			minValG = indexG;
			int totalG = 0;
			for (int countG = minValG; countG < 60; countG += 12)
			{
				
				totalG += sales[countG].getSale();
			
			}
			System.out.println(totalG + "\n");
		}	
		//-------------------------------------------------------------------------------
		*/
	}
}
