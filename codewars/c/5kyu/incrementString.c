#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

char* incrementString(const char* str) {

    int size = strlen(str);
    int sizeOfNum = size;

    for (int i = size - 1; i >= 0; i--) {
        char value = str[i];

        if (!(value >= '0' && value <= '9')) {
            sizeOfNum = size - i - 1;
            break;
        }
    }

    char* num = calloc(sizeOfNum, sizeof(char));
    for (int i = 0; i < sizeOfNum; i++)  num[i] = str[size - sizeOfNum + i];

    int newSizeOfNum = sizeOfNum;
    if (sizeOfNum != 0) {

        bool changed = false;
        int cntOf9 = 0;
        for (int i = sizeOfNum - 1; i >= 0; i--) {
            if (num[i] != '9') {
                num[i]++;
                changed = true;
                break;
            }
            cntOf9++;
        }   

        // if the number is for e.g 999 we have to make another arr
        if (!changed) {
            char* newNum = calloc(sizeOfNum + 1, sizeof(char));
            newNum[0] = '1';
            for (int i = 0; i < sizeOfNum; i++)  newNum[i + 1] = '0';

            num = newNum;
            newSizeOfNum++;   
        } else if (cntOf9 > 0) {
            for (int i = sizeOfNum - 1; cntOf9 > 0; i--) {
                num[i] = '0';
                cntOf9--;
            }
        }
    }

    bool noNumber = (newSizeOfNum == 0);
    char* newStr = calloc(size - sizeOfNum + newSizeOfNum + ((noNumber) ? 1 : 0), sizeof(char));
    int cnt = 0;
    for (; cnt < size - sizeOfNum; cnt++)          newStr[cnt] = str[cnt];
    for (int i = 0; i < newSizeOfNum; i++, cnt++)  newStr[cnt] = num[i];

    if (noNumber)  newStr[cnt] = '1';
    return newStr;
}