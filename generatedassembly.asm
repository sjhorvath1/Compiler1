.data
  fum:	.word	0
gi:	.word	0
ger:	.word	0
fro:	.word	0
fi:	.word	0
fee:	.word	0


.text
main:
addi	$t0,   $zero,3
sw	$t0,    fee
addi	$t1,   $zero,4
sw	$t1,    fi
addi	$v0,     $zero,    10
syscall
