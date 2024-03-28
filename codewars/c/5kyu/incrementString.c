

#include <stdbool.h>
#include <stdlib.h>

int numOfDigits(int n);

char* incrementString(const char* str) {

    int size = strlen(str);
    bool numStarting = false;
    int num = 0;

    char* newStr = calloc(size, sizeof(int));
    for (int i = 0; i < strlen(str); i++) {
        char value = str[i];

        if (value >= '0' && value <= '9')  numStarting = true;

        if (numStarting) {
            num *= 10;
            num += value - '0';
        } else {
            newStr[i] = value;
        }
    }

    num++;
    int numSize = numOfDigits(num);
    for (int i = size - numSize; i < size; i++) {
        newStr[i] = num % 10 * numSize;
        numSize--;
    }


    
}

int numOfDigits(int n) {
    int size = 0;
    while (n > 0) {
        n /= 10;
        size++;
    }

    return size;
}
