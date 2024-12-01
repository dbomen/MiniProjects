#include <stddef.h>
#include <string.h>

size_t get_count(const char *s) {

    int cnt = 0;
    int lenght = (int) strlen(s);

    char vowels[] = {'a', 'e', 'i', 'o', 'u'};
    for (int i = 0; i < lenght; i++) {
        for (int j = 0; j < 5; j++) {
            if (s[i] == vowels[j]) {
                cnt++;
                break;
            }
        }
    }

    return cnt;
}