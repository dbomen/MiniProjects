from pwn import *

s = ssh(host='pwnable.kr', port=2222, password='guest', user='col')

# we want: 0x21DD09EC
# we have to send 20B => we cant just do p32(0x21DD09EC), because that is only 4B
# Can we fill the 16B with 0x00? No! As we know the string terminator '\0' is 0x00, so out argv[2] will still be 4B long

# There are multiple solution for how to do this
# 1st: we fill the 16B with 0x01 
# 2nd: we divide 0x21DD09EC into 5 32b pieces (to fill the 20B). The first 4 are identical and the last one just makes sure
#      that, if 0x21DD09EC is not divisible by 5 - in our case it is not, the sum will be the same value
# Note that in both solution we got 'lucky' that none of the 4B pieces dont contain 0x00, because if any did we would have
# the same problem with the string terminator as before => we would have to find another solution to do this

# ---

# 1st
# p = '\x01\x01\x01\x01' * 4 + '\xe8\x05\xd9\x1d'
p = p32(0x01010101) * 4 + p32(0x21DD09EC - 0x01010101 * 4)

# 2nd
# p = p32(0x21DD09EC // 5) * 4 + p32(0x21DD09EC - ((0x21DD09EC // 5) * 4))

print(p)
col_process = s.process(["./col", p])

print(col_process.recvall().decode())
