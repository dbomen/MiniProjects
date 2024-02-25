#include <stdbool.h>
#include <math.h>

bool is_square(int n) {
    for (int i = 0; pow(i, 2) <= n; i++) {
        if (pow(i, 2) == n) {
            return true;
        }
    }
    return false;
}