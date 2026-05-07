package main// colection of odin files

import "core:fmt"
import "core:os"

main :: proc() {
    for {}
}

for_ :: proc() {
    for i := 0; i < 3; i += 1 do fmt.println(i)
    for i := 0; i < 3; i += 1 {fmt.printf("Hello %d\n", i)}

    // val and index are copies
    x := [3]int{1, 2, 3}
    for val, index in x do fmt.printf("%d:%d\n", index, val)
    // val is reference
    for &val, index in x {
        if val == 4 do continue
        if val == 5 do break
        fmt.printf("%d:%d\n", index, val)
    }

    #reverse for el in x {fmt.println(el)}
    #unroll for el in x {fmt.println(el)}     // expand loop at compile-time to individual statements
}

if_ :: proc() {
    num :: 3
    //odinfmt: disable
    if      num == 3 do fmt.println("x == 3")
    else if num == 4 do fmt.println("x == 4")
    else             do fmt.println("wow")
    //odinfmt: enable
}

switch_ :: proc() {
    // can have 0,1 or 2 statemts (init_statement, switch_expression)
    switch arch := ODIN_ARCH; arch {
    case .i386, .wasm32, .arm32:
        fmt.println("32 bit")
    case .amd64, .wasm64p32, .arm64, .riscv64:
        fmt.println("64 bit")
    case .Unknown:
        fmt.println("Unknown architecture")
    }

    // switch can be used instead of a bunch of if-else statements
    switch i := 2; {
    case i < 0:
        fmt.println("i is negative")
    case i == 0:
        fmt.println("i is zero")
    case:
        fmt.println("i is positive")
    }

    switch c := 'A'; c {
    case 'A' ..= 'Z', 'a' ..= 'z', '0' ..= '9':
        fmt.println("c is alphanumeric")
    }

    Foo :: enum {
        A,
        B,
        C,
        D,
    }
    switch f := Foo.A; f {
    case .A:
    case .B:
    case .C:
    case .D:
    }
    #partial switch f := Foo.A; f {
    case .B:
    case .C:
    }
}

defer_ :: proc() {
    f, err := os.open("my_file.txt")
    if err != os.ERROR_NONE {
        // handle error
    }
    defer os.close(f) // defer execution to end of scope (put on stack)
    // rest of code
}

when_ :: proc() {
    // comp-time if statement (only constanst!), type checked (like #if preprocessor in C)
    when ODIN_ARCH == .i386 {
        fmt.println("32 bit")
    } else when ODIN_ARCH == .amd64 {
        fmt.println("64 bit")
    } else {
        fmt.println("Unsupported architecture")
    }
}
