#include <stdbool.h>

int multiply(int number) {

    bool isNegative = false;
    if (number < 0) {
        isNegative = true;
        number = -number;
    }

    int numberCopy = number;
    int lenght = 1;

    while (numberCopy >= 10) {
        lenght++;
        numberCopy /= 10;
    }

    int res = number;
    
    int i;
    for (i = 0; i < lenght; i++) {
        res *= 5;
    }

    return (isNegative) ? -res: res;
}