# queryProt.pl by Tony Matts
# CSCI 451 Computational Biology (Introduction to Bioinformatics)
# Fall 2013
# Project 4

use warnings;
use strict;

# opening the query file
open(IN,"queryNuc.txt")||die;

# reads the query data
@arrayOfLines = <IN>;
 
# close the query file
close(IN);

#=====================================================================
# For each query sequence
	# Write to query text file	
	# Run blast command
	# Parse output
	# Determine what strain	
	
foreach my $line (@arrayOfLines){
  if($line =~ />/){
    $tagLine = $line;
    next;
  }else{
    $query = $line;
    chomp $query;
    print "query tagline: $tagLine\n";
    print "query sequence: $query\n";
  }
}

# Write to query text file-------------------------------------------
open(OUT,">query.txt")||die;
print OUT $query;
close(OUT);

# Run blast command--------------------------------------------------
my $command = "blastp -db anthraxDB.fna -query query.txt -outfmt 5 -evalue 1e-10";
my $result = `$command`;
print $result;

my $setOfHits = "";
if($result =~ /<Iteration>(.*?)<\/Iteration>/sm){
  $setOfHits = $1;
}else{
  print "whoa there, something very very wrong\n";
  exit;
}

while($setOfHits =~ /<Hit>(.*?)<\/Hit>/sgm){
  my $hit = $1;
}

# Parse output-------------------------------------------------------
my @orgNames;
my @scores;

# Determine what strain----------------------------------------------
my %votes;		
if($scores[0] == $scores[1]){
  $votes{"ambiguous"}++;
}else{
  $votes{$orgNames[0]}++;
}

print "\n\n\n------------------------------------------\n";
foreach my $key (keys(%votes)){
  print "votes for $key: ".$votes{$key}."\n";
}
print "------------------------------------------\n\n";
	