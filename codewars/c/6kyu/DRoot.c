int digital_root(int n) {
    int newNumber = 0;

    while (n > 0) {
        newNumber += n % 10;
        n /= 10;
    }

    while (newNumber >= 10) {
        newNumber = digital_root(newNumber);
    }

    return newNumber;
}