#include <stddef.h>

void move_zeros(size_t len, int arr[len]) {

    int cntZeros = 0;
    int out[len];
    size_t index = 0;

    for (size_t i = 0; i < len; i++) {
        if (arr[i] == 0)  cntZeros++;
        else {
            out[index] = arr[i];
            index++;
        }
    }

    for (; index < len; index++)  out[index] = 0;

    for (size_t i = 0; i < len; i++)  arr[i] = out[i];
}