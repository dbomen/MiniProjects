#include <stddef.h>
#include <string.h>
#include <stdlib.h>

char rotateLetter(char letter);

char *rot13(const char *src) {
    
    int length = strlen(src);
    char* out = (char*) malloc(length);

    for (int i = 0; i < length; i++) {
        out[i] = rotateLetter(src[i]);
    }
    out[length] = '\0';

    return out;
}

char rotateLetter(char letter) {
    if (letter < 'A' || (letter > 'Z' && letter < 'a') || letter > 'z')  return letter;

    int newLetter = letter + 13;
    if ((newLetter > 'Z' && letter <= 'Z') || (newLetter > 'z'))  newLetter -= 'Z' - 'A' + 1;

    return newLetter;
}