# Tony Matts
# HW 2
# Spring '14

# Program sums num down to 1, then puts the value into sum.
# Extra credit: added functionality allowing the Grader to input 
# the value of num and print out the value of sum.

	  .data  # Variable declarations
prompt1:  .asciiz "Enter an integer greater than or equal to one:\n" # Prompt to get num's value from user
prompt2:  .asciiz "The summation of your number to one is:\n" # Prompt to output sum's value to user
num:	  .word	  1 # Integer variable num initialized to one
sum:	  .word	  0 # Integer variable sum initialized to zero

	  .text  # Instructions follow  
main:	
	  li $v0, 4  # Load system call '4 - print string' into $v0
	  la $a0, prompt1  # Load address of prompt1 string into $a0
	  syscall  # Call operating system to perform operation
	  
	  li $v0, 5  # Load system call '5 - read integer' into $v0
	  syscall  # Call operating system to perform operation
	  sw $v0, num  # Move the value $v0 to location num
	  
	  li $t1, 0  # Place zero in register $t1
	  lw $t2, num  # Place the value stored at location num in register $t2
	
loop: 
	  add $t1, $t1, $t2  # Add $t2 to $t1
	  subi $t2, $t2, 1  # Subtract 1 from $t2
	  bne $t2, $zero, loop  # Branch to loop if $t2 is not equal to zero
	  sw $t1, sum  # Move the value of $t1 to location sum
	  j printSum  # Jump to printSum since $t2 equals zero
	
printSum:
	  li $v0, 4  # Load system call '4 - print string' into $v0
	  la $a0, prompt2  # Load address of prompt2 string into $a0
	  syscall  # Call operating system to perform operation
	  	
	  li $v0, 1  # Load system call '1 - print integer' into $v0
	  lw $a0, sum  # Move integer to be printed into $a0
	  syscall  # Call operating system to perform operation
	  j exit  # Jump to exit since sum was printed

exit:	
	  li $v0, 10  # load system call '10 - exit'
	  syscall  # call operating system to perform operation