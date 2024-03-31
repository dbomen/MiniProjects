#include <stddef.h>
#include <stdlib.h>

#include <stdio.h>

int find_odd(size_t length, const int array[length]) {

    int out = 0;
    for (int i = 0; i < length; i++)
        out = out ^ array[i];

    return out;
}