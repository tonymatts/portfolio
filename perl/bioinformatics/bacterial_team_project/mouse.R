#===============================================================================
# mouse.R -- Arnaud Somda, Tony Matts, and Michael Campbell
# Arnaud Somda, Tony Matts, and Michael Campbell
# 10/30/13
# Project 6
# CSCI 451
# Fall
#
#-------------------------------------------------------------------------------
#
# Analyzes a single data file to determine if all of the samples taken from a 
# given location (ilium, cecum, etc.) are more similar to one another than to 
# other sample points using Principal Coordinates Analysis and Linear 
# Discriminant Analysis.
#===============================================================================
#setwd(~/Documents/school_fall_13/csci451/proj6Team)
if(length(grep("linux",R.Version()$os))){
	windows <- function( ... ) X11( ... )
}

# read in  data.
mydata = read.csv("mouse.csv", row.names = 1, header = TRUE)

# removes any genera that show-up in only one sample by building an array with all of 
# the indexes of rows that have only one non-zero value.
for(i in 1:nrow(mydata)){
	indexes = which(mydata[i,] >0)
	numNonZero = length(indexes)
	if(numNonZero < 2){
		mydata = mydata[-i,]
	}
}

# gets rid of any rows for which the genus is unknown, naming it “Other”.
taxa = row.names(mydata)
taxaList = strsplit(taxa,";")
taxaMatrix = matrix(unlist(taxaList),nrow=length(taxaList[[1]]))
genusVector = taxaMatrix[6,]
rowsWithOtherInGenus = which(genusVector == "Other")

mydata = t(mydata) #transposes the data.
sampleNames = row.names(mydata) #find sample names by getting row names.

# look for the associated letters for each of the sample points.
indexesOfIleum = grep(".*IL", sampleNames)
indexesOfDColon = grep(".*DC", sampleNames)
indexesOfPColon = grep(".*PC", sampleNames)
indexesOfCTip = grep(".*A", sampleNames)
indexesOfCecum = grep(".*Ce", sampleNames)
indexesOfMColon = grep(".*MC", sampleNames)

# create a vector of colors
myColors = rep("red",59)
myColors[indexesOfCecum] = "green";
myColors[indexesOfCTip] = "blue";
myColors[indexesOfPColon] = "orange";
myColors[indexesOfMColon] = "gray";
myColors[indexesOfDColon] = "darkorchid";

# instantiate classic multidimensional scaling
myMDS = cmdscale(dist(mydata))

# plot the data transformed into two dimensions
plot(myMDS)
myMultiplier = 1
windows(3,3)
par(mar=c(3.0,2.5,.1,.1),mgp = c(1.5, .75, 0))
plot(myMDS,col = myColors,pch = 19,
xlab = "Principal Coordinate 1",
ylab = "Principal Coordinate 2",
cex.lab = 1, cex.axis = 1,bty = "n")
box(lwd = myMultiplier)
myText = c('Ileum', 'Cecum', 'Tip of Cecum', 'Proximal Colon', 'Mid-Colon', 'Distal Colon')
myPCH = rep(19,length(myText))
listOfColors = c("red","green","blue","orange","gray","darkorchid")
colorsForLegend = listOfColors
legend("topleft",myText,pch = myPCH,cex = .7*myMultiplier,
col = colorsForLegend,box.lwd = myMultiplier)
library(MASS)
z <- lda(mydata,myColors,tol = 0)
projectionOntoFirst = as.matrix(mydata) %*% z$scaling[,1]
projectionOntoSec = as.matrix(mydata) %*% z$scaling[,2]
XYs = cbind(projectionOntoFirst, projectionOntoSec)

windows(3,3)
par(mar=c(3.0,2.5,.1,.1),mgp = c(1.5, .75, 0))
plot(projectionOntoFirst, projectionOntoSec,pch = 19,
  col = myColors,cex = 1,
  xlab = "Linear Discriminant 1",
  ylab = "Linear Discriminant 2",
  xlim = c(-5,20),
  cex.lab = 1, cex.axis = 1,bty = "n")
box(lwd = 1)
myText = classNames
myPCH = rep(19,length(myText))
colorsForLegend = listOfColors
legend("bottomright",myText,pch = myPCH,cex = .7,
col = colorsForLegend,box.lwd = 1)

save.image()