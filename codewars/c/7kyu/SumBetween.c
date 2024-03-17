int get_sum(int a, int b) {
    int smaller =  (a > b) ? b : a;
    int bigger =   (a > b) ? a : b;

    int out = 0;
    for (int i = smaller; i <= bigger; i++)  out += i;

    return out;
}