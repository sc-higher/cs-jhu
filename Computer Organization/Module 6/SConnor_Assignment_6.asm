#######################################################
## Lottery Jackpot Odds and Probabilities            ##
## Computer Organization and Design, Summer 2018     ##
##                                                   ##
## @author Sean Connor                               ##
## @date 8 July 2018                                 ##
#######################################################

## Calculation of the lottery jackpot odds generally requires algebraic
## simplifcation of the general formula due to 12! being the highest factorial
## value able to be stored in 32 bit word.
##
## The general formula is C(N,R) = N! / (R! * (N-R)!). This can be simplified
## to sum(N-R+1,N)/R!. This is the formula implemented in this program.

.data
strLN: .asciiz "Enter integer size of large pool: "
strLR: .asciiz "Enter integer amount of numbers to be selected from large pool: "
strSN: .asciiz "Enter integer size of small pool: "
strSR: .asciiz "Enter integer amount of numbers to be selected from small pool: "
strOdds: .asciiz "The odds are 1 in "
strEnd: .asciiz "END"
strNewLine: .asciiz "\n"

.text
.globl main

main:
  # Get large pool size (stored in $s0)
  li $v0, 4
  la $a0, strLN
  syscall

  li $v0, 5
  syscall

  move $s0, $v0

  # Get large pool number selected (stored in $s1)
  li $v0, 4
  la $a0, strLR
  syscall

  li $v0, 5
  syscall

  move $s1, $v0

  # Get small pool size (stored in $s2)
  li $v0, 4
  la $a0, strSN
  syscall

  li $v0, 5
  syscall

  move $s2, $v0

  # Get small pool number selected (stored in $s3)
  li $v0, 4
  la $a0, strSR
  syscall

  li $v0, 5
  syscall

  move $s3, $v0

  # Perform subtraction operations (N-R) for both pools
  sub $t0, $s0, $s1   # stored in $t0 for large pool
  sub $t1, $s2, $s3   # stored in $t1 for small pool

  # Perform addition of 1 to difference (N-R+1) for both pools
  addi $t0, $t0, 1   # update $t0 with sum for large pool
  addi $t1, $t1, 1   # update $t1 with sum for small pool

  # Sum N-R+1 to N for both pools
  move $a0, $s0
  move $a1, $t0
  move $a2, $s0
  jal loop
  move $s4, $a2   # product stored in $s4 for large pool

  move $a0, $s2
  move $a1, $t1
  move $a2, $s2
  jal loop
  move $s5, $a2   # product stored in $s5 for small pool

  # Calculate R! for both pools
  move $a0, $s1
  jal factrl
  move $s6, $v0   # factorial stored in $s6 for large pool

  move $a0, $s3
  jal factrl
  move $s7, $v0   # factorial stored in $s7 for small pool

  # Divide for both pools
  div $s4, $s6
  mfhi $t0   # large pool remainder
  mflo $t1   # large pool quotient

  div $s5, $s7
  mfhi $t2   # small pool remainder
  mflo $t3   # small pool quotient

  # Multiply odds of each pool to get final result
  mul $t1, $t1, $t3   # product (final result) stored in $t1

  # Print a new new line
  li $v0, 4
  la $a0, strNewLine
  syscall

  # Print the result
  li $v0, 4
  la $a0, strOdds
  syscall

  li $v0, 1
	move $a0, $t1
	syscall

  # Print a new new line
  li $v0, 4
  la $a0, strNewLine
  syscall

  # Print a new new line
  li $v0, 4
  la $a0, strNewLine
  syscall

  # Exit
  li $v0, 4
  la $a0, strEnd
  syscall

  li  $v0,10
  syscall

#####################
##   Subroutines   ##
#####################

# Two methods needed to take product of all numbers between N-R+1 ($a1) and
# N ($a0). Product stored in register $a2.

loop:
  ble $a0, $a1, exit    # if N <= N-R+1 exit
  addi $a0,$a0,-1       # else, decrement N by 1
  mul $a2, $a2, $a0     # multiply product by new N
  j loop                # Repeat

exit:
  jr $ra                # Exit by jumping to $ra stored from original jal in
                        # the main method

# Two methods needed to compute the factorial with $a0 as input and $v0
# as result

factrl:
  sw   $ra, 4($sp)    # save the return address
  sw   $a0, 0($sp)    # save the current value of n
  addi $sp, $sp, -8   # move stack pointer
  slti $t0, $a0, 2    # save 1 iteration, n=0 or n=1;n!=1
  beq  $t0, $zero, L1 # notless than 2, calculate n(n-1)!
  addi $v0, $zero, 1  # n=1; n!=1
  jr   $ra            # now multiply

L1:
  addi $a0, $a0, -1   # n = n-1
  jal factrl          # now (n-1)!
  addi $sp, $sp, 8    # reset the stack pointer
  lw   $a0, 0($sp)    # fetch saved (n-1)
  lw   $ra, 4($sp)    # fetch return address
  mul  $v0, $a0, $v0  # multiply (n)*(n-1)
  jr   $ra            # return value n!
