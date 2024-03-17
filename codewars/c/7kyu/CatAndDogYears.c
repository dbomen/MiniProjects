#include <stdbool.h>
int getYears(int catYears, bool isCat);

typedef struct Owned_Cat_Dog_Years {
    unsigned owned_cat;
    unsigned owned_dog;
} owned;

owned owned_cat_and_dog(unsigned cat_years, unsigned dog_years) {
    struct Owned_Cat_Dog_Years s1;
    s1.owned_cat = getYears(cat_years, true);
    s1.owned_dog = getYears(dog_years, false);

    return s1;
}

int getYears(int catYears, bool isCat) {
    int a = (isCat) ? 4 : 5;
    int years = 0;
    bool isPerfect = false;

    while (catYears > 0) {
        if      (years == 0)  catYears -= 15;
        else if (years == 1)  catYears -= 9;
        else                  catYears -= a;
        if (catYears == 0)  isPerfect = true;
        years++;
    }

    return (isPerfect) ? years : years - 1;
}