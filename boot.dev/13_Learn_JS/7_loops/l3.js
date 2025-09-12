function printPrimes(max) {

    for (let n = 2; n <= max; n++) {

        if (n == 2) console.log(n);
        // evens
        else if (n % 2 == 0) continue;
        // odds
        else {

            let bad = false;
            for (let i = 3; i * i <= n; i += 2) {

                if (n % i == 0) {

                    bad = true;
                    break;
                }
            }

            if (!bad) console.log(n);
        }
    }
}

// don't touch below this line

function test(max) {
    console.log(`Primes up to ${max}:`);
    printPrimes(max);
    console.log(
        "===============================================================",
    );
}

test(10);
test(20);
test(30);
