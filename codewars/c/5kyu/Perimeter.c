typedef unsigned long long ull;

ull fib(int n);

ull perimeter(int n) {
    return 4 * fib(n + 1);
}

ull fib(int n) {
    // SLOW VERSION (recursion)
    // if (n <= 1)  return n;
    // else         return (fib(n - 1) + fib(n - 2));

    if (n <= 0) 
        return 0;
 
    ull fib[n + 1];
    fib[0] = 0, fib[1] = 1;
 
    ull sum = fib[0] + fib[1];
 
    for (int i = 2; i <= n; i++){
        fib[i] = fib[i - 1] + fib[i - 2];
        sum += fib[i];
    }
 
    return sum;
}