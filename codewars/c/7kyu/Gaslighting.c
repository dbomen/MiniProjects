#include <stdbool.h>
#include <string.h>

bool gaslighting(const char *shirt_word, const char *your_word, const char *friends_letters) {

    int lengthWords = strlen(shirt_word);
    int lengthKnown = strlen(friends_letters);

    for (int i = 0; i < lengthWords; i++) {
        if (shirt_word[i] != your_word[i]) {
            for (int j = 0; j < lengthKnown; j++) {
                if (shirt_word[i] == friends_letters[j] || your_word[i] == friends_letters[j])  return true;
            }
        }
    }

    return false;
}