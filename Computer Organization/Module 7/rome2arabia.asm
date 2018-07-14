########################################
##           ROME TO ARABIA           ##
##                                    ##
## Convert roman numeral input to     ##
## arabic (decimal) format.           ##
##                                    ##
## Sean Connor                        ##
## 14 July 2018                       ##
## Computer Organization JHU 604.204  ##
########################################

.data

# Roman numeral to arabic table
roman: 
	.asciiz "IVXLCDM"
arabic: 
	.word 1, 5, 10, 50, 100, 500, 1000

# Main prompt
prompt:
	.asciiz "\nPlease enter a roman numeral sequence: "

# Exit prompts
convdisplay:
	.asciiz " is equal to "
fexit:
	.asciiz "Conversion failed - invalid character(s)"
sexit:
	.asciiz "\n\nSUCCESS"
again:
	.asciiz "\n\nConvert again (1 = yes)? "
finalexit:
	.asciiz "\nThanks for using Rome2Arabia! Goodbye!"

# Allocate 64 bytes of space for user input	
input:
	.space 64
  
.text

main:
	# Initial user prompt
	li $v0, 4
	la $a0, prompt
	syscall
    
	# Read user input as string
	li $v0, 8
	la $a0, input
	li $a1, 64
	syscall
	
	# Print new line
	addi $a0, $0, 0xA
    addi $v0, $0, 0xB
	syscall
	
	# Initialize sum counter, length, and input iterators
	add $s7, $zero, $zero   # sum counter
	add $s6, $zero, $zero   # input string length
	add $s0, $zero, $zero   # input iterator input[i]
	addi $s1, $zero, 1      # input iterator input[i+1]
	
	loop:
		li $v0, 11
		lb $a0, input($s0)
		lb $a1, input($s1)
		beq $a0, 0xA, exit
		syscall
		jal checkExist
		j loop
	
return:
	j $ra	
	
checkExist:
	add $t0, $zero, $zero
	add $t1, $zero, $zero
	add $t4, $zero, $zero
	add $t5, $zero, $zero
	c1loop:
		beq $t0, 7, failexit
		lb $t2, roman($t0)
		lw $s2, arabic($t4)
		beq $t2, $a0, c2loop
		addi $t0, $t0, 1
		addi $t4, $t4, 4
		j c1loop
	c2loop:
		beq $a1, 0xA, case1
		lb $t2, roman($t1)
		lw $s3, arabic($t5)
		beq $t2, $a1, checkCase
		addi $t1, $t1, 1
		addi $t5, $t5, 4
		j c2loop
		
checkCase:
	bge $s2, $s3, case1
	j case2
	
case1:
	add $s7, $s7, $s2
	addi $s0, $s0, 1
	addi $s1, $s1, 1
	j return

case2:
	sub $t7, $s3, $s2
	add $s7, $s7, $t7
	li $v0, 11
	lb $a0, input($s1)
	syscall
	addi $s0, $s0, 2
	addi $s1, $s1, 2	
	j return
	
exit:	
	# Print result
	li $v0, 4
	la $a0, convdisplay
	syscall
	li $v0, 1
	move $a0, $s7
	syscall
	
	# Print success
	li $v0, 4
	la $a0, sexit
	syscall
	
	# Again?
	li $v0, 4
	la $a0, again
	syscall
	li $v0, 5
	syscall
	beq $v0, 1, main
	
	# Goodbye
	li $v0, 4
	la $a0, finalexit
	syscall
	
	# Exit
	li $v0, 10
	syscall
	
failexit:
	# Print fail exit
	li $v0, 4
	la $a0, fexit
	syscall
	
	# Exit
	li $v0, 10
	syscall
	