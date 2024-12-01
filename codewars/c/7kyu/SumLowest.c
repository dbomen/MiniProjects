#include <stddef.h>
#include <limits.h>
#include <stdbool.h>

long sum_two_smallest_numbers(size_t n, const int numbers[n]) {
    size_t lowest[] = {INT_MAX, INT_MAX};

    for (int i = 0; i < (int) n; i++) {
        if (numbers[i] < lowest[0] || numbers[i] < lowest[1]) {
            bool smaller = lowest[0] < lowest[1];
            if (smaller) lowest[1] = numbers[i];
            else         lowest[0] = numbers[i];
        }
    }

    return lowest[0] + lowest[1];
}