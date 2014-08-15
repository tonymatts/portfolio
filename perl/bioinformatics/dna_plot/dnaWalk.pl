# dnaWalk.pl by Tony Matts
# CSCI 451 Computational Biology (Introduction to Bioinformatics)
# Fall 2013
# Project 2

# reads a file containing a DNA sequence, 
# creates an array of the nucleotides, outputs
# X and Y vals to a csv file for plotting in Excel or R.
use warnings;
use strict;

my @nucleotides = readInNucs();
(my $refXs, my $refYs) = takeWalk(@nucleotides);
outputXYs($refXs,$refYs);

sub readInNucs{
	# opening the genome file
	#if it fails to open the program will quit with an error
	open(IN, "NC_001318.fna")|| die;

	# read in the sequence
	my @lines = <IN>;

	# remove the header line
	shift @lines;

	# join all the sequences together into one big sequence
	my $genome = join("",@lines);

	# replace (or switch, hence the "s") all \n's with nothings, and the g option means to do it globally
	$genome =~ s/\n//g;

	# print first 500 characters of the sequence
	print substr($genome,0,500);
	print "\n";

	# build an array with a single nucleotide in each array and print
	print "splitting all of the nucs\n";
	my @nucleotides = split(//,$genome);
	print "done splitting\n";

	return @nucleotides;
}

sub takeWalk{
	my @nucleotides = @_;
	# find/print number of nucleotides
	my $numNucs = @nucleotides;
	my @Xvals;
	my @Yvals;
	print "there are $numNucs nucs\n";

	# initialize X and Y variables
	$Xvals[0] = 0;
	$Yvals[0] = 0;

	# the for loop stops executing one less because the for statement's condition is set to
	# less than which causes it stop when the i variable iterates to the value $numNucs.
	print "building DNA walk\n";
	for(my $i = 1; $i < $numNucs; $i++){
	  my $nuc = $nucleotides[$i];
	  if($nuc eq 'C'){
	    #~ north, add to Y
	    $Xvals[$i] = $Xvals[$i-1];
	    $Yvals[$i] = $Yvals[$i-1];
	    $Yvals[$i]++;
	  }elsif($nuc eq 'G'){
	    #~ south, subtract from y
	    $Xvals[$i] = $Xvals[$i-1];
	    $Yvals[$i] = $Yvals[$i-1];
	    $Yvals[$i]--;
	  }elsif($nuc eq 'A'){
	    #~ west, subtract from X
	    $Xvals[$i] = $Xvals[$i-1];
	    $Yvals[$i] = $Yvals[$i-1];
	    $Xvals[$i]--;
	  }elsif($nuc eq 'T'){
	    #~ east, add to X
	    $Xvals[$i] = $Xvals[$i-1];
	    $Yvals[$i] = $Yvals[$i-1];
	    $Xvals[$i]++;
	  }else{
	    #~ do nothing (set the current cells equal to previous)
	    $Xvals[$i] = $Xvals[$i-1];
	    $Yvals[$i] = $Yvals[$i-1];
	    print "GOT A NONNORMAL NUC: $nuc\n";
	  }
	}
	
	return(\@Xvals,\@Yvals);
}

sub outputXYs{
  	my $refXs = shift;
	my $refYs = shift;
    my @Xvals = @$refXs;
    my @Yvals = @$refYs; 
	#~ outputting the x and y coordinates
	#~ for each nucleotide
	print "outputting the x and y vals\n";
	 
	# create file for output
	open(OUT,">dnawalk.csv")||die;
 
	# print x and y coordinates to output file
	for(my $i = 0; $i < @Xvals; $i++){
	  print OUT $Xvals[$i].",".$Yvals[$i]."\n";
	}
	# close output file
	close(OUT); 
 
	#close the genome file
	close(IN);
}