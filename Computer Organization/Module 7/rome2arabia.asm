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
	add $s7, $zero, $zero        # sum counter
	add $s6, $zero, $zero        # input string length
	add $s0, $zero, $zero        # input iterator input[i]
	addi $s1, $zero, 1           # input iterator input[i+1]
	                             
	loop:                        
		li $v0, 11               
		lb $a0, input($s0)       # load input[i] into $a0
		lb $a1, input($s1)       # load input[i+1] into $a1
		beq $a0, 0xA, exit       # if character is newline, jump to exit
		syscall                  
		jal checkExist           # jump to checkExist and save $ra
		j loop                   # iterate through all characters in input string
	                             
return:                          
	j $ra	                     # jump to the stored $ra
	
checkExist:
	add $t0, $zero, $zero
	add $t1, $zero, $zero
	add $t4, $zero, $zero
	add $t5, $zero, $zero
	c1loop:                   
		beq $t0, 7, failexit     # failexit if input[i] not in roman numeral table
		lb $t2, roman($t0)       
		lw $s2, arabic($t4)      
		beq $t2, $a0, c2loop     # if match, jump to c2loop to check input [i+1]
		addi $t0, $t0, 1         # iterate through roman numeral table to match to input[i]
		addi $t4, $t4, 4         # matching arabic value to current roman numeral
		j c1loop                 # loop until match found or all roman numerals checked
	c2loop:
		beq $a1, 0xA, case1      # if input[i+1] == newline, input[i] is last char so jump to case1
		lb $t2, roman($t1)
		lw $s3, arabic($t5)
		beq $t2, $a1, checkCase  # if input[i+1] is a roman numeral, jump to checkCase
		addi $t1, $t1, 1
		addi $t5, $t5, 4
		j c2loop

checkCase:                       # check whether input[i] is greater than or equal to input[i+1]
	bge $s2, $s3, case1          # case1 - input[i] >= input[i+1]
	j case2                      # case2 - input[i] < input[i+1]
	
case1:                           
	add $s7, $s7, $s2            # increase sum by numeral value
	addi $s0, $s0, 1             # move to next input character
	addi $s1, $s1, 1             
	j return                     # jump back to loop
    
case2:                           # input[i] < input[i+1], so add difference to sum
	sub $t7, $s3, $s2            # subtract input[i] from input[i-1]
	add $s7, $s7, $t7            # add difference to sum 
	li $v0, 11                   
	lb $a0, input($s1)           
	syscall                      
	addi $s0, $s0, 2             # move to next input character
	addi $s1, $s1, 2	         
	j return                     # jump back to loop
	
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
	
	# Again?                     # upon successful conversion, ask if want to convert another
	li $v0, 4                    
	la $a0, again                
	syscall                      
	li $v0, 5                    # get user input for again?
	syscall                      
	beq $v0, 1, main             # if input == 1, jump to main to repeat entire program
	
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
	