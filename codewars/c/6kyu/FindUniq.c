#include <stddef.h>
#include <stdbool.h>

float finduniq(const float *nums, size_t n) {
    float num = nums[0];
    float uniq = 0;

    bool lfu = false;
    for (size_t i = 1; i < n; i++) {
        if (lfu)  return (nums[i] == num) ? uniq : num;

        if (num != nums[i] && i > 1) return nums[i];
        else if (num != nums[i]) {
            uniq = nums[i];
            lfu = true;
        }
    }

    return -1;
}