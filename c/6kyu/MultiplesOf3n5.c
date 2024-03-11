#include <stdio.h>

int solution(int number) {
    int sum = 0;
    for (int i = 0; i < number; i++)  if (i % 3 == 0 || i % 5 == 0)  sum += i;
    return sum;
}

// this is not a real 6kyu imo
// tests dont check for numbers > 5 digits, so this algorithm completes all of them in ~100ms
// ¯\_(ツ)_/¯