#--------------------Arnaud Somda--------------------------#
#                     Tony Matts                           #
#                  Michael Campbell                        #
############################################################
#                      OVERVIEW                            #
#	This perl script generates a matrix based on sampl-#
# ing of mouses in different environment using different   #
# part of their digestive tract. The script unifies 4 text #
# files by processing each file, finding the percentage of #
# bacterial life found in each sample and storing that value#
# as a link to the mouse represented then outputting it to #
# an external file for R processing.                       #
############################################################

#use strict;
#use warnings;
my @lines = ();

#This line finds all the .txt files in
my $command = "ls -1 *.txt";
my @array = `$command`;
my %hashTaxaAndSampleToVal = ();

foreach my $file (@array){
open(IN, $file)||die;
	@lines = <IN>;
	print "data read\n";
	my $header = shift @lines;
	chomp $header;
	my @sampleNames = split("\t", $header);
	shift @sampleNames;

	foreach my $name (@sampleNames){
		print "|$name|\n";
	}
	print "\tthere are ".@lines." taxa in this file\n";
	foreach my $key (@lines){
		chomp($key);
		my @fields = split("\t",$key);
		my $taxon = shift @fields;
		chomp $taxon;
		
	for(my $i = 0; $i < @fields; $i++){
		my $sample = $sampleNames[$i];
		chomp $sample;
		my $val = $fields[$i];
		chomp $val;
		$hashTaxaAndSampleToVal{$taxon}{$sample} = $val;
		#print $hashTaxaAndSampleToVal{$taxon}{$sample};
	}
		
	}
}
close(IN);
printOut(%hashTaxaAndSampleToVal);
exit;

sub printOut(%Hash){
my %hashTaxaAndSampleToVal = @_;
my @genus;
my @sample;
my $size = 0;
my $val = 0;
	foreach my $key1 (keys %hashTaxaAndSampleToVal){
		foreach my $key2 (keys %{$hashTaxaAndSampleToVal{$key1}}){
			push(@sample, $key2);
			#print $key2."--------".$key1."\n";
		}
		push(@genus, $key1);
	}

	open(OUT, ">mouse.csv");
	print OUT "names";
	foreach $values (@sample){
		my $temp = $values;
		chomp $temp;
		print OUT ",".$temp;
	}
	print OUT "\n";
	foreach my $k1 (@genus){
		$size = 0;
		foreach my $k2 (@sample){
			if(exists($hashTaxaAndSampleToVal{$k1}{$k2})){
				$val = $hashTaxaAndSampleToVal{$k1}{$k2};
			}
			else{
				$val = 0.0;
			}

			if($size == 0){
			my $temp = $k1;
			chomp $temp;
			print OUT $temp.",".$val;
			#print $val;
					}	
			else{
			print OUT ",".$val;
			#print ",".$val;
}
			$size++;
		}
		print OUT "\n";
		#print "\n";
	}
	close (OUT);
}
