#==========================genExp.pl==========================
# by Tony Matts
# Gene Expression Project
# CSCI 451
# Fall '13
#-------------------------------------------------------------
# Takes in Suspension and Biofilm values from a txt file 
# produced by a cluster analysis page. There are values for 
# both Suspension and Biofilm at 4 time periods. Biofilm is
# subtracted from Suspension at each period and the output is
# put into an array and then printed to a csv file.
#=============================================================
#variables
my $susp8,$susp9,$susp7,$susp6; #Suspension
my $bio4,$bio5,$bio3,$bio2; #Biofilm
my @hour4,@hour7,@hour15,@hour27;
my $g = 0;
my $nan = "nan";

# open a file for input
open(IN,"GDS2768.txt")||die;

# Read in all of the lines at once
# variables that begin with @ are arrays
# so the lines are stored in an array.
@arrayOfLines = <IN>;

# Close the file.
close(IN);

# Joins all the lines together to form one long string.
$line = join("\n",@arrayOfLines);

#print "$line\n";

# Split a line into an array.
chomp $line; #gets rid of the newline at end of line
my @arrayOfFields = split(/\t/,$line);

my $arraySize = @arrayOfFields;

for(my $i=0; $i<=$arraySize; $i++){
	#print "\n$arrayOfFields[0]\n";
	$i = $i+2; #iterate over 
	$susp8 = $arrayOfFields[$i];
	$i++;
	$bio4 = $arrayOfFields[$i];
	$i++;
	$susp9 = $arrayOfFields[$i];
	$i++;
	$bio5 = $arrayOfFields[$i];
	$i++;
	$susp7 = $arrayOfFields[$i];
	$i++;
	$bio3 = $arrayOfFields[$i];
	$i++;
	$susp6 = $arrayOfFields[$i];
	$i++;
	$bio2 = $arrayOfFields[$i];
	#Suspension - Biofilm at each time slot
	$hour4[$g] = $susp6 - $bio2;
	$hour7[$g] = $susp7 - $bio3;
	$hour15[$g] = $susp8 - $bio4;
	$hour27[$g] = $susp9 - $bio5;
	$g++;
}
$g=$g-1; #set $g to the size of the hour arrays.

# Open a file for output.
open(OUT,">ecoliDifferentialExp.csv")||die;

#print header
#print OUT "Diff4h\tDiff7h\tDiff15h\tDiff27h\n";

#print time differences in four columns: 4hrs, 7hrs, 15hrs, 27hrs 
for(my $i=1; $i<=$g; $i++){
	if($hour4[$i]eq$nan or $hour7[$i]eq$nan or $hour15[$i]eq$nan or $hour27[$i]eq$nan){
	}
	else{
		# Print to the file.
		print OUT "$hour4[$i]\t";
		print OUT "$hour7[$i]\t";
		print OUT "$hour15[$i]\t";
		print OUT "$hour27[$i]\n";
	}
}
close(OUT);
