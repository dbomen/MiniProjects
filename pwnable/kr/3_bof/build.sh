#!/bin/sh

gcc -o bof bof.c -std=c99 -fno-stack-protector -no-pie -static
