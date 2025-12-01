- trying to run the binary and static analysis with ```objdump -D check_list | nvim```:
    - file: ELF x86-64
    - operations:
        1. reads from stdin into buffer of size `0x400` on stack
        2. do alot of `addb` and `subb` operations on these bytes
        3. compare all `0x400` bytes with specific values
            - if all match =>
                - write some message + 
                - open file (prob `./flag` file) + 
                - read (idk what) + 
                - write (prob flag) +
                - exit
            - if none match => 
                - write error message to stdout + 
                - exit
