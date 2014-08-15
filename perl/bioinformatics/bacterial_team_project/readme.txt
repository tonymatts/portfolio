Arnaud Somda, Tony Matts, and Michael Campbell
10/30/13
Project 6
CSCI 451
Fall

Section I - Running the Files:

procData.pl
	1. Run a command line application of your choice, like Terminal.
	2. Open file path to the folder containing the file procData.pl.
	3. Type perl procData.pl.
	4. Assuming you have a the proper data files in the same folder as procData.pl,  a csv file
	containing a matrix should appear in the same folder.
	5. If an error occurs, you may need to download a perl compiler.

	The perl script will generate a matrix based on sampling of mouses in different environment using different part of their digestive tract. The script unifies 4 text files by processing each file, finding the percentage of bacterial life found in each sample and storing that value as a link to the mouse represented then outputting it to an external file for R processing.


mouse.R
	1. Open the R application/compiler.
	2. Open mouse.R and run the file.
	3. Should produce two 2D plots.

	The R file analyzes a single data file(the csv file produced by the perl script) to determine if all of the samples taken from a given location (ilium, cecum, etc.) are more similar to one another than to other sample points using Principal Coordinates Analysis and Linear Discriminant Analysis. 




















