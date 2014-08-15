# Tony Matts
# HW 3
# Spring '14

# uses subroutines to perform a summation of on two arrays 
# while adding a factor of 5 each iteration:
# Running Total = factor + (a[i]*b[i]) from 1 to num

	.data # Variable declarations
num:	.word	10 	# Allocate a word num set to 10
arrayA:	.word  	0 : 10 	# Allocate array a of length 10
arrayB:	.word  	0 : 10 	# Allocate array b of length 10
init:	.word	2  	# Allocate a word init set to 2
factor:	.word	5  	# Allocate a word for factor set to 5
total: 	.word	0  	# Allocate a word for total set to 0

	.text 			# Instructions follow
	lw $t4, factor		# $t4 = factor
	lw $t1, init 		# $t1 = init
	lw $t7, num		# $t7 = num
	addi $t7, $t7, 1	# 1 added to num for branch checking
	
	li $t0, 0 		# i = 0 ($t0 is i)
	li $t3, 0 		# byte offset set to 0
	jal Fill_A 		# Fill array a[i] with i*init
	
	li $t0, 0 		# i = 0 ($t0 is i)
	li $t6, 44 		# byte offset set to 48
	addi $t1, $t1, 2	# Initialize init*2 for arrayB
	jal Fill_B		# Fill array b[i] with i*2*init
	
	li $t0, 0 		# i = 0 ($t0 is i)
	li $t3, 0 		# byte offset set to 0
	li $t6, 44		# byte offset set to 48
	jal Multiply		# Begin a loop to multiply a[i] and b[i] and add the factor
	
	li $v0, 10		# load exit call
	syscall			# call exit
	
Fill_A:
	mul $t2, $t0, $t1 	# $t2 = i*init
	sw  $t2, arrayA($t3) 	# a[i] = $t2
	addi $t0, $t0, 1 	# i++
	addi $t3, $t3, 4 	# $t3 increased by 4 for byte offset
	bne  $t0, $t7, Fill_A	# if i is less than 11, repeat loop
	jr $ra			# return to where called
	
Fill_B:
	mul $t2, $t0, $t1 	# $t2 = i*init
	sw  $t2, arrayB($t6) 	# b[i] = $t2
	addi $t0, $t0, 1 	# i++
	addi $t6, $t6, 4 	# $t3 increased by 4 for byte offset
	bne  $t0, $t7, Fill_B	# if i is less than 11, repeat loop
	jr $ra			# return to where called
	
Multiply:
	lw $a0, arrayA($t3)	# $a0 = a[i]
	lw $a1, arrayB($t6)	# $a1 = b[i]
	mul $t5, $a0, $a1 	# Multiply a[i] times b[i] and place result in location $t5 the temp
	add $t5, $t5, $t4 	# Add factor to temp
	add $a2, $a2, $t5	# Add temp to running total stored in a register of your choice which is $a2
	addi $t3, $t3, 4 	# $t3 increased by 4 for byte offset
	addi $t6, $t6, 4 	# $t3 increased by 4 for byte offset
	addi $t0, $t0, 1 	# i++
	bne  $t0, $t7, Multiply	# if offset is less than 11*4, repeat loop
	jr $ra			# return to where called
