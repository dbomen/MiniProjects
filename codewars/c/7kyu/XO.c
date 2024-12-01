#include <stdbool.h>
#include <string.h>

bool xo (const char* str) {
    int xo[] = {0, 0};

    for (int i = 0; i < (int) strlen(str); i++) {
        if (str[i] == 'x' || str[i] == 'X') {
            xo[0]++;
        } else if (str[i] == 'o' || str[i] == 'O') {
            xo[1]++;
        }
    }
    return xo[0] == xo[1];
}